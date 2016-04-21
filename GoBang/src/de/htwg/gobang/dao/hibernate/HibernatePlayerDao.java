package de.htwg.gobang.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.entities.impl.GamePlayer;
import de.htwg.gobang.game.GoBangModule;
import de.htwg.gobang.persistence.IPersistentPlayer;
import de.htwg.gobang.persistence.hibernate.HibernatePlayer;
import de.htwg.gobang.persistence.hibernate.HibernateUtil;

public class HibernatePlayerDao implements IPlayerDao {

	private final SessionFactory sessionFactory;
	private final Injector injector;

	public HibernatePlayerDao() {
		sessionFactory = HibernateUtil.getInstance();
		injector = Guice.createInjector(new GoBangModule());
	}

	private IGamePlayer copyPlayer(IPersistentPlayer persistentPlayer) {
		if (persistentPlayer == null) {
			return null;
		}
		IGamePlayer player = new GamePlayer(persistentPlayer.getName());
		player.setId(persistentPlayer.getId());
		// player.setName(persistentPlayer.getName());
		for (int i = 0; i < persistentPlayer.getWins(); i++) {
			player.addWin(player);// ?
		}
		for (int i = 0; i < persistentPlayer.getLosses(); i++) {
			player.addLoss(player);// ?
		}
		List<Integer> list = new ArrayList<>();
		for(Integer id : persistentPlayer.getEnemies()) {
			list.add(id);
		}
		player.setEnemies(list);

		return player;
	}

	private IPersistentPlayer copyPlayer(IGamePlayer player) {
		if (player == null) {
			return null;
		}

		int playerId = player.getId();
		IPersistentPlayer persistentPlayer;
		if (containsPlayerById(playerId)) {
			// The Object already exists within the session
			Session session = HibernateUtil.getInstance().getCurrentSession();
			persistentPlayer = (HibernatePlayer) session.get(HibernatePlayer.class, playerId);
		} else {
			// A new database entry
			persistentPlayer = new HibernatePlayer();
		}

		persistentPlayer.setId(playerId);
		persistentPlayer.setName(player.getName());
		persistentPlayer.setWins(player.getWins());
		persistentPlayer.setLosses(player.getLosses());
		List<Integer> list = new ArrayList<>();
		System.out.println(player.getEnemies());

		for (Integer id : player.getEnemies()) {
			list.add(id);
		}
		persistentPlayer.setEnemies(list);
		return persistentPlayer;
	}

	@Override
	public void saveOrUpdatePlayer(IGamePlayer player) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
//			SQLQuery query = session.createSQLQuery("drop table player"); 
//			query.executeUpdate();
			IPersistentPlayer persistentPlayer = copyPlayer(player);
			session.saveOrUpdate(persistentPlayer);
			tx.commit();
		} catch (HibernateException ex) {
			handleHibernateException(ex, tx);
		}
	}

	@Override
	public IGamePlayer loadPlayer(IGamePlayer player) {

		IGamePlayer result = null;
		// Transaction tx = null;
		// try {
		// Session session = sessionFactory.getCurrentSession();
		// tx = session.beginTransaction();
		//
		// Criteria criteria = session.createCriteria(GameSaver.class);
		// criteria.add(Restrictions.eq("id", player.getId()));
		// List<?> list = criteria.list();
		// if (!list.isEmpty()) {
		// result = ((HibernatePlayer) list.get(0)).loadPlayer(injector);
		// } else {
		// criteria = session.createCriteria(GameSaver.class);
		// criteria.add(Restrictions.eq("id", player.getId()));
		// list = criteria.list();
		// if (!list.isEmpty()) {
		// result = ((HibernatePlayer) list.get(0)).loadPlayer(injector);
		// }
		// }
		// } catch (HibernateException ex) {
		// handleHibernateException(ex, tx);
		// }
		return result;
	}

	@Override
	public List<IGamePlayer> listAllPlayers() {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HibernatePlayer.class);

		@SuppressWarnings("unchecked")
		List<IPersistentPlayer> results = criteria.list();

		List<IGamePlayer> players = new ArrayList<>();
		for (IPersistentPlayer persistentPlayer : results) {
			IGamePlayer player = copyPlayer(persistentPlayer);
			players.add(player);
		}
		return players;
	}

	@Override
	public boolean containsPlayerById(int id) {
		if (getPlayerById(id) != null) {
			return true;
		}
		return false;
	}

	@Override
	public IGamePlayer getPlayerById(int id) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		return copyPlayer((HibernatePlayer) session.get(HibernatePlayer.class, id));
	}

	@Override
	public List<IGamePlayer> getPlayersByName(String name) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HibernatePlayer.class);
		
		@SuppressWarnings("unchecked")
		List<IPersistentPlayer> results = criteria.list();

		List<IGamePlayer> players = new ArrayList<>();
		for (IPersistentPlayer p : results) {
			IGamePlayer player = copyPlayer(p);
			if(p.getName().equals(player.getName())) {
				players.add(player);
			}
		}
		return players;
	}
	
	private void handleHibernateException(HibernateException ex, Transaction tx) {
		if (tx != null) {
			try {
				tx.rollback();
			} catch (HibernateException e) {
				throw new RuntimeException("Exeption at Rollback:\n" + e.getMessage());
			}
			throw new RuntimeException(ex.getMessage());
		}
	}
}
