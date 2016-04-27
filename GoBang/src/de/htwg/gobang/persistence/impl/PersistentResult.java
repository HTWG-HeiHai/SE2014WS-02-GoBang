package de.htwg.gobang.persistence.impl;

import de.htwg.gobang.persistence.IPersistentResult;

public class PersistentResult implements IPersistentResult{

	private int id;
	private int wins;
	private int losses;

	public PersistentResult(int id, int wins, int losses) {
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
