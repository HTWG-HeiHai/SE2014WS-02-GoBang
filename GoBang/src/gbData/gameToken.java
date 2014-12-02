package gbData;

import java.awt.Color;

public abstract class GameToken {
	
	protected String name;
	protected Color mcolor;
	
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
