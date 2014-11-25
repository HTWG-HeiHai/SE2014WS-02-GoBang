package gbUI;

import gbData.tokenO;
import gbData.gameToken;
import gbData.tokenX;
import gbGame.GbLogic;

public class TUI {
	
	gameToken player1;
	gameToken player2;
	GbLogic myGame;
	
	public static void main(String[] args) {
		
		
	}
	
	private void newGame(){
		player1 = new tokenX();
		player2 = new tokenO();
		myGame = new GbLogic(player1, player2);
	}

}
