package de.htwg.gobang.entities;

import java.awt.Color;

public abstract class GameToken {
	
	private String name;
	private Color mcolor;
	
	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return mcolor;
	}
	
	GameToken(String pname, Color pcolor)
	{
		name = pname;
		mcolor = pcolor;
	}

}
