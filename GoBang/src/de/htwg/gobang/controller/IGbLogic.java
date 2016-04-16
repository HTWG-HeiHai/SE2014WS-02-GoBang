package de.htwg.gobang.controller;

import java.awt.Color;

import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.observer.IObservable;
import de.htwg.gobang.persistence.IGameSaver;

public interface IGbLogic extends IObservable {

	void newGame(boolean pStartplayer);

	char setToken(int x, int y);
	
	void changePlayer(int counter);
	
	IGamePlayer getcPlayer();
	
	IGameToken[][] getField();
	
	boolean removeToken();
	
	IGamePlayer getPlayer1();
	
	IGamePlayer getPlayer2();
	
	Color getColor1();
	
	Color getColor2();
	
	int getWinPlayer1();
	
	int getWinPlayer2();

	char getStatus();

	Color getcColor();

	void restoreGame(IGameSaver saver);
}
