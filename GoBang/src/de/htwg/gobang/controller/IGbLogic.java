package de.htwg.gobang.controller;

import java.awt.Color;

import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.util.observer.IObservable;

public interface IGbLogic extends IObservable {

	void newGame(boolean pStartplayer);

	char setToken(int x, int y);
	
	void changePlayer(int counter);
	
	IPlayer getcPlayer();
	
	IToken[][] getField();
	
	boolean removeToken();
	
	IPlayer getPlayer1();
	
	IPlayer getPlayer2();
	
	Color getColor1();
	
	Color getColor2();
	
	int getWinPlayer1();
	
	int getWinPlayer2();

	char getStatus();

	Color getcColor();

	IField getGameField();

	void setPlayer1(String name);

	void setPlayer2(String name);
}
