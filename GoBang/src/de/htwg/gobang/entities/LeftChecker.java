package de.htwg.gobang.entities;

class LeftChecker extends Checker {

	@Override
	protected void checkWin(int x, int y, GameToken token) {
		int ty = y - 1;
		if (checkStep(x, ty, token) == 1) {
			super.counter += 1;
			checkWin(x, ty, token);
		}
	}
}