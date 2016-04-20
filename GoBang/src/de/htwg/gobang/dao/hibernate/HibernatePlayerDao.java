package de.htwg.gobang.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.game.GoBangModule;
import de.htwg.gobang.persistence.hibernate.HibernatePlayer;
import de.htwg.gobang.persistence.hibernate.HibernateUtil;
import de.htwg.gobang.persistence.impl.GameSaver;

public class HibernatePlayerDao implements IPlayerDao {

	private final SessionFactory sessionFactory;
	private final Injector injector;

	public HibernatePlayerDao() {
		sessionFactory = HibernateUtil.getInstance();
		injector = Guice.createInjector(new GoBangModule());
	}

	@Override
	public void saveOrUpdatePlayer(IGamePlayer player) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			// IGameSaver save = injector.getInstance(IGameSaver.class);
			HibernatePlayer save = new HibernatePlayer();
			save.savePlayer(player);
			session.save(save);
			tx.commit();
		} catch (HibernateException ex) {
			System.out.println(ex + "asdfasdfasdfas");
			handleHibernateException(ex, tx);
		}
	}

	@Override
	public IGamePlayer loadPlayer(IGamePlayer player) {

    	IGamePlayer result = null;
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(GameSaver.class);
            criteria.add(Restrictions.eq("id", player.getId()));
            List<?> list = criteria.list();
            if (!list.isEmpty()) {
                result =
                    ((HibernatePlayer) list.get(0)).loadPlayer(injector);
            } else {
                criteria = session.createCriteria(GameSaver.class);
                criteria.add(Restrictions.eq("id", player.getId()));
                list = criteria.list();
                if (!list.isEmpty()) {
                    result =
                        ((HibernatePlayer) list.get(0)).loadPlayer(injector);
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return result;
	}

	@Override
	public List<IGamePlayer> listAllPlayers() {
		// TODO Auto-generated method stub
		return null;
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
