package gbData;

import java.awt.Color;

public abstract class gameToken {
	
	protected String name;
	protected Color mcolor;
	
	public String getName(){
		return name;
	}
	
	public Color getColor(){
		return mcolor;
	}
	
	gameToken(String pname, Color pcolor)
	{
		name = pname;
		mcolor = pcolor;
	}

}
