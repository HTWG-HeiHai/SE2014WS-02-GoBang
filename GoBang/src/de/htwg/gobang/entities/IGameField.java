package de.htwg.gobang.entities;

public interface IGameField {

	int getFieldSize();

	IGameToken[][] getGameField();

	char putStone(int x, int y, IGameToken token);

	char removeToken(int x, int y);

}
