package de.htwg.gobang.entities.impl;

import de.htwg.gobang.entities.IGamePlayer;

public class GamePlayer implements IGamePlayer {

	String name;
	int wins;
	int losses;
	GamePlayer(String pName){
		name = pName;
		wins = 0;
		losses = 0;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getWins() {
		return wins;
	}

	@Override
	public void addWin() {
		wins++;
	}

	@Override
	public int getLosses() {
		return losses;
	}

	@Override
	public void addLoss() {
		losses++;
	}

}
