package de.htwg.gobang.dao.couchdb;

import java.util.List;

import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.entities.IGamePlayer;

public class CouchDbPlayerDao implements IPlayerDao {

	@Override
	public void saveOrUpdatePlayer(IGamePlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGamePlayer loadPlayer(IGamePlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IGamePlayer> listAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGamePlayer getPlayerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsPlayerById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IGamePlayer> getPlayersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
