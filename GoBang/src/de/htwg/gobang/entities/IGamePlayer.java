package de.htwg.gobang.entities;

public interface IGamePlayer {
		
	String getName();
	
	int getWins();
	void addWin();
	int getLosses();
	void addLoss();
	
}
