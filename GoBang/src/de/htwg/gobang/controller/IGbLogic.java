package de.htwg.gobang.controller;

import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.observer.IObservable;

public interface IGbLogic extends IObservable {

	void newGame(boolean pStartplayer);

	char setToken(int x, int y);
	
	void changePlayer(int counter);
	
	int getCounter();
	
	IGameToken getcPlayer();
	
	IGameToken[][] getField();
	
	boolean removeToken();
	
	IGameToken getPlayer1();
	
	IGameToken getPlayer2();
}
