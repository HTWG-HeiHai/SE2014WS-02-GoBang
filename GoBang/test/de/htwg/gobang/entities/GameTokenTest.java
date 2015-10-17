package de.htwg.gobang.entities;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import de.htwg.gobang.entities.impl.GameToken;

public class GameTokenTest {

	@Test
	public void test() {
		GameToken myToken = new GameToken("X", Color.BLACK);
		assertEquals("X", myToken.getName());
		assertEquals(Color.black, myToken.getColor());
		
		myToken = new GameToken("O", Color.BLUE);
		assertEquals("O", myToken.getName());
		assertEquals(Color.blue, myToken.getColor());
	}

}
