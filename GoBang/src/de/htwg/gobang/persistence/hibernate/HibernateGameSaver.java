package de.htwg.gobang.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.persistence.IGameSaver;

@Entity
@Table(name = "game")
public class HibernateGameSaver implements IGameSaver, Serializable {

	private static final long serialVersionUID = 4897937764693014224L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "player1")
	private IGamePlayer player1;

	@Column(name = "player2")
	private IGamePlayer player2;

	@Column(name = "cPlayer")
	private IGamePlayer cPlayer;

	@Column(name = "myField")
	private IGameField myField;

	@Column(name = "status")
	private char status;

	public HibernateGameSaver() {
	}

	public HibernateGameSaver(IGbLogic controller) {
		this.saveGame(controller);
	}

	@Override
	public IGamePlayer getPlayer1() {
		return player1;
	}

	@Override
	public void setPlayer1(IGamePlayer player1) {
		this.player1 = player1;
	}

	@Override
	public IGamePlayer getPlayer2() {
		return player2;
	}

	@Override
	public void setPlayer2(IGamePlayer player2) {
		this.player2 = player2;
	}

	@Override
	public char getStatus() {
		return status;
	}

	@Override
	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public IGamePlayer getCurrentPlayer() {
		return cPlayer;
	}

	@Override
	public void setCurrentPlayer(IGamePlayer player) {
		this.cPlayer = player;
	}

	@Override
	public IGameField getGameField() {
		return myField;
	}

	@Override
	public void setGameField(IGameField field) {
		this.myField = field;
	}

	@Override
	public IGbLogic restoreGame(Injector injector) {
		IGbLogic controller = injector.getInstance(IGbLogic.class);
		controller.restoreGame(this);
		return controller;
	}

	@Override
	public void saveGame(IGbLogic controller) {
		this.player1 = controller.getPlayer1();
		this.player2 = controller.getPlayer2();
		this.cPlayer = controller.getcPlayer();
		this.status = controller.getStatus();
		this.myField = controller.getGameField();
	}

}
