package gbData;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class GameTokenTest {

	@Test
	public void test() {
		gameToken myToken = new tokenBlack();
		assertEquals("black", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new tokenX();
		assertEquals("X", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new tokenO();
		assertEquals("O", myToken.getName());
		assertEquals(new Color(0.f,0.f,0.f), myToken.getColor());
		
		myToken = new tokenWhite();
		assertEquals("white", myToken.getName());
		assertEquals(new Color(1.f,1.f,1.f), myToken.getColor());
	}

}
