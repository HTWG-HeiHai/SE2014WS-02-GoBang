package de.htwg.gobang.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IGameDao;
import de.htwg.gobang.dao.hibernate.HibernateGameDao;
import de.htwg.gobang.persistence.IGameSaver;
import de.htwg.gobang.persistence.hibernate.HibernateGameSaver;


public class GoBangModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGbLogic.class).to(de.htwg.gobang.controller.impl.GbLogic.class).in(Singleton.class);
		bind(IGameDao.class).to(HibernateGameDao.class);
        bind(IGameSaver.class).to(HibernateGameSaver.class);
	}
}
