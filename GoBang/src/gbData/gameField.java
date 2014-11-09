package gbData;

public class gameField{
	
	final int win = 5;
	final int size = 19;
	
	gameToken[][] matrix;
	
	gameField(){
		matrix = new gameToken[size][size];
	}
	
	public int getSize(){
		return size;
	}
	
	public int putStone(int x, int y, gameToken token){

		if(x > size || y > size)
		{
			return 1;
		}
		
		this.matrix[x][y] = token;
		
		return getWin(x, y, token);
		
	}

	private int getWin(int x, int y, gameToken token) {
		
		return 0;
	}
	
	
}