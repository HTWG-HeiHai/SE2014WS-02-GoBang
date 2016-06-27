package de.htwg.gobang.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.htwg.gobang.persistence.IPersistentResult;

@Entity
@Table(name = "results_new_version")
public class HibernateResult implements IPersistentResult, Serializable {

	private static final long serialVersionUID = -1130452671664393744L;

	@Id
	private int id;

	@Column(name = "wins")
	private int wins;

	@Column(name = "losses")
	private int losses;

	public HibernateResult() {
		
	}

	public HibernateResult(int id, int wins, int losses) {
		this.id = id;
		this.wins = wins;
		this.losses = losses;
	}

	@Override
	public int getEnemyId() {
		return id;
	}

	@Override
	public void setEnemyId(int id) {
		this.id = id;
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

}
