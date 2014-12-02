package gbGame;

import static org.junit.Assert.*;
import gbData.GameToken;
import gbData.tokenO;
import gbData.tokenX;

import org.junit.Test;

public class GbLogicTest {

	@Test
	public void test() {
		GameToken Player1 = new tokenX();
		GameToken Player2 = new tokenO();
		GbLogic myGame = new GbLogic(Player1, Player2);
		
		assertEquals(0, myGame.getCounter());
		assertEquals("X", myGame.getcPlayer().getName());
		assertEquals('e', myGame.setToken(1, 1));
		assertEquals("O", myGame.getcPlayer().getName());
		assertEquals('e', myGame.setToken(1, 2));
		assertEquals("X", myGame.getcPlayer().getName());
		assertEquals(2, myGame.getCounter());
		assertEquals('e', myGame.setToken(19, 19));
		assertEquals('b', myGame.setToken(1, 2));
		assertEquals('f', myGame.setToken(20, 1));
		assertEquals('f', myGame.setToken(1, 20));
		assertEquals('f', myGame.setToken(-1, 1));
		assertEquals('f', myGame.setToken(1, -1));
		assertEquals('f', myGame.setToken(1, 0));
		assertEquals('f', myGame.setToken(0, 1));
	}


}
