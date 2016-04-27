package de.htwg.gobang.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.gobang.persistence.IPersistentPlayer;
import de.htwg.gobang.persistence.IPersistentResult;

public class PersistentPlayer implements IPersistentPlayer {

	private int id;
	private String name;
	private int wins;
	private int losses;
	private List<IPersistentResult> results;

//	public PersistentPlayer(String pName) {
//		name = pName;
//		wins = 0;
//		losses = 0;
//		results = new ArrayList<>();
//		id = (new Random()).nextInt(Integer.MAX_VALUE - 1) + 1;
//	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getWinsTotal() {
		return wins;
	}

	@Override
	public int getLossesTotal() {
		return losses;
	}

	@Override
	public List<IPersistentResult> getResults() {
		return results;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setResults(List<IPersistentResult> enemies) {
		this.results = (ArrayList<IPersistentResult>) enemies;
	}

	@Override
	public void setWinsTotal(int wins) {
		this.wins = wins;
	}

	@Override
	public void setLossesTotal(int losses) {
		this.losses = losses;
	}
}
