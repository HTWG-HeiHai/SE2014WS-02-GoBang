package de.htwg.gobang.persistence.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import de.htwg.gobang.persistence.IPersistentPlayer;
import de.htwg.gobang.persistence.IPersistentResult;

@Entity
@Table(name = "player")
public class HibernatePlayer implements IPersistentPlayer, Serializable {

	private static final long serialVersionUID = 2099727281780128347L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column( name="name")
	private String name;
	
	@Column( name="wins")
	private int wins;
	
	@Column( name="losses")
	private int losses;
	
	@ElementCollection
	@JoinColumn(name="results")//
	private List<IPersistentResult> results;
	
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
