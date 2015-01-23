package de.htwg.gobang.entities;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

public class GameTokenTest {

	@Test
	public void test() {
		GameToken myToken = new TokenBlack();
		assertEquals("X", myToken.getName());
		assertEquals(Color.black, myToken.getColor());
		
		myToken = new TokenWhite();
		assertEquals("O", myToken.getName());
		assertEquals(Color.blue, myToken.getColor());
	}

}
