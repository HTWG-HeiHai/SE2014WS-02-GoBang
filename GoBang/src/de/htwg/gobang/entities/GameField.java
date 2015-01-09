package de.htwg.gobang.entities;

public class GameField{
	
	private static final int TokenToWin = 5;
	private static final int FieldSize = 19;
	
	private static GameToken[][] matrix;
	
	public GameField(){
		matrix = new GameToken[FieldSize][FieldSize];
	}
	
	public int getFieldSize(){
		return FieldSize;
	}
	
	@SuppressWarnings("static-access")
	public char putStone(int x, int y, GameToken token){
		int tx = x-1;
		int ty = y-1;
		
		if(isValid(tx, ty))
		{
			if(matrix[tx][ty] != null)
			{
				return 'b'; 
			}
			
			this.matrix[tx][ty] = token;
			return getWin(tx, ty, token);
		
		}
		
		return 'f';
		}

	private static boolean isValid(int x, int y) {
		
		if(x >= FieldSize  || x < 0 || y >= FieldSize  || y < 0)
		{
			return false; 
		}
		
		return true;	
		
	}
	
	public char removeToken(int x, int y) {
		int tx = x-1;  
		int ty = y-1;
		if(isValid(x, y))
		{
			if (matrix[tx][ty] == null)
			{
				return 'f';
			}
			matrix[tx][ty] = null;
			return 'r';
		}
		
		return 'f';
	}

	@SuppressWarnings("static-access")
	private char getWin(int x, int y, GameToken token) {
		
		Checker myCheck = createChain();
		myCheck.getWin(x, y, token);
		if (myCheck.win == 1)
		{ 
			return 'g';
		}
		return 'e';
	}
	

	private static Checker createChain(){
		Checker myChecker = new LeftChecker();
		Checker myRCheck = new RightChecker();
		Checker myTCheck = new TopChecker();
		Checker myDCheck = new DownChecker();
		Checker myTLCheck = new TopLeftChecker();
		Checker myTRCheck = new TopRightChecker();
		Checker myDLCheck = new DownRightChecker();
		Checker myDRCheck = new DownLeftChecker();
		
		myChecker.setNext(myRCheck);
		myRCheck.setNext(myTCheck);
		myTCheck.setNext(myDCheck);
		myDCheck.setNext(myTLCheck);
		myTLCheck.setNext(myDRCheck);
		myDRCheck.setNext(myTRCheck);
		myTRCheck.setNext(myDLCheck);
		
		return myChecker;
	}

	abstract static class Checker {
	
		protected static int counter = 1;
		protected static int tend = 0;
		protected static int win = 0;
		private Checker next;
		
		public void resetCounter() {
			counter = 1;
		}
		
		public void setNext(Checker nChecker) {
			next = nChecker;
		}
		public void getWin(int x, int y, GameToken token) {
			tend = 0;
			checkWin(x, y, token);
			if (tend == 1 && counter >= TokenToWin) {
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
		
		public int checkStep(int x, int y, GameToken token)
		{
			if (isValid(x, y) && matrix[x][y] == token) {
				return 1;
			}
			return 0;
		}
		
		abstract protected void checkWin(int x, int y, GameToken token);
	
	}
	
	static class LeftChecker extends Checker {
	
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int ty = y - 1;
			if (checkStep(x, ty, token) == 1) {
				super.counter += 1;
				checkWin(x, ty, token);
			}
		}
	}
	
	static class RightChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int ty = y + 1;
			if (checkStep(x, ty, token) == 1) {
				super.counter += 1;
				checkWin(x, ty, token);
			}
		super.tend = 1;
		}
	}
	
	static class TopChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x - 1;
			if (checkStep(tx, y, token) == 1) {
				super.counter += 1;
				checkWin(tx, y, token);
			}
		}
	}
	
	static class DownChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x + 1;
			if (checkStep(tx, y, token) == 1) {
				super.counter += 1;
				checkWin(tx, y, token);
			}
		super.tend = 1;
		}
	}
	
	static class TopLeftChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x - 1;
			int ty = y + 1;
			if (checkStep(tx, ty, token) == 1) {
				super.counter += 1;
				checkWin(tx, ty, token);
			}
		}
	}
	
	static class DownRightChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x + 1;
			int ty = y - 1;
			if (checkStep(tx, ty, token) == 1) {
				super.counter += 1;
				checkWin(tx, ty, token);
			}
		super.tend = 1;
		}
	}
	
	static class TopRightChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x + 1;
			int ty = y + 1;
			if (checkStep(tx, ty, token) == 1) {
				super.counter += 1;
				checkWin(tx, y, token);
			}
		}
	}
	
	static class DownLeftChecker extends Checker {
		
		@SuppressWarnings("static-access")
		@Override
		protected void checkWin(int x, int y, GameToken token) {
			int tx = x - 1;
			int ty = y - 1;
			if (checkStep(tx, ty, token) == 1) {
				super.counter += 1;
				checkWin(tx, ty, token);
			}
		super.tend = 1;
		}
	}
}