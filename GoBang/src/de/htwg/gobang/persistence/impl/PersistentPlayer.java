package de.htwg.gobang.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Injector;

import de.htwg.gobang.entities.IGamePlayer;
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
	public void addWin(int id) {
		addEnemy(id);
		wins++;
	}

	@Override
	public int getLosses() {
		return losses;
	}

	@Override
	public void addLoss(int id) {
		addEnemy(id);
		losses++;
	}

	@Override
	public List getEnemies() {
		return enemies;
	}

	private void addEnemy(int id) {
		if (!enemies.contains(id)) {
			enemies.add(id);
		}
	}

	@Override
	public void savePlayer(IGamePlayer player) {
		this.id = player.getId();
		this.name = player.getName();
		this.wins = player.getWins();
		this.losses = player.getLosses();
		List l = new ArrayList();
		for(IGamePlayer p : player.getEnemies()) {
			l.add(p.getId());
		}
		this.enemies = l;
	}

	@Override
	public IGamePlayer loadPlayer(Injector injector) {
		IGamePlayer player = injector.getInstance(IGamePlayer.class);
		player.load(this);
		return player;
	}
}
