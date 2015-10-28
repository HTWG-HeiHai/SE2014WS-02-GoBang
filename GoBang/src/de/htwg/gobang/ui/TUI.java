package de.htwg.gobang.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.gobang.controller.IGbLogic;
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
	private static String headLine = "00  01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19";

	@Inject
	public TUI(IGbLogic engine)
	{
		newGame(engine);
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
					line[y-1][x] = setLine(x,cPlayer);
					cPlayer = changeTName(controller.getcPlayer().getName());
					lx = x;
					ly = y;
					controller.changePlayer(controller.getCounter());
					return myprint("Token is put on " + x + ", " + y);
			}
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
				line[ly-1][lx] = setLine(lx,"_");
				myprint("Token deleted.");
				cPlayer = changeTName(controller.getcPlayer().getName());
				return myprint("Last token deleted");
			}
			
			return myprint("No token left to delete."); 
		}
	}
	
	private String setLine(int x, String current) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("|_").append(current).append("_");
		if (x==BORDER)
		{
			tmp.append("|");
		}
		return tmp.toString();
	}
	
	public String pTurn() {
		return "Player " + cPlayer + " it is your turn.\n"
			+ "Please enter the position of your token (x,y):";
	}

	public String field() {
		StringBuilder tSB = new StringBuilder();
		tSB.append(headLine).append("\n");
		
		for(int i = 0; i < BORDER; i++)
		{
			for(int k = 0; k < LOOP; k++)
			{
				tSB.append(line[i][k]);
			}
			tSB.append("\n");
		}
		return tSB.toString();
	}

	private void newGame(IGbLogic engine){

		controller = engine;
		controller.addObserver(this);

		cPlayer = changeTName(controller.getcPlayer().getName());
		line = new String[BORDER][LOOP];
		StringBuilder tmp = new StringBuilder();
		for(int i = 1; i < HALFLOOP; i++)
		{
			tmp.append("0").append(i);
			line[i-1][0] = tmp.toString();
			for(int k = 1; k < LOOP; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][BORDER] = line[i-1][BORDER] + "|"; 
			tmp = new StringBuilder();
		}
		
		for(int i = HALFLOOP; i < LOOP; i++)
		{
			line[i-1][0] = Integer.toString(i);
			for(int k = 1; k < LOOP; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][BORDER] = line[i-1][BORDER] + "|";
		}
	}
	
	private String newGameLine()	{
		return "|___";
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
	public void update(char s, int x, int y) {
		
	}
	
	public String changeTName(String cname) {
		if (cname.equals("black"))
			return "X";
		else
			return "O";
	}

}
