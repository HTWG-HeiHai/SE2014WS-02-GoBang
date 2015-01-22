package de.htwg.gobang.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.gobang.entities.GameField;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

public class GameFieldTest {
	
	GameToken tokenB;
	GameToken tokenW;
	GameField myField;

	@Test
	public void winTop() {
		tokenB = new TokenBlack();
		tokenW = new TokenWhite();
		myField = new GameField();
		myField.reset();
		assertEquals(19, myField.getFieldSize());
		assertEquals('e', myField.putStone(8, 10, tokenB));
		assertEquals('e', myField.putStone(9, 10, tokenB));
		assertEquals('e', myField.putStone(10, 10, tokenB));
		assertEquals('e', myField.putStone(11, 10, tokenB));
		assertEquals('g', myField.putStone(7, 10, tokenB));
	}
	
	@Test
	public void winLeft(){
		tokenB = new TokenBlack();
		tokenW = new TokenWhite();
		myField = new GameField();
		myField.reset();
		
		assertEquals('e', myField.putStone(15, 14, tokenB));
		assertEquals('r', myField.removeToken(15, 14));
		assertEquals('f', myField.removeToken(15, 14));
		assertEquals('e', myField.putStone(15, 14, tokenB));
		assertEquals('e', myField.putStone(15, 13, tokenB));
		assertEquals('e', myField.putStone(15, 15, tokenB));
		assertEquals('e', myField.putStone(15, 16, tokenB));
		assertEquals('g', myField.putStone(15, 17, tokenB));
		
	}
	@Test
	public void winTopLeft(){
		
		tokenB = new TokenBlack();
		tokenW = new TokenWhite();
		myField = new GameField();
		myField.reset();
		
		assertEquals('e', myField.putStone(2, 2, tokenB));
		assertEquals('e', myField.putStone(3, 3, tokenB));
		assertEquals('e', myField.putStone(5, 5, tokenB));
		assertEquals('e', myField.putStone(6, 6, tokenB));
		assertEquals('g', myField.putStone(4, 4, tokenB));
	}
	@Test
	public void winTopRight(){
		
		tokenB = new TokenBlack();
		tokenW = new TokenWhite();
		myField = new GameField();
		myField.reset();
		
		assertEquals('e', myField.putStone(10, 10, tokenB));
		assertEquals('e', myField.putStone(11, 9, tokenB));
		assertEquals('e', myField.putStone(13, 7, tokenB));
		assertEquals('e', myField.putStone(14, 6, tokenB));
		assertEquals('g', myField.putStone(12, 8, tokenB));
	}
	@Test
	public void putSomeStones(){
		tokenB = new TokenBlack();
		tokenW = new TokenWhite();
		myField = new GameField();
		myField.reset();
		myField.getFieldSize();
		assertEquals('f', myField.removeToken(20, 20));
		
		assertEquals('e', myField.putStone(17, 14, tokenB));
		assertEquals('e', myField.putStone(17, 13, tokenB));
		assertEquals('e', myField.putStone(17, 15, tokenB));
		assertEquals('e', myField.putStone(17, 16, tokenB));
		assertEquals('e', myField.putStone(17, 17, tokenW));
		
		assertEquals('b', myField.putStone(17, 14, tokenB));
		assertEquals('b', myField.putStone(17, 13, tokenW));
		
		assertEquals('f', myField.putStone(-1, 1, tokenB));	
		assertEquals('f', myField.putStone(0, 0, tokenB));	
		assertEquals('e', myField.putStone(19,19, tokenW));	
		assertEquals('f', myField.putStone(20,15,tokenW)); 	
		assertEquals('f', myField.putStone(15,20, tokenW));	
		assertEquals('f', myField.putStone(20,20, tokenW));	
		
		
	}

}
