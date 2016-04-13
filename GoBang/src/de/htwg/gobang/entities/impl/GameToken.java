package de.htwg.gobang.entities.impl;

import java.awt.Color;

import de.htwg.gobang.entities.IGameToken;

public class GameToken implements IGameToken {

	private String name;
	private Color color;
	
	public GameToken() {
		this("none", null);
	}

	public GameToken(String name, Color color) {
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
