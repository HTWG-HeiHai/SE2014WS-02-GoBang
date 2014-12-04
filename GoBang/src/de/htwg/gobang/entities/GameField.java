package de.htwg.gobang.entities;

public class GameField{
	
	private final int win = 5;
	private final int size = 19;
	
	private GameToken[][] matrix;
	
	public GameField(){
		matrix = new GameToken[size][size];
	}
	
	public int getSize(){
		return size;
	}
	
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

	private boolean isValid(int x, int y) {
		
		if(x >= size  || x < 0 || y >= size  || y < 0)
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

	private char getWin(int x, int y, GameToken token) {
		int counter = 1;
		counter = goLeft(x, y-1, token, counter);
		counter = goRight(x, y+1, token, counter);
		if (counter >= win)
		{
			return 'g';
		} else {
			counter = 1;
		}
		
		counter = goUp(x-1 , y, token, counter);
		counter = goDown(x+1, y, token, counter);
		if (counter >= win)
		{
			return 'g';
		} else {
			counter = 1;
		}
		
		counter = goLeftUp(x-1, y-1, token, counter);
		counter = goRightDown(x+1, y+1, token, counter);
		if (counter >= win)
		{
			return 'g';
		} else {
			counter = 1;
		}
		
		counter = goRightUp(x-1, y+1, token, counter);
		counter = goLeftDown(x+1, y-1, token, counter);
		if (counter >= win)
		{
			return 'g';
		} else {
			counter = 1;
		}
		
		return 'e';
	}


	private int goLeftDown(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goLeftDown(x+1 , y-1, token, tcounter);
		}

		return tcounter;
	}

	private int goRightUp(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goRightUp(x-1 , y+1, token, tcounter);
		}

		return tcounter;
	}

	private int goRightDown(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goRightDown(x+1 , y+1, token, tcounter);
		}

		return tcounter;
	}

	private int goLeftUp(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goLeftUp(x-1 ,y-1, token, tcounter);
		}

		return tcounter;
	}

	private int goDown(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goDown(x+1 ,y, token, tcounter);
		}

		return tcounter;
	}

	private int goUp(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goUp(x-1 ,y, token, tcounter);
		}

		return tcounter;
	}

	private int goLeft(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goLeft(x ,y-1, token, tcounter);
		}

		return tcounter;
	}
	
	private int goRight(int x, int y, GameToken token, int counter) {
		int tcounter = counter;
		if(!isValid(x,y)) {
			return tcounter;
		}
		
		if(matrix[x][y] == token){
			tcounter++;
			tcounter = goRight(x ,y+1, token, tcounter);
		}

		return tcounter;
	}
}