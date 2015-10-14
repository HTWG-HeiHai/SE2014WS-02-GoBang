package de.htwg.gobang.entities;

public class GameField{
	
	final int TOKENTOWIN = 5;
	private final int FIELDSIZE = 19;
	
	GameToken[][] matrix;
	
	public GameField(){
		matrix = new GameToken[FIELDSIZE][FIELDSIZE];
	}
	
	public int getFieldSize(){
		return FIELDSIZE;
	}
	
	public GameToken[][] getGameField(){
		return matrix;
	}
	
	public char putStone(int x, int y, GameToken token){
		int tx = x-1;
		int ty = y-1;
		
		if(matrix[tx][ty] != null)
		{
			return 'b'; 
		}
		this.matrix[tx][ty] = token;
		return 'e';
	}
	
	public char removeToken(int x, int y) {
		int tx = x-1;  
		int ty = y-1;

		if (matrix[tx][ty] == null)
		{
			return 'f';
		}
		matrix[tx][ty] = null;
		return 'r';
	}
}