package de.htwg.gobang.model.impl;

import java.awt.Color;

import de.htwg.gobang.model.IToken;

public class Token implements IToken {

	private String name;
	private Color color;
	
	public Token() {
		this("none", null);
	}

	public Token(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
