package de.htwg.gobang.controller.impl;

import java.awt.Color;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.gobang.controller.IChecker;
import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.entities.impl.GameField;
import de.htwg.gobang.entities.impl.GameToken;
import de.htwg.gobang.observer.MyObserverable;

@Singleton
public class GbLogic extends MyObserverable implements IGbLogic {

	private IGameField myField;
	private IGameToken player1;
	private IGameToken player2;
	private IGameToken cPlayer;
	private IChecker myChecker;
	private int lastX;
	private int lastY;
	private int cWinPlayer1;
	private int cWinPlayer2;
	private int counter;
	private char status;

	@Inject
	public GbLogic() {
		this(true);
	}

	public GbLogic(boolean pStartplayer) {
		newGame(pStartplayer);
		cWinPlayer1 = 0;
		cWinPlayer2 = 0;
	}

	public void newGame(boolean pStartplayer) {
		myField = new GameField();
		player1 = new GameToken("black", Color.BLACK);
		player2 = new GameToken("blue", Color.BLUE);
		myChecker = new Checker(myField.getGameField());
		counter = 0;
		if (pStartplayer) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
			counter++;
		}
		lastX = 1;
		lastY = 1;
	}

	public char setToken(int x, int y) {
		status = myField.putStone(x, y, cPlayer);
		if (status == 'f' || status == 'b') {
			return status;
		}
		counter++;
		lastX = x;
		lastY = y;
		notifyObservers(status, cPlayer);
		return getWin(x, y, cPlayer);
	}

	public void changePlayer(int counter) {
		if (counter % 2 == 0) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
		}
	}

	public int getCounter() {
		return counter;
	}

	public IGameToken getcPlayer() {
		return cPlayer;
	}
	
	public int getWinPlayer1(){
		return cWinPlayer1;
	}
	
	public int getWinPlayer2(){
		return cWinPlayer2;
	}

	public boolean removeToken() {
		status = myField.removeToken(lastX, lastY);
		if (status == 'f') {
			return false;
		}
		counter--;
		notifyObservers(status, cPlayer);
		changePlayer(counter);
		return true;
	}

	public IGameToken getPlayer1() {
		return player1;
	}

	public IGameToken getPlayer2() {
		return player2;
	}

	private char getWin(int x, int y, IGameToken token) {
		if (myChecker.checkWin(x, y, token))
		{ 
			if (token.equals(player1)) {
				cWinPlayer1 += 1;
			} else {
				cWinPlayer2 += 1;
			}
			return 'g';
		}
		return 'e';
	}

	@Override
	public IGameToken[][] getField() {
		return myField.getGameField();
	}
}
