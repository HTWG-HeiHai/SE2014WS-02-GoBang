package de.htwg.gobang.dao.couchdb;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;

import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.entities.IResult;
import de.htwg.gobang.entities.impl.GamePlayer;
import de.htwg.gobang.entities.impl.Result;
import de.htwg.gobang.persistence.IPersistentResult;
import de.htwg.gobang.persistence.couchdb.CouchDbPlayer;
import de.htwg.gobang.persistence.couchdb.CouchDbResult;
import de.htwg.gobang.persistence.couchdb.CouchDbUtil;

public class CouchDbPlayerDao implements IPlayerDao {

	private final CouchDbConnector db;
	
	public CouchDbPlayerDao() {
		db = CouchDbUtil.getDB();
	}

	private IGamePlayer copyPlayer(CouchDbPlayer persistentPlayer) {
		if (persistentPlayer == null) {
			return null;
		}
		IGamePlayer player = new GamePlayer(persistentPlayer.getName());
		player.setId(Integer.parseInt(persistentPlayer.getId()));

		List<IResult> list = new ArrayList<>();
		for(IPersistentResult r : persistentPlayer.getResults()) {
			list.add(new Result(r.getEnemyId(), r.getWins(), r.getLosses()));
		}
		player.setResults(list);
		player.setWinsTotal(persistentPlayer.getWinsTotal());
		player.setLossesTotal(persistentPlayer.getLossesTotal());

		return player;
	}

	private CouchDbPlayer copyPlayer(IGamePlayer player) {
		if (player == null) {
			return null;
		}

		int playerId = player.getId();
		CouchDbPlayer persistentPlayer;
		if (containsPlayerById(playerId)) {
			// The Object already exists within the session
			persistentPlayer = (CouchDbPlayer) db.find(CouchDbPlayer.class, String.valueOf(playerId));
		} else {
			// A new database entry
			persistentPlayer = new CouchDbPlayer();
		}

		persistentPlayer.setId(String.valueOf(playerId));
		persistentPlayer.setName(player.getName());
		persistentPlayer.setWinsTotal(player.getWinsTotal());
		persistentPlayer.setLossesTotal(player.getLossesTotal());

//		System.out.println(player.getResults());

		List<IPersistentResult> list = new ArrayList<>();
		for(IResult r : player.getResults()) {
			list.add(new CouchDbResult(r.getEnemyId(), r.getWins(), r.getLosses()));
		}
		persistentPlayer.setResults(list);
		return persistentPlayer;
	}

	@Override
	public void saveOrUpdatePlayer(IGamePlayer player) {
		if (containsPlayerById(player.getId())) {
			db.update(copyPlayer(player));
		} else {
			db.create(String.valueOf(player.getId()), copyPlayer(player));
		}
	}

	@Override
	public IGamePlayer loadPlayer(IGamePlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IGamePlayer> listAllPlayers() {
		List<IGamePlayer> players = new ArrayList<>();
		ViewQuery query = new ViewQuery().allDocs();
		ViewResult vr = db.queryView(query);

		for (Row r : vr.getRows()) {
			players.add(getPlayerById(Integer.parseInt(r.getId())));
		}
		return players;
	}

	@Override
	public IGamePlayer getPlayerById(int id) {
		CouchDbPlayer p = db.find(CouchDbPlayer.class, String.valueOf(id));
		if (p == null) {
			return null;
		}
		return copyPlayer(p);
	}

	@Override
	public boolean containsPlayerById(int id) {
		if (getPlayerById(id) == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<IGamePlayer> getPlayersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
