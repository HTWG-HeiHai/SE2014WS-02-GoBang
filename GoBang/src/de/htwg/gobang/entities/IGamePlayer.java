package de.htwg.gobang.entities;

public interface IGamePlayer {
	String getName();
	int getWins();
	void addWin(IGamePlayer pPlayer);
	int getLosses();
	void addLoss(IGamePlayer pPlayer);
	int getEnemyCounter();
}
