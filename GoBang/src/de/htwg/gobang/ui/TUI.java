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
	private static GbLogic myGame;
	
	private static final Scanner Scan = new Scanner(System.in); 
	private static int choice;
	private static String[] line;
	
	public static void main(String[] args) {
			welcome();
			
			while(true){
				try {
					switch(choice = Scan.nextInt()) {
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
						System.out.println("Please choose 1, 2 or 3");
						welcome();
						break;
					} 
					
				} catch (InputMismatchException x) {
					System.err.println("Wrong input type. Please choose 1, 2 or 3");
					welcome();
					break;
					}
			}
	}
			
	private static void game() {
		newGame();
		field();
		
	}

	private static void help() {
		System.out.println("Go Bang is a strategy board game for two players from Japane. "
				+ "\nIt is played on a board of 19 x 19 fields. The players aim to align five "
				+ "\nstones of the same token suite in vertical, horizontal or diagonal lines. ");
		System.out.println();
	}

	private static void welcome() {
		System.out.println("Welcome to GoBang");
		System.out.println("1: start new game");
		System.out.println("2: help");
		System.out.println("3: quit");
		
	}

	private static void field() {
		
	}

	private static void newGame(){
		player1 = new TokenX();
		player2 = new TokenO();
		myGame = new GbLogic(player1, player2);
	}

}
