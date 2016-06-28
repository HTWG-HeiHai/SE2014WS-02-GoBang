package de.htwg.gobang.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.inject.Inject;

import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IResult;
import de.htwg.gobang.model.impl.Player;
import de.htwg.gobang.model.impl.Result;
import de.htwg.gobang.persistence.IPersistentResult;
import de.htwg.gobang.persistence.hibernate.HibernatePlayer;
import de.htwg.gobang.persistence.hibernate.HibernateResult;
import de.htwg.gobang.util.HibernateUtil;

public class HibernatePlayerDao implements IPlayerDao {

	private final SessionFactory sessionFactory;
//	private final Injector injector;

	@Inject
	public HibernatePlayerDao() {
		sessionFactory = HibernateUtil.getInstance();
//		injector = Guice.createInjector(new GoBangModule());
	}

	private IPlayer copyPlayer(HibernatePlayer persistentPlayer) {
		if (persistentPlayer == null) {
			return null;
		}
		IPlayer player = new Player(persistentPlayer.getName());
		player.setId(persistentPlayer.getId());

		List<IResult> list = new ArrayList<>();
		for(IPersistentResult r : persistentPlayer.getResults()) {
			list.add(new Result(r.getEnemyId(), r.getWins(), r.getLosses()));
		}
		player.setResults(list);
		player.setWinsTotal(persistentPlayer.getWinsTotal());
		player.setLossesTotal(persistentPlayer.getLossesTotal());

		return player;
	}

	private HibernatePlayer copyPlayer(IPlayer player) {
		if (player == null) {
			return null;
		}

		int playerId = player.getId();
		HibernatePlayer persistentPlayer;
		if (containsPlayerById(playerId)) {
			// The Object already exists within the session
			Session session = HibernateUtil.getInstance().getCurrentSession();
			persistentPlayer = (HibernatePlayer) session.get(HibernatePlayer.class, playerId);
		} else {
			// A new database entry
			persistentPlayer = new HibernatePlayer();
			persistentPlayer.setId(playerId);
			persistentPlayer.setName(player.getName());
			persistentPlayer.setWinsTotal(player.getWinsTotal());
			persistentPlayer.setLossesTotal(player.getLossesTotal());
	
//			System.out.println(player.getResults());
	
			List<IPersistentResult> list = new ArrayList<>();
			for(IResult r : player.getResults()) {
				list.add(new HibernateResult(r.getEnemyId(), r.getWins(), r.getLosses()));
			}
			persistentPlayer.setResults(list);
		}
		System.out.println(persistentPlayer.getId() + " " + persistentPlayer.getName());

		return persistentPlayer;
	}

	@Override
	public void saveOrUpdatePlayer(IPlayer player) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
//			SQLQuery query = session.createSQLQuery("drop table player"); 
//			query.executeUpdate();
			HibernatePlayer persistentPlayer = copyPlayer(player);
			session.saveOrUpdate(persistentPlayer);
			for (IPersistentResult result : persistentPlayer.getResults()) {
				session.saveOrUpdate(result);
			}
			tx.commit();
		} catch (HibernateException ex) {
			handleHibernateException(ex, tx);
		}
	}

	@Override
	public IPlayer loadPlayer(IPlayer player) {

		IPlayer result = null;
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
	public List<IPlayer> listAllPlayers() {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HibernatePlayer.class);

		@SuppressWarnings("unchecked")
		List<HibernatePlayer> results = criteria.list();

		List<IPlayer> players = new ArrayList<>();
		for (HibernatePlayer persistentPlayer : results) {
			IPlayer player = copyPlayer(persistentPlayer);
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
	public IPlayer getPlayerById(int id) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		return copyPlayer((HibernatePlayer) session.get(HibernatePlayer.class, id));
	}

	@Override
	public List<IPlayer> getPlayersByName(String name) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HibernatePlayer.class, name);
		
		@SuppressWarnings("unchecked")
		List<HibernatePlayer> results = criteria.list();
		List<IPlayer> players = new ArrayList<>();
		for (HibernatePlayer p : results) {
			if(p.getName().equals(name)) {
				players.add(copyPlayer(p));
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
