package de.htwg.gobang.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        final AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
//        cfg.addAnnotatedClass(HibernateGameSaver.class);
        sessionFactory = cfg.buildSessionFactory();
    }

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
