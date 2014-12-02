package gbData;

public class GameField{
	
	final int win = 5;
	final int size = 19;
	
	GameToken[][] matrix;
	
	public GameField(){
		matrix = new GameToken[size][size];
	}
	
	public int getSize(){
		return size;
	}
	
	public char putStone(int x, int y, GameToken token){
		x--;
		y--;
		
		if(isValid(x, y))
		{
			if(matrix[x][y] != null)
			{
				return 'b'; 
			}
			
			this.matrix[x][y] = token;
			return getWin(x, y, token);
		
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
		x--;  
		y--;
		if(isValid(x, y))
		{
			matrix[x][y] = null;
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
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goLeftDown(x+1 , y-1, token, counter);
		}

		return counter;
	}

	private int goRightUp(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goRightUp(x-1 , y+1, token, counter);
		}

		return counter;
	}

	private int goRightDown(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goRightDown(x+1 , y+1, token, counter);
		}

		return counter;
	}

	private int goLeftUp(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goLeftUp(x-1 ,y-1, token, counter);
		}

		return counter;
	}

	private int goDown(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goDown(x+1 ,y, token, counter);
		}

		return counter;
	}

	private int goUp(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goUp(x-1 ,y, token, counter);
		}

		return counter;
	}

	private int goLeft(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goLeft(x ,y-1, token, counter);
		}

		return counter;
	}
	
	private int goRight(int x, int y, GameToken token, int counter) {
		if(!isValid(x,y))
			return counter;
		
		if(matrix[x][y] == token){
			counter++;
			counter = goRight(x ,y+1, token, counter);
		}

		return counter;
	}
}