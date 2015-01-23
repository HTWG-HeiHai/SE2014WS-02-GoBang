package de.htwg.gobang.controller;

import de.htwg.gobang.entities.GameField;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.observer.MyObserverable;

public class GbLogic extends MyObserverable {
	
	private GameField myField;
	private GameToken player1;
	private GameToken player2;
	private GameToken cPlayer;
	private int lastX;
	private int lastY;
	private int counter;
	private char status;
	
	public GbLogic(GameToken p1, GameToken p2){
		myField = new GameField();
		player1 = p1;
		player2 = p2;
		counter = 0;
		cPlayer = player1;
		lastX = 1;
		lastY = 1;
	}
	
	public char setToken(int x, int y)
	{
		status = myField.putStone(x, y, cPlayer);
		if (status == 'f' || status == 'b')
		{
			return status;
		}
		counter++;
		changePlayer(counter);
		lastX = x;
		lastY = y;
		notifyObserver(status, x, y);
		return status;
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
	
	public void reset(){
		myField.reset();
	}
	
	public GameToken getPlayer1(){
		return player1;
	}
	
	public GameToken getPlayer2(){
		return player2;
	}
}
