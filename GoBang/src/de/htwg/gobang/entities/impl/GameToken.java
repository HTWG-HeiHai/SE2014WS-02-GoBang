package de.htwg.gobang.entities.impl;

import java.awt.Color;

import de.htwg.gobang.entities.IGameToken;

public class GameToken implements IGameToken {

	private String name;
	private Color mcolor;
	
	public GameToken() {
		this("none", null);
	}

	public GameToken(String pname, Color pcolor) {
		name = pname;
		mcolor = pcolor;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return mcolor;
	}
}
