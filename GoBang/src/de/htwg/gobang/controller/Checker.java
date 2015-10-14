package de.htwg.gobang.controller;

import de.htwg.gobang.entities.GameToken;

public class Checker {
	
	private final int FIELDSIZE;
	private GameToken[][] matrix;
	public Checker(int pSize){
		FIELDSIZE = pSize;
	}
	
	public void setMatrix(GameToken[][] pMatrix){
		matrix = pMatrix;
	}

	public boolean checkWin(int x, int y, GameToken token){
		if (checkDirection(x, 0, 0, y, -1, 1, token) || 
			checkDirection(x, -1, 1, y, 0, 0, token) ||
			checkDirection(x, -1, 1, y, -1, 1, token) ||
			checkDirection(x, 1, -1, y, 1, -1, token)){
			return true;
		}
		return false;
	}

	private boolean checkDirection(int x, int dX, int dX2, int y, int dY, int dY2, GameToken token){
		if (checkWay(x, dX, y, dY, token) + checkWay(x, dX2, y, dY2, token) >= 5) {
			return true;
		}
		return false;
	}

	private int checkWay(int x, int dX, int y, int dY, GameToken token) {
		int tmpCounter = 0;
		if (checkStep(x+dX, y+dY, token)){
			tmpCounter++;
			tmpCounter += checkWay(x,  dX,  y,  dY, token);
		}
		return tmpCounter;
	}

	private boolean checkStep(int x, int y, GameToken token)
	{
		if (isValid(x, y) && matrix[x][y].getName().equals(token.getName())) {
			return true;
		}
		return false;
	}

	private boolean isValid(int x, int y) {
		
		if(x >= FIELDSIZE  || x < 0 || y >= FIELDSIZE  || y < 0)
		{
			return false; 
		}
		return true;
	}
}
