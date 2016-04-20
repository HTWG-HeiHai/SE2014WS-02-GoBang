package de.htwg.gobang.persistence.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.inject.Injector;

import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.persistence.IPersistentPlayer;

@Entity
@Table(name = "player")
public class HibernatePlayer implements IPersistentPlayer, Serializable {

	private static final long serialVersionUID = 2099727281780128347L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column( name="name")
	private String name;
	
	@Column( name="wins")
	private int wins;
	
	@Column( name="losses")
	private int losses;
	
	@Column( name="enemies")
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
		List l = new ArrayList();//http://stackoverflow.com/questions/3774198/org-hibernate-mappingexception-could-not-determine-type-for-java-util-list-at
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
