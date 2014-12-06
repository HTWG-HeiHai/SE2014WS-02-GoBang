package de.htwg.gobang.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenO;
import de.htwg.gobang.entities.TokenX;

public final class TUI {
	
	private TUI()
	{
	}
	
	private static GameToken player1;
	private static GameToken player2;
	private static String cPlayer;
	private static String wPlayer;
	private static GbLogic myGame;
	final static int myBorder = 19;
	final static int myLoop = 20;
	final static int myHalfLoop = 10;
	
	private static Scanner scanner = new Scanner(System.in); 
	private static String[][] line;
	private static String headLine = "    01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19";
	
	public static void main(String[] args) {
			welcome();
			int choice = 0;
			
			while(true){
				try {
					choice = scanner.nextInt();
					switch(choice) {
					case 1:
						game();
						break;
					case 2:
						help();
						welcome();
						break;
					case 3:
						System.exit(0);
						break;
					default:
						myprint("Please choose 1, 2 or 3");
						welcome();
						break;
					} 
					
				} catch (InputMismatchException x) {
					System.err.println("Wrong input type. Please choose 1, 2 or 3");
					welcome();
					continue;
					}
			}
	}
			
	private static void game() {
		scanner = new Scanner(System.in);
		newGame();
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
					cPlayer = myGame.getcPlayer().getName();
					lx = x;
					ly = y;
					break;
				default:
					System.err.println("Something went wrong with myGame.setToken");
					continue;
			}
		}
		field();
		myprint(wPlayer + " you won after " + Integer.toString(myGame.getCounter())  + " turns!");
		System.exit(0);
	}
	
	private static boolean checkRemove(String s, int y, int x){
		if (s.equals("b") || s.equals("B"))
		{
			if(myGame.removeToken())
			{
				line[y-1][x] = setLine(x,"_");
				myprint("Token deleted.");
				cPlayer = myGame.getcPlayer().getName();
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
		if (x==myBorder)
		{
			tmp.append("|");
		}
		return tmp.toString();
	}
	private static void help() {
		myprint("Go Bang is a strategy board game for two players from Japane. "
				+ "\nIt is played on a board of 19 x 19 fields. The players aim to align five "
				+ "\nstones of the same token suite in vertical, horizontal or diagonal lines. ");
		myprint("");
	}

	private static void welcome() {
		myprint("Welcome to GoBang");
		myprint("1: start new game");
		myprint("2: help");
		myprint("3: quit");
	}
	
	private static void pTurn() {
		myprint("Player " + cPlayer + " it is your turn.");
		myprint("With 'b' oder 'B' you remove your last move.");
		myprint("Please enter the position of your token (x,y):");
	}

	private static void field() {
		myprint("");
		myprint(headLine);
		StringBuilder tSB = new StringBuilder();
		
		for(int i = 0; i < myBorder; i++)
		{
			for(int k = 0; k < myLoop; k++)
			{
				tSB.append(line[i][k]);
			}
			myprint(tSB.toString());
			tSB = new StringBuilder();
		}
		myprint("");
	}

	private static void newGame(){

		player1 = new TokenX();
		player2 = new TokenO();
		myGame = new GbLogic(player1, player2);
		cPlayer = myGame.getcPlayer().getName();
		wPlayer = "";
		line = new String[myBorder][myLoop];
		StringBuilder tmp = new StringBuilder();
		for(int i = 1; i < myHalfLoop; i++)
		{
			tmp.append("0").append(i);
			line[i-1][0] = tmp.toString();
			for(int k = 1; k < myLoop; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][myBorder] = line[i-1][myBorder] + "|"; 
			tmp = new StringBuilder();
		}
		
		for(int i = myHalfLoop; i < myLoop; i++)
		{
			line[i-1][0] = Integer.toString(i);
			for(int k = 1; k < myLoop; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][myBorder] = line[i-1][myBorder] + "|";
		}
	}
	
	private static String newGameLine()	{
		return "|___";
	}
	
	private static boolean isNumeric(String str)  
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
	
	private static void myprint(String tline)
	{
		System.out.println(tline);
	}

}
