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
				return 'b'; //liegt schon ein Token
			}
			
			this.matrix[x][y] = token;
			//return getWin(x, y, token);
			return 'e';
		}
		
		return 'f';
		}

	private boolean isValid(int x, int y) {
		
		if(x > size || x < 0 || y > size || y < 0)
		{
			return false; 
		}
		
		return true;	
		
	}

	/*private char getWin(int x, int y, gameToken token) {
		int counter = 1;
		
		if(isValid(x,y--) == 0)
		{
			if(matrix[x][y--] == token){
				counter++;
				getWin(x ,y, token, counter);
			}
		}
		
		
		return 0;
	}
	
	private boolean getWin(int x, int y, gameToken token, int counter)
	{
		return true;
	}
	
	if(matrix[x--][y] == null){
		if(matrix[x][y--] == null){
			//bin Ecke obenlinks
		}
		//bin oben
	}
	
	if(matrix[x][y--] == null){
		if(matrix[x--][y] == null){
			//bin Ecke untenlinks
		}
		// bin links
	}
	
	if(matrix[x--][y] == null){
		if(matrix[x][y++] == null){
			//bin ecke untenrechts
		}
		//bin unten
	}
	if(matrix[x][y++] == null){
		if(matrix[x--][y] == null){
			// bin ecke rechtsoben
		}
		//bin rechts
	}*/
	
	
	
}