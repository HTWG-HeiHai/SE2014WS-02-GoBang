package de.htwg.gobang.model;

public interface IField {

	int getFieldSize();

	IToken[][] getGameField();

	char putStone(int x, int y);

	char removeToken(int x, int y);

}
