package de.htwg.gobang.ui;

import com.google.inject.Inject;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.observer.IObserver;

public final class TUI implements IObserver {

	//private Logger logger = Logger.getLogger("GoBang");
	
	private String cPlayer;
	private IGbLogic controller;
	static final int BORDER = 19;
	static final int LOOP = 20;
	static final int HALFLOOP = 10;
	static final int THREE = 3;
	static final int TWO = 2;
	static final int ONE = 1;
	private int lx = 0;
	private int ly = 0;
	
	//private static Scanner scanner = new Scanner(System.in); 
	private static String[][] line;
	private IGameToken[][] field;
	private static String headLine = "00  01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19";

	@Inject
	public TUI(IGbLogic engine)
	{
		controller = engine;
		engine.addObserver(this);
		field = controller.getField();
//		newGame();
	}

	public String setToken(String cord){
		
		String[] position;
		
		if (!cord.contains(","))
		{
			return myprint("Not seperated with ','.");
		}
		position = cord.split(",");
		
		if(isNumeric(position[0]) && (isNumeric(position[1])))
		{
			char s = controller.setToken(Integer.valueOf(position[0]), Integer.valueOf(position[1]));
			int x = Integer.valueOf(position[0]);
			int y = Integer.valueOf(position[1]);
			switch (s) {
				case 'f':
					return myprint("Only number between 1 and 19 valid.");
				case 'b':
					return myprint("There is already a token.");
				case 'g':
					return myprint("won");
				case 'e':
					cPlayer = changeTName(controller.getcPlayer().getName());
					lx = x;
					ly = y;
					controller.changePlayer(controller.getCounter());
					return myprint("Token is put on " + x + ", " + y);
			}
			field = controller.getField();
		}
		else
		{
			return myprint("2 numbers are needed.");
		}
		return myprint("Something went wrong with myGame.setToken");
	}
	
	public String removeToken(){
		{
			if(controller.removeToken())
			{
				myprint("Token deleted.");
				cPlayer = changeTName(controller.getcPlayer().getName());
				return myprint("Last token deleted");
			}
			
			return myprint("No token left to delete."); 
		}
	}
	
	public String drawField(){
		StringBuilder tSB = new StringBuilder();
		tSB.append(headLine).append("\n");
		String eLine = "|___";
		for (int i=0; i<19; i++){
			if(i<9){
				tSB.append("0");
			}
			tSB.append(i+1);
			for (int y=0; y<19; y++){
				if(!field[i][y].getName().equals("none")){
					tSB.append("|_" + changeTName(field[i][y].getName()) + "_");
				}
				tSB.append(eLine);
			}
			tSB.append("|\n");
		}
		return tSB.toString();
		
	}
	
	public String pTurn() {
		return "Player1 " + cPlayer + " it is your turn.\n"
			+ "Please enter the position of your token (x,y):";
	}

	private void newGame(){
		cPlayer = changeTName(controller.getcPlayer().getName());
	}
	
	private boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private String myprint(String tline)
	{
		//logger.info(tline);
		return tline;
	}

	@Override
	public void update() {
		field = controller.getField();
	}
	
	public String changeTName(String cname) {
		if (cname.equals("black"))
			return "X";
		else
			return "O";
	}

}
