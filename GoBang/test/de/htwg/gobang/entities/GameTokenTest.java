package de.htwg.gobang.entities;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenO;
import de.htwg.gobang.entities.TokenWhite;
import de.htwg.gobang.entities.TokenX;

public class GameTokenTest {

	@Test
	public void test() {
		GameToken myToken = new TokenBlack();
		assertEquals("black", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new TokenX();
		assertEquals("X", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new TokenO();
		assertEquals("O", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new TokenWhite();
		assertEquals("white", myToken.getName());
		assertEquals(new Color(1.f,1.f,1.f), myToken.getColor());
	}

}
