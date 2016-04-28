package de.htwg.gobang.controller;

import de.htwg.gobang.model.IToken;

public interface IChecker {

	void setMatrix(IToken[][] pMatrix);
	
	boolean checkWin(int x, int y, IToken token);

	boolean checkDirection(int x, int dX, int dX2, int y, int dY, int dY2, IToken token);

	int checkWay(int x, int dX, int y, int dY, IToken token);

	boolean checkStep(int x, int y, IToken token);

	boolean isValid(int x, int y);
}
