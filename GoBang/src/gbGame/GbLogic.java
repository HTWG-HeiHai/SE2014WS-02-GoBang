package gbGame;

import gbData.gameToken;
import gbData.gameField;

public class GbLogic {
	
	private gameField myfield;
	private gameToken Player1;
	private gameToken Player2;
	private gameToken cPlayer;
	private int counter;
	
	GbLogic(gameToken p1, gameToken p2){
		myfield = new gameField();
		Player1 = p1;
		Player2 = p2;
		counter = 0;
		cPlayer = Player1;
	}
	
	public int setToken(int x, int y)
	{
		counter++;
		myfield.putStone(x, y, cPlayer);
		changePlayer(counter);
		return 1;
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
	
	public gameToken getcPlayer()
	{
		return cPlayer;
	}
}
