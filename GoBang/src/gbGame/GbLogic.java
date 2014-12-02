package gbGame;

import gbData.GameToken;
import gbData.GameField;

public class GbLogic {
	
	private GameField myfield;
	private GameToken Player1;
	private GameToken Player2;
	private GameToken cPlayer;
	private int counter;
	private int size;
	private char status;
	
	
	public GbLogic(GameToken p1, GameToken p2){
		myfield = new GameField();
		Player1 = p1;
		Player2 = p2;
		counter = 0;
		cPlayer = Player1;
		size = myfield.getSize();
	}
	
	public char setToken(int x, int y)
	{
		if (x > size || x < 0 || y > size || y < 0)
		{
			return 'f';
		}
		status = myfield.putStone(x, y, cPlayer);
		if (status == 'f' || status == 'b')
		{
			return status;
		}
		counter++;
		changePlayer(counter);
		return status;
	}
	
	private void changePlayer(int counter)
	{
		if (counter %2 == 0)
		{
			cPlayer = Player1;
		}
		else
		{
			cPlayer = Player2;
		}
	}
	
	public int getCounter()
	{
		return counter;
	}
	
	public GameToken getcPlayer()
	{
		return cPlayer;
	}
}
