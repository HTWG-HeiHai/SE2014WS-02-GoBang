package de.htwg.gobang.controller;

import de.htwg.gobang.entities.IGameToken;

public interface IChecker {

	void setMatrix(IGameToken[][] pMatrix);
	
	boolean checkWin(int x, int y, IGameToken token);

	boolean checkDirection(int x, int dX, int dX2, int y, int dY, int dY2, IGameToken token);

	int checkWay(int x, int dX, int y, int dY, IGameToken token);

	boolean checkStep(int x, int y, IGameToken token);

	boolean isValid(int x, int y);
}
