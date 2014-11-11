package gbData;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameFieldTest {

	@Test
	public void test() {
		gameToken tokenB = new tokenBlack();
		gameToken tokenW = new tokenWhite();
		gameField myField = new gameField();
		
		assertEquals(19, myField.getSize());
		assertEquals('e', myField.putStone(15, 14, tokenB));
		assertEquals('b', myField.putStone(15, 14, tokenB));
		assertEquals('b', myField.putStone(15, 14, tokenW));
		
		assertEquals('f', myField.putStone(-1, 1, tokenB));	
		assertEquals('f', myField.putStone(0, 0, tokenB));	
		assertEquals('e', myField.putStone(19,19, tokenW));	
		assertEquals('f', myField.putStone(20,15,tokenW)); 	
		assertEquals('f', myField.putStone(15,20, tokenW));	
		assertEquals('f', myField.putStone(20,20, tokenW));	
	}

}
