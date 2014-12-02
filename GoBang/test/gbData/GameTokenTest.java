package gbData;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

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
