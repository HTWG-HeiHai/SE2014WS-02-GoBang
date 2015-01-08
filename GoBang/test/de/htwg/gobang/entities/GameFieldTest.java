package de.htwg.gobang.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.gobang.entities.GameField;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

public class GameFieldTest {

	@Test
	public void test() {
		GameToken tokenB = new TokenBlack();
		GameToken tokenW = new TokenWhite();
		GameField myField = new GameField();
		
		assertEquals(19, myField.getFieldSize());
		
		//left - right
		assertEquals('e', myField.putStone(15, 14, tokenB));
		assertEquals('r', myField.removeToken(15, 14));
		assertEquals('f', myField.removeToken(15, 14));
		assertEquals('e', myField.putStone(15, 14, tokenB));
		assertEquals('e', myField.putStone(15, 13, tokenB));
		assertEquals('e', myField.putStone(15, 15, tokenB));
		assertEquals('e', myField.putStone(15, 16, tokenB));
		assertEquals('g', myField.putStone(15, 17, tokenB));
		
		//up - down
		assertEquals('e', myField.putStone(8, 10, tokenB));
		assertEquals('e', myField.putStone(9, 10, tokenB));
		assertEquals('e', myField.putStone(10, 10, tokenB));
		assertEquals('e', myField.putStone(11, 10, tokenB));
		assertEquals('g', myField.putStone(7, 10, tokenB));
		
		//leftUp - downRight
		assertEquals('e', myField.putStone(6, 14, tokenB));
		assertEquals('e', myField.putStone(5, 13, tokenB));
		assertEquals('e', myField.putStone(8, 16, tokenB));
		assertEquals('e', myField.putStone(7, 15, tokenB));
		assertEquals('g', myField.putStone(4, 12, tokenB));
		
		//leftDown - rightUp
		assertEquals('e', myField.putStone(10, 7, tokenB));
		assertEquals('e', myField.putStone(12, 5, tokenB));
		assertEquals('e', myField.putStone(14, 3, tokenB));
		assertEquals('e', myField.putStone(11, 6, tokenB));
		assertEquals('g', myField.putStone(13, 4, tokenB));
		
		assertEquals('e', myField.putStone(17, 14, tokenB));
		assertEquals('e', myField.putStone(17, 13, tokenB));
		assertEquals('e', myField.putStone(17, 15, tokenB));
		assertEquals('e', myField.putStone(17, 16, tokenB));
		assertEquals('e', myField.putStone(17, 17, tokenW));
		
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
