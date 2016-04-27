package de.htwg.gobang.persistence.couchdb;

import org.ektorp.support.TypeDiscriminator;

import de.htwg.gobang.persistence.IPersistentResult;

public class CouchDbResult implements IPersistentResult {

	@TypeDiscriminator
	private int id;
	private int wins;
	private int losses;


	public CouchDbResult() {
		
	}

	public CouchDbResult(int id, int wins, int losses) {
		this.id = id;
		this.wins = wins;
		this.losses = losses;
	}

	@Override
	public int getEnemyId() {
		return id;
	}

	@Override
	public void setEnemyId(int id) {
		this.id = id;
	}

	@Override
	public int getWins() {
		return wins;
	}

	@Override
	public void setWins(int wins) {
		this.wins = wins;
	}

	@Override
	public int getLosses() {
		return losses;
	}

	@Override
	public void setLosses(int losses) {
		this.losses = losses;
	}

}
