package de.htwg.gobang.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import de.htwg.gobang.persistence.IPersistentPlayer;

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
	@JoinColumn(name="enemies_id")
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
