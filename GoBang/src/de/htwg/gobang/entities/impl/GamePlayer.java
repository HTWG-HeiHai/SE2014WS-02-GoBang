package de.htwg.gobang.entities.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.entities.IResult;

public class GamePlayer implements IGamePlayer {

	private int id;
	private String name;
	private int wins;
	private int losses;
	private List<IResult> results;

	public GamePlayer(String pName) {
		name = pName;
		wins = 0;
		losses = 0;
		results = new ArrayList<>();
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
	public int getWinsTotal() {
		return wins;
	}

	@Override
	public int getWinsAgainst(int id) {
		for(IResult r : results) {
			if(r.getEnemyId() == id) {
				return r.getWins();
			}
		}
		return 0;
	}

	@Override
	public void addWinAgainst(int id) {
		for (IResult e : results) {
			if (e.getEnemyId() == id) {
				e.setWins(e.getWins() + 1);
				wins++;
				return;
			}
		}
		results.add(new Result(id, 1, 0));
		wins++;
	}

	@Override
	public int getLossesTotal() {
		return losses;
	}

	@Override
	public int getLossesAgainst(int id) {
		for(IResult r : results) {
			if(r.getEnemyId() == id) {
				return r.getLosses();
			}
		}
		return 0;
	}

	@Override
	public void addLossAgainst(int id) {
		for (IResult res : results) {
			if (res.getEnemyId() == id) {
				res.setLosses(res.getLosses() + 1);
				losses++;
				return;
			}
		}
		results.add(new Result(id, 0, 1));
		losses++;
	}

	@Override
	public List<IResult> getResults() {
		return results;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setResults(List<IResult> enemies) {
		this.results = (ArrayList<IResult>) enemies;
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
