package de.htwg.gobang.entities;

abstract class Checker {

	protected static int counter = 1;
	protected static int tend = 0;
	protected static int win = 0;
	private Checker next;
	
	private void resetCounter() {
		counter = 1;
		tend = 0;
	}
	
	protected void setNext(Checker nChecker) {
		next = nChecker;
	}
	public void getWin(int x, int y, GameToken token) {
		checkWin(x, y, token);
		if (tend == 1 && counter >= GameField.TOKENTOWIN) {
			win = 1;
			return;
		}
		else if (tend == 1) {
			resetCounter();
		}
		if (next != null){
			next.getWin(x, y, token);
		}
	}
	
	protected int checkStep(int x, int y, GameToken token)
	{
		if (GameField.isValid(x, y) && GameField.matrix[x][y] == token) {
			return 1;
		}
		return 0;
	}
	
	protected abstract void checkWin(int x, int y, GameToken token);

}