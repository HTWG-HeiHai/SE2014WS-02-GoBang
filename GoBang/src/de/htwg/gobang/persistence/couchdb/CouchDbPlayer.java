package de.htwg.gobang.persistence.couchdb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import de.htwg.gobang.persistence.IPersistentPlayer;
import de.htwg.gobang.persistence.IPersistentResult;

public class CouchDbPlayer extends CouchDbDocument  implements IPersistentPlayer, Serializable  {

	private static final long serialVersionUID = 9111095847125696937L;

	@TypeDiscriminator
	private String id;

	private String name;

	private int wins;

	private int losses;

	private List<IPersistentResult> results;

	public CouchDbPlayer() {
		
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
	public int getLossesTotal() {
		return losses;
	}

	@Override
	public List<IPersistentResult> getResults() {
		return results;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
