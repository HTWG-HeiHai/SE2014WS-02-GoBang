package de.htwg.gobang.persistence;

import java.util.List;

public interface IPersistentPlayer {

	// int getId();
	//
	// void setId(int id);

	String getName();

	void setName(String name);

	int getWinsTotal();

	int getLossesTotal();

	void setWinsTotal(int wins);
	
	void setLossesTotal(int losses);

	List<IPersistentResult> getResults();

	void setResults(List<IPersistentResult> enemies);

}
