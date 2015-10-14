package de.htwg.gobang.entities;

import java.awt.Color;

public class GameToken {
	
	private String name;
	private Color mcolor;
	
	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return mcolor;
	}
	
	public GameToken(String pname, Color pcolor)
	{
		name = pname;
		mcolor = pcolor;
	}

}
