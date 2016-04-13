package de.htwg.gobang.entities.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.annotations.Entity;

import de.htwg.gobang.entities.IGamePlayer;

@Entity
public class GamePlayer implements IGamePlayer, Serializable {

	private static final long serialVersionUID = 2783195758195385831L;
	
	private String name;
	private int wins;
	private int losses;
	private ArrayList<IGamePlayer> enemies;
	
	public GamePlayer(String pName){
		name = pName;
		wins = 0;
		losses = 0;
		enemies = new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return name;
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

}
