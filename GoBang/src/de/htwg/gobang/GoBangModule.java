package de.htwg.gobang;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.dao.couchdb.CouchDbPlayerDao;
import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IResult;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.persistence.IPersistentPlayer;
import de.htwg.gobang.persistence.IPersistentResult;
import de.htwg.gobang.persistence.couchdb.CouchDbPlayer;
import de.htwg.gobang.persistence.couchdb.CouchDbResult;


public class GoBangModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGbLogic.class).to(de.htwg.gobang.controller.impl.ActorController.class).in(Singleton.class);
//		bind(IChecker.class).to(de.htwg.gobang.controller.impl.Checker.class);
		bind(IField.class).to(de.htwg.gobang.model.impl.Field.class);
		bind(IPlayer.class).to(de.htwg.gobang.model.impl.Player.class);
		bind(IToken.class).to(de.htwg.gobang.model.impl.Token.class);
		bind(IResult.class).to(de.htwg.gobang.model.impl.Result.class);
		bind(IPlayerDao.class).to(CouchDbPlayerDao.class);
		bind(IPersistentPlayer.class).to(CouchDbPlayer.class);
		bind(IPersistentResult.class).to(CouchDbResult.class);
	}
}
