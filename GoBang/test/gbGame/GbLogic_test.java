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
		
		System.out.println("ChangePlayer, Spielzüge, Counter werden getestet:");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Spielzüge: " + myGame.getCounter());
		System.out.println("Aktueller Spieler: " + myGame.getcPlayer().getName());
		myGame.setToken(5, 5);
		System.out.println("Spielzug getätigt\nAktueller Spieler: " + myGame.getcPlayer().getName());
		myGame.setToken(5, 6);
		System.out.println("Spielzug getätigt\nAktueller Spieler: " + myGame.getcPlayer().getName());
		System.out.println("Spielzüge getätigt: " + myGame.getCounter());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\nJetzt wird die Logik getestet:");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		
		//2x erfolgreich
		//1x besetzt
		//4 falsch (out of Index)
		
		status = myGame.setToken(1, 1);
		output(status);
		status = myGame.setToken(1, 2);
		output(status);
		status = myGame.setToken(1, 1);
		output(status);
		status = myGame.setToken(-1, 1);
		output(status);
		status = myGame.setToken(1, -1);
		output(status);
		status = myGame.setToken(20, 1);
		output(status);
		status = myGame.setToken(1, 20);
		output(status);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	private static void output(char status)
	{
		if (status != 'e')
			System.out.println("setToken hat nicht funktioniert: " + status);
		else
			System.out.println("setToken hat funktioniert.");
	}
}
