package gbGame;

import gbData.gameToken;
import gbData.tokenO;
import gbData.tokenX;


public class GbLogic_test {

	public static void main(String[] args) {
		gameToken Player1 = new tokenX();
		gameToken Player2 = new tokenO();
		char status;
		GbLogic myGame = new GbLogic(Player1, Player2);
		System.out.println(myGame.getCounter());
		System.out.println(myGame.getcPlayer().getName());
		myGame.setToken(5, 5);
		System.out.println(myGame.getcPlayer().getName());
		myGame.setToken(5, 6);
		System.out.println(myGame.getcPlayer().getName());
		System.out.println("Spieler " + myGame.getcPlayer().getName() + " ist wieder dran.");
		System.out.println(myGame.getCounter());
		System.out.println("Jetzt wird die Logik getestet");
		
		status = myGame.setToken(1, 1);
		if (status != 'e')
			fehler();
		
		
	}
	
	private static void fehler()
	{
		System.out.println("setToken hat nicht funktioniert.");
	}
}
