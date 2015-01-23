package de.htwg.gobang.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

public class GbLogicTest {

	@Test
	public void test() {
		GameToken Player1 = new TokenWhite();
		GameToken Player2 = new TokenBlack();
		GbLogic myGame = new GbLogic(Player1, Player2);
		myGame.reset();
		
		assertEquals(0, myGame.getCounter());
		assertEquals("blue", myGame.getcPlayer().getName());
		assertEquals('e', myGame.setToken(1, 1));
		assertEquals("black", myGame.getcPlayer().getName());
		assertEquals('e', myGame.setToken(1, 2));
		assertEquals("blue", myGame.getcPlayer().getName());
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
