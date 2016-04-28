package de.htwg.gobang.model.impl;

import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IToken;

public class Field implements IField {

	final static int TOKENTOWIN = 5;
	private final static int FIELDSIZE = 19;

	private IToken[][] matrix;

	public Field() {
		matrix = new Token[FIELDSIZE][FIELDSIZE];
		for(int i = 0; i < FIELDSIZE; i++) {
			for(int j = 0; j < FIELDSIZE; j++) {
				matrix[i][j] = new Token();
			}
		}
	}

	public int getFieldSize() {
		return FIELDSIZE;
	}

	public IToken[][] getGameField() {
		return matrix;
	}

	public char putStone(int x, int y) {

		if (!"none".equals(matrix[x][y].getName())) {
			return 'b';
		}
		return 'e';
	}

	public char removeToken(int x, int y) {

		if ("none".equals(matrix[x][y].getName())) {
			return 'f';
		}
		matrix[x][y].setName("none");
		matrix[x][y].setColor(null);
		return 'r';
	}
}