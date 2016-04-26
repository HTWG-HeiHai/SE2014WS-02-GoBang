package de.htwg.gobang.persistence.couchdb;

import java.io.Serializable;
import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.gobang.persistence.IPersistentPlayer;

public class CouchDbPlayer extends CouchDbDocument  implements IPersistentPlayer, Serializable  {

	private static final long serialVersionUID = 9111095847125696937L;

	@TypeDiscriminator
	private String id;

	private String name;

	private int wins;

	private int losses;

	private List<Integer> enemies;

	public CouchDbPlayer() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
