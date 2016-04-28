package de.htwg.gobang.entities;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import de.htwg.gobang.model.impl.Token;

public class GameTokenTest {

	@Test
	public void test() {
		Token myToken = new Token("X", Color.BLACK);
		assertEquals("X", myToken.getName());
		assertEquals(Color.black, myToken.getColor());
		
		myToken = new Token("O", Color.BLUE);
		assertEquals("O", myToken.getName());
		assertEquals(Color.blue, myToken.getColor());
	}

}
