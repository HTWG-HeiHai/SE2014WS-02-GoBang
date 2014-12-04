package de.htwg.gobang.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenO;
import de.htwg.gobang.entities.TokenX;

public class TUI {
	
	private static GameToken player1;
	private static GameToken player2;
	private static String cPlayer;
	private static GbLogic myGame;
	
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
		int x,y, lx = 0,ly = 0;
		while (s != 'g')
		{
			field();
			pTurn();
			cord = scanner.nextLine();
			
			if (cord.equals("b") || cord.equals("B"))
			{
				if(myGame.removeToken())
				{
					line[ly-1][lx] = setLine(lx,ly,"_");
					myprint("Token deleted.");
					cPlayer = myGame.getcPlayer().getName();
					continue;
				}
				else
				{
					myprint("No token left to delete.");
					continue;
				}
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
				case 'e': 
				case 'g':
					line[y-1][x] = setLine(x,y,cPlayer);
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
		myprint(cPlayer + " you won after " + Integer.toString(myGame.getCounter())  + " turns!");
		System.exit(0);
	}

	private static String setLine(int x, int y, String current) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("|_").append(current).append("_");
		if (x==19)
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
		
		for(int i = 0; i < 19; i++)
		{
			for(int k = 0; k < 20; k++)
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
		line = new String[19][20];
		StringBuilder tmp = new StringBuilder();
		for(int i = 1; i < 10; i++)
		{
			tmp.append("0").append(i);
			line[i-1][0] = tmp.toString();
			for(int k = 1; k < 20; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][19] = line[i-1][19] + "|"; 
			tmp = new StringBuilder();
		}
		
		for(int i = 10; i < 20; i++)
		{
			line[i-1][0] = Integer.toString(i);
			for(int k = 1; k < 20; k++)
			{
				line[i-1][k] = newGameLine();
			}
			line[i-1][19] = line[i-1][19] + "|";
		}
	}
	
	private static String newGameLine()	{
		return "|___";
	}
	
	private static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
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
