package de.htwg.gobang.persistence;

import java.util.List;

public interface IPersistentPlayer {

	int getId();

	void setId(int id);

	String getName();

	void setName(String name);

	int getWins();

	void setWins(int wins);

	int getLosses();

	void setLosses(int losses);

	List<String> getEnemies();
	
	void setEnemies(List<String> enemies);
}
