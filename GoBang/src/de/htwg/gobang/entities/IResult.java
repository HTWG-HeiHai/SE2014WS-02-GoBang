package de.htwg.gobang.entities;

public interface IResult {

	int getEnemyId();

	void setEnemyId(int id);

	int getWins();

	void setWins(int wins);

	int getLosses();

	void setLosses(int losses);
}
