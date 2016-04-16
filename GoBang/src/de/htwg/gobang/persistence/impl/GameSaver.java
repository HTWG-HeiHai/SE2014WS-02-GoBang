package de.htwg.gobang.persistence.impl;

import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.persistence.IGameSaver;


public class GameSaver implements IGameSaver {

	private IGamePlayer player1;
	private IGamePlayer player2;
	private IGamePlayer cPlayer;
	private IGameField myField;
	private char status;

	public GameSaver() {
	}

	public GameSaver(IGbLogic controller) {
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
//		this.myField = controller.getField().;
	}

}

