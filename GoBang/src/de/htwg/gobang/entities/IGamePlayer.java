package de.htwg.gobang.entities;

public interface IGamePlayer {
	int getId();
	void setId(int id);
	String getName();
	void setName(String name);
	int getWins();
	void addWin(IGamePlayer pPlayer);
	int getLosses();
	void addLoss(IGamePlayer pPlayer);
	int getEnemyCounter();
}
