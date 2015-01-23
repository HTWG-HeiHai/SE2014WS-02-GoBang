package de.htwg.gobang.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.observer.IObserver;

public final class TUI implements IObserver {

	private Logger logger = Logger.getLogger("GoBang");
	public TUI(GbLogic myGameField)
	{
		newGame(myGameField);
		game();
	}

	
	private String cPlayer;
	private String wPlayer;
	private GbLogic myGame;
	static final int BORDER = 19;
	static final int LOOP = 20;
	static final int HALFLOOP = 10;
	static final int THREE = 3;
	static final int TWO = 2;
	static final int ONE = 1;
	
	private static Scanner scanner = new Scanner(System.in); 
	private static String[][] line;
	private static String headLine = "    01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19";
			
	private void game() {
		scanner = new Scanner(System.in);
		char s = 'a';
		String cord;
		String[] position;
		int x,y; 
		int lx = 0;
		int ly = 0;
		while (s != 'g')
		{
			field();
			pTurn();
			cord = scanner.nextLine();
			
			if (checkRemove(cord, ly, lx))
			{
				continue;
			}
			
			if (!cord.contains(","))
			{
				myprint("Not seperated with ','.");
				continue;
			}
			position = cord.split(",");
			
			if(isNumeric(position[0]) && (isNumeric(position[1])))
			{
				x = Integer.valueOf(position[0]);
				y = Integer.valueOf(position[1]);
				s = myGame.setToken(x, y);
			}
			else
			{
				myprint("2 numbers are needed.");
				continue;
			}
			switch (s) {
				case 'f':
					myprint("Only number between 1 and 19 valid.");
					continue;
				case 'b':
					myprint("There is already a token.");
					continue;
				case 'g':
					wPlayer = cPlayer;
				case 'e':
					line[y-1][x] = setLine(x,cPlayer);
					cPlayer = changeTName(myGame.getcPlayer().getName());
					lx = x;
					ly = y;
					break;
				default:
					myprint("Something went wrong with myGame.setToken");
					continue;
			}
		}
		field();
		myprint(wPlayer + " you won after " + Integer.toString(myGame.getCounter())  + " turns!");
		System.exit(0);
	}
	
	private boolean checkRemove(String s, int y, int x){
		if (s.equals("b") || s.equals("B"))
		{
			if(myGame.removeToken())
			{
				line[y-1][x] = setLine(x,"_");
				myprint("Token deleted.");
				cPlayer = changeTName(myGame.getcPlayer().getName());
				return true;
			}
			else
			{
				myprint("No token left to delete.");
				return true;
			}
		}
		return false;
	}
	
	private static String setLine(int x, String current) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("|_").append(current).append("_");
		if (x==BORDER)
		{
			tmp.append("|");
		}
		return tmp.toString();
	}
	
	private void pTurn() {
		myprint("Player " + cPlayer + " it is your turn.");
		myprint("With 'b' oder 'B' you remove your last move.");
		myprint("Please enter the position of your token (x,y):");
	}

	private void field() {
		myprint("");
		myprint(headLine);
		StringBuilder tSB = new StringBuilder();
		
		for(int i = 0; i < BORDER; i++)
		{
			for(int k = 0; k < LOOP; k++)
			{
				tSB.append(line[i][k]);
			}
			myprint(tSB.toString());
			tSB = new StringBuilder();
		}
		myprint("");
	}

	private void newGame(GbLogic myGameField){

		myGame = myGameField;
		cPlayer = changeTName(myGame.getcPlayer().getName());
		wPlayer = "";
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
	
	private void myprint(String tline)
	{
		logger.info(tline);
	}

	@Override
	public void update(char s, int x, int y) {
		
	}
	
	public static String changeTName(String cname) {
		if (cname.equals("black"))
			return "X";
		else
			return "O";
	}

}
