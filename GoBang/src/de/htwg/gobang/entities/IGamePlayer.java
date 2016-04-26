package de.htwg.gobang.entities;

import java.util.List;

public interface IGamePlayer {
	int getId();
	void setId(int id);
	String getName();
	void setName(String name);
	int getWins();
	void addWin(IGamePlayer pPlayer);
	int getLosses();
	void addLoss(IGamePlayer pPlayer);
	List<Integer> getEnemies();
	void setEnemies(List<Integer> enemies);
}
