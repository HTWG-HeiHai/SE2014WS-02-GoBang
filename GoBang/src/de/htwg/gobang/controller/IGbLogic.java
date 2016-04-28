package de.htwg.gobang.controller;

import java.awt.Color;

import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.persistence.IGameSaver;
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

	void restoreGame(IGameSaver saver);
	
	IField getGameField();
}
