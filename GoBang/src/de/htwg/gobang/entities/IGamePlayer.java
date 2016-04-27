package de.htwg.gobang.entities;

import java.util.List;

public interface IGamePlayer {
	
	int getId();

	void setId(int id);

	String getName();

	void setName(String name);

	int getWinsAgainst(int id);

	void addWinAgainst(int id);

	int getLossesAgainst(int id);

	void addLossAgainst(int id);

	int getWinsTotal();

	int getLossesTotal();

	void setWinsTotal(int wins);
	
	void setLossesTotal(int losses);

	List<IResult> getResults();

	void setResults(List<IResult> enemies);
}
