package de.htwg.gobang.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUI extends JFrame implements ActionListener{
	
	GbLogic myGame;
	GameToken player1;
	GameToken player2;

	JPanel gameField;
	JPanel choice;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem newGame;
	JMenuItem help;
	JMenuItem exit;
	JLabel currentPlayerLabel;
	JTextField currentPlayerText;
	JLabel wins;
	JLabel player1Label;
	JLabel player2Label;
	JTextField player1Text;
	JTextField player2Text;
	JButton remove;
	JButton newRound;
	
	private final static int LENGTH = 20;
	private final static int FIELD = 361;
	
	
	JButton[] array = new JButton[FIELD];
	
	public GUI(){	
	this.setTitle("GoBang");
	this.setLayout(new BorderLayout());
	
	player1 = new TokenWhite();
	player2 = new TokenBlack();
	myGame = new GbLogic(player1, player2);
	

	//MenuBar
	menuBar = new JMenuBar();
	menu = new JMenu("Menu");
	newGame = new JMenuItem("new Game");
	help = new JMenuItem("help");
	exit = new JMenuItem("exit");
	
	menuBar.add(menu);
	menu.add(newGame);
	menu.add(help);
	menu.add(exit);
	this.setJMenuBar(menuBar);
	
	//GameField
	gameField = new JPanel();
	gameField.setLayout(new GridBagLayout());
	
	GridBagConstraints g = new GridBagConstraints();
	g.fill = GridBagConstraints.HORIZONTAL;
	g.ipadx = 5;
	g.ipady = 5;
	g.weightx = 8;
	
	for(int i = 1; i < LENGTH; i++){
		for(int k = 1; k < LENGTH; k++){
			g.gridx = i;
			g.gridy = k;
			gameField.add(new JButton() ,g);	
		}
	}
	
	g.gridx = LENGTH;
	g.gridy = 0;
	gameField.add(new JLabel(" ") ,g);
	
	g.gridx = LENGTH;
	g.gridy = LENGTH;
	gameField.add(new JLabel(" ") ,g);
	
	g.gridx = 0;
	g.gridy = LENGTH;
	gameField.add(new JLabel(" ") ,g);
			
	//Choice
	choice = new JPanel();
	choice.setLayout(new GridBagLayout());
	
	currentPlayerLabel = new JLabel("current Player: ");
	currentPlayerText = new JTextField(myGame.getcPlayer().getName());
	currentPlayerText.setEditable(false);
	wins = new JLabel("wins: ");
	player1Label = new JLabel("player1");
	player2Label = new JLabel("player2");
	player1Text = new JTextField("0");
	player1Text.setEditable(false);
	player2Text = new JTextField("0");
	player2Text.setEditable(false);
	remove = new JButton("remove last Token");
	newRound = new JButton("new Round");
	newRound.setEnabled(false);
	
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipadx = 5;
	c.ipady = 5;
	c.weightx = 1;
	
	c.gridx = 0;
	c.gridy = 0;
	choice.add(currentPlayerLabel,c);
	
	c.gridx = 1;
	c.gridy = 0;
	choice.add(currentPlayerText,c);
	
	c.gridx = 0;
	c.gridy = 2;
	choice.add(new JLabel(" "),c);
	
	c.gridx = 0;
	c.gridy = 3;
	choice.add(wins,c);
	
	c.gridx = 0;
	c.gridy = 4;
	choice.add(player1Label, c);
	
	c.gridx = 1;
	c.gridy = 4;
	choice.add(player1Text, c);
	
	c.gridx = 0;
	c.gridy = 5;
	choice.add(player2Label, c);
	
	c.gridx = 1;
	c.gridy = 5;
	choice.add(player2Text, c);
	
	c.gridx = 0;
	c.gridy = 6;
	choice.add(new JLabel(" "),c);
	
	c.gridx = 0;
	c.gridy = 7;
	choice.add(remove, c);
	
	c.gridx = 0;
	c.gridy = 8;
	choice.add(new JLabel(" "),c);
	
	c.gridx = 0;
	c.gridy = 9;
	choice.add(newRound, c);
	
	c.gridx = 5;
	c.gridy = 0;
	choice.add(new JLabel(" "),c);
	
	

	
	
	
	this.add(gameField, BorderLayout.CENTER);
	this.add(choice, BorderLayout.EAST);
	
	this.pack();
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new GUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
