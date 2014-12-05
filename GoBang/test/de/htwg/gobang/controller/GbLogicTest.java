package de.htwg.gobang.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenO;
import de.htwg.gobang.entities.TokenX;

public class GbLogicTest {

	@Test
	public void test() {
		GameToken Player1 = new TokenX();
		GameToken Player2 = new TokenO();
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
		assertEquals('e',myGame.setToken(5, 5));
		assertEquals(true, myGame.removeToken());
		assertEquals(false, myGame.removeToken());
	}


}
