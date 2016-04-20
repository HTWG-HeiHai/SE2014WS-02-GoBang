package de.htwg.gobang.entities;

import java.util.List;

import de.htwg.gobang.persistence.IPersistentPlayer;

public interface IGamePlayer {
	int getId();
	void setId(int id);
	String getName();
	void setName(String name);
	int getWins();
	void addWin(IGamePlayer pPlayer);
	int getLosses();
	void addLoss(IGamePlayer pPlayer);
	List<IGamePlayer> getEnemies();
	void load(IPersistentPlayer persistentPlayer);
}
