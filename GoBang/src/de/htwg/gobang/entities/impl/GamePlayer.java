package de.htwg.gobang.entities.impl;

import java.util.ArrayList;
import java.util.Random;

import de.htwg.gobang.entities.IGamePlayer;

public class GamePlayer implements IGamePlayer {

	private int id;
	private String name;
	private int wins;
	private int losses;
	private ArrayList<IGamePlayer> enemies;
	
	public GamePlayer(String pName){
		name = pName;
		wins = 0;
		losses = 0;
		enemies = new ArrayList<>();
		id = (new Random()).nextInt(Integer.MAX_VALUE - 1) + 1;
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
	public void addWin(IGamePlayer pPlayer) {
		if (!enemies.contains(pPlayer)) {
			enemies.add(pPlayer);
		}
		wins++;
	}

	@Override
	public int getLosses() {
		
		return losses;
	}

	@Override
	public void addLoss(IGamePlayer pPlayer) {
		if (!enemies.contains(pPlayer)) {
			enemies.add(pPlayer);
		}
		losses++;
	}

	@Override
	public int getEnemyCounter() {
		return enemies.size();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
