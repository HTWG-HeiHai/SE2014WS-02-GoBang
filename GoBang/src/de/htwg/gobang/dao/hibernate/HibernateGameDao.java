package de.htwg.gobang.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IGameDao;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.game.GoBangModule;
import de.htwg.gobang.persistence.IGameSaver;
import de.htwg.gobang.persistence.hibernate.HibernateGameSaver;
import de.htwg.gobang.persistence.hibernate.HibernateUtil;
import de.htwg.gobang.persistence.impl.GameSaver;

public class HibernateGameDao implements IGameDao {

    private final SessionFactory sessionFactory;
    private final Injector injector;

    public HibernateGameDao() {
        sessionFactory = HibernateUtil.getInstance();
        injector = Guice.createInjector(new GoBangModule());
    }

    @Override
    public void saveOrUpdateGame(IGbLogic controller) {
    	Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
//            IGameSaver save = injector.getInstance(IGameSaver.class);
            HibernateGameSaver save = new HibernateGameSaver();
            save.saveGame(controller);
            session.save(save);
            tx.commit();
        } catch (HibernateException ex) {
        	System.out.println(ex + "asdfasdfasdfas");
            handleHibernateException(ex, tx);
        }
    }

    @Override
    public boolean isGameExisting(IGamePlayer player1, IGamePlayer player2) {
        Transaction tx = null;
        boolean contains = false;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(GameSaver.class);
            List<?> list = criteria.list();

            for (Object o : list) {
                GameSaver gameSaver = (GameSaver) o;
                if (gameSaver.getPlayer1().equals(player1) &&
                    gameSaver.getPlayer2().equals(player2) ||
                    gameSaver.getPlayer1().equals(player2) &&
                    gameSaver.getPlayer2().equals(player1)) {
                    contains = true;
                    break;
                }
            }
            tx.commit();
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return contains;
    }

    @Override
    public IGbLogic loadGame(IGamePlayer player1, IGamePlayer player2) {

    	IGbLogic result = null;
        Transaction tx = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(GameSaver.class);
            criteria.add(Restrictions.eq("player1ID", player1.getId()))
                    .add(Restrictions.eq("player2ID", player2.getId()));
            List<?> list = criteria.list();
            if (!list.isEmpty()) {
                result =
                    ((HibernateGameSaver) list.get(0)).restoreGame(injector);
            } else {
                criteria = session.createCriteria(GameSaver.class);
                criteria.add(Restrictions.eq("player1ID", player2.getId()))
                        .add(Restrictions.eq("player2ID", player1.getId()));
                list = criteria.list();
                if (!list.isEmpty()) {
                    result =
                        ((HibernateGameSaver) list.get(0)).restoreGame(injector);
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return result;
    }

    @Override
    public List<IGbLogic> listAllGames(IGamePlayer player) {
        Transaction tx = null;
        List<IGbLogic> list = new LinkedList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            int queryPlayer = player.getId();

            Criteria criteria = session.createCriteria(HibernateGameSaver.class);
            List<?> list1 = criteria.list();
            for (Object o : list1) {
                HibernateGameSaver gs = (HibernateGameSaver) o;
                if (gs.getPlayer1().getId() == queryPlayer ||
                    gs.getPlayer2().getId() == queryPlayer) {
                    list.add(gs.restoreGame(injector));
                }
            }
        } catch (HibernateException ex) {
            handleHibernateException(ex, tx);
        }
        return list;
    }

    private void handleHibernateException(HibernateException ex,
                                          Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(
                    "Exeption at Rollback:\n" + e.getMessage());
            }
            throw new RuntimeException(ex.getMessage());
        }
    }
}
