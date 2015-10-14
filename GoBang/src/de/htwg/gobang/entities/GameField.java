package de.htwg.gobang.entities;

public class GameField{
	
	static final int TOKENTOWIN = 5;
	private static final int FIELDSIZE = 19;
	
	static GameToken[][] matrix;
	
	private Checker myCheck;
	
	public GameField(){
		matrix = new GameToken[FIELDSIZE][FIELDSIZE];
		//myCheck = createChain();
	}
	
	public int getFieldSize(){
		return FIELDSIZE;
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

	static boolean isValid(int x, int y) {
		
		if(x >= FIELDSIZE  || x < 0 || y >= FIELDSIZE  || y < 0)
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
	
	public void reset(){
		/*Checker.win = 0;
		Checker.counter = 1;
		Checker.tend = 0;
		*/
	}

	private char getWin(int x, int y, GameToken token) {
		/*
		myCheck.getWin(x, y, token);
		if (Checker.win == 1)
		{ 
			return 'g';
		}
		return 'e';
		*/
		return 'e';
	}
	


}