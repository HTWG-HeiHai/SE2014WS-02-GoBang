package de.htwg.gobang.persistence;

import java.util.List;

import com.google.inject.Injector;

import de.htwg.gobang.entities.IGamePlayer;

public interface IPersistentPlayer {

	int getId();

	void setId(int id);

	String getName();

	void setName(String name);

	int getWins();

	void addWin(int id);

	int getLosses();

	void addLoss(int id);

	List getEnemies();
	
	void savePlayer(IGamePlayer player);

	IGamePlayer loadPlayer(Injector injector);
}
