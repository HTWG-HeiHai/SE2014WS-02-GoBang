package de.htwg.gobang.model.impl;

import com.google.inject.Inject;

import de.htwg.gobang.model.IResult;

public class Result implements IResult {

	private int id;
	private int wins;
	private int losses;

	@Inject
	public Result() {
		this(0, 0, 0);
	}

	public Result(int id, int wins, int losses) {
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
