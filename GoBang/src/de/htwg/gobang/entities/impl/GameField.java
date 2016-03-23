package de.htwg.gobang.entities.impl;

import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGameToken;

public class GameField implements IGameField {

	final static int TOKENTOWIN = 5;
	private final static int FIELDSIZE = 19;

	private IGameToken[][] matrix;

	public GameField() {
		matrix = new GameToken[FIELDSIZE][FIELDSIZE];
		for(int i = 0; i < FIELDSIZE; i++) {
			for(int j = 0; j < FIELDSIZE; j++) {
				matrix[i][j] = new GameToken();
			}
		}
	}

	public int getFieldSize() {
		return FIELDSIZE;
	}

	public IGameToken[][] getGameField() {
		return matrix;
	}

	public char putStone(int x, int y, IGameToken token) {

		if (!"none".equals(matrix[x][y].getName())) {
			return 'b';
		}
		this.matrix[x][y] = token;
		return 'e';
	}

	public char removeToken(int x, int y) {

		if (!"none".equals(matrix[x][y].getName())) {
			return 'f';
		}
		matrix[x][y] = new GameToken();
		return 'r';
	}
}