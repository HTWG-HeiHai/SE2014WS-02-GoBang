package de.htwg.gobang.controller;

import java.awt.Color;

import de.htwg.gobang.entities.GameField;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.observer.MyObserverable;

public class GbLogic extends MyObserverable {
	
	private GameField myField;
	private GameToken player1;
	private GameToken player2;
	private GameToken cPlayer;
	private Checker myChecker;
	private int lastX;
	private int lastY;
	private int counter;
	private char status;
	
	public GbLogic(boolean pStartplayer){
		myField = new GameField();
		player1 = new GameToken("black", Color.BLACK);
		player2 = new GameToken("blue", Color.BLUE);
		myChecker = new Checker(myField.getFieldSize());
		counter = -1;
		if (pStartplayer) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
		}
		lastX = 1;
		lastY = 1;
	}
	
	public char setToken(int x, int y)
	{
		counter++;
		changePlayer(counter);
		status = myField.putStone(x, y, cPlayer);
		if (status == 'f' || status == 'b')
		{
			return status;
		}
		lastX = x;
		lastY = y;
		notifyObserver(status, x, y);
		
		return getWin(x, y, cPlayer);
	}
	
	private void changePlayer(int counter)
	{
		if (counter %2 == 0)
		{
			cPlayer = player1;
		} 
		else
		{
			cPlayer = player2;
		}
	}
	
	public int getCounter()
	{
		return counter;
	}
	
	public GameToken getcPlayer()
	{
		return cPlayer;
	}
	
	public boolean removeToken()
	{
		status = myField.removeToken(lastX, lastY);
		if (status == 'f')
		{
			return false;
		}
		counter--;
		changePlayer(counter);
		notifyObserver('r', 0, 0);
		return true;
	}
	
	public GameToken getPlayer1(){
		return player1;
	}
	
	public GameToken getPlayer2(){
		return player2;
	}
	
	private char getWin(int x, int y, GameToken token) {
		if (myChecker.checkWin(x, y, token))
		{ 
			return 'g';
		}
		return 'e';
	}
}
