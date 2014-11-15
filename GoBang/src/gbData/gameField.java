package gbData;

public class gameField{
	
	final int win = 5;
	final int size = 19;
	
	gameToken[][] matrix;
	
	public gameField(){
		matrix = new gameToken[size][size];
	}
	
	public int getSize(){
		return size;
	}
	
	public char putStone(int x, int y, gameToken token){
		x--;
		y--;
		
		if(isValid(x, y))
		{
			if(matrix[x][y] != null)
			{
				return 'b'; 
			}
			
			this.matrix[x][y] = token;
			//return getWin(x, y, token);
		
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
	
	public char remove(int x, int y) {
		x--;  
		y--;
		if(isValid(x, y))
		{
			matrix[x][y] = null;
			return 'r';
		}
		
		return 'f';
	}


}