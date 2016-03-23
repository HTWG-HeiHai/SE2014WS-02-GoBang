package de.htwg.gobang.controller.impl;

import de.htwg.gobang.controller.IChecker;
import de.htwg.gobang.entities.IGameToken;

public class Checker implements IChecker {

	private final int fieldsize;
	private IGameToken[][] matrix;

	public Checker(IGameToken[][] matrix) {
		this.matrix = matrix;
		fieldsize = matrix.length;
	}

	public void setMatrix(IGameToken[][] pMatrix) {
		matrix = pMatrix;
	}

	public boolean checkWin(int x, int y, IGameToken token) {
		if (checkDirection(x, 0, 0, y, -1, 1, token) || checkDirection(x, -1, 1, y, 0, 0, token)
				|| checkDirection(x, -1, 1, y, -1, 1, token) || checkDirection(x, 1, -1, y, -1, 1, token)) {
			return true;
		}
		return false;
	}

	public boolean checkDirection(int x, int dX, int dX2, int y, int dY, int dY2, IGameToken token) {
		if (checkWay(x, dX, y, dY, token) + checkWay(x, dX2, y, dY2, token) >= 4) {
			return true;
		}
		return false;
	}

	public int checkWay(int x, int dX, int y, int dY, IGameToken token) {
		int tmpCounter = 0;
		if (checkStep(x + dX, y + dY, token)) {
			tmpCounter++;
			tmpCounter += checkWay(x+dX, dX, y+dY, dY, token);
		}
		return tmpCounter;
	}

	public boolean checkStep(int x, int y, IGameToken token) {
		if (isValid(x, y) && matrix[x][y].getName().equals(token.getName())) {
			return true;
		}
		return false;
	}

	public boolean isValid(int x, int y) {

		if (x >= fieldsize || x < 0 || y >= fieldsize || y < 0) {
			return false;
		}
		return true;
	}
}
