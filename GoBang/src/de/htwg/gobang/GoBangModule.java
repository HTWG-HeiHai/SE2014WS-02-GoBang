package de.htwg.gobang;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.dao.couchdb.CouchDbPlayerDao;
import de.htwg.gobang.persistence.IPersistentResult;
import de.htwg.gobang.persistence.couchdb.CouchDbResult;


public class GoBangModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGbLogic.class).to(de.htwg.gobang.controller.impl.GbLogic.class).in(Singleton.class);
//		bind(IPlayerDao.class).to(HibernatePlayerDao.class);
		bind(IPlayerDao.class).to(CouchDbPlayerDao.class);
		bind(IPersistentResult.class).to(CouchDbResult.class);
		
//		bind(IGameDao.class).to(HibernateGameDao.class);
//        bind(IGameSaver.class).to(HibernateGameSaver.class);
//        bind(IPersistentPlayer.class).to(HibernatePlayer.class);
	}
}
