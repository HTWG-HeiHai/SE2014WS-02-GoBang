package de.htwg.gobang.persistence.impl;

import java.util.List;

import de.htwg.gobang.persistence.IPersistentPlayer;

public class PersistentPlayer implements IPersistentPlayer {

	private int id;
	private String name;
	private int wins;
	private int losses;
	private List<Integer> enemies;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getWins() {
		return wins;
	}

	@Override
	public void setWins(int wins) {
		this.wins = wins;
	}

	@Override
	public int getLosses() {
		return losses;
	}

	@Override
	public void setLosses(int losses) {
		this.losses = losses;
	}

	@Override
	public List<Integer> getEnemies() {
		return enemies;
	}

	@Override
	public void setEnemies(List<Integer> enemies) {
		this.enemies = enemies;
	}
}
