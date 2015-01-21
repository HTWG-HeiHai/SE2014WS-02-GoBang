package de.htwg.gobang.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Enumeration;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private GbLogic myGame;
	private GameToken player1;
	private GameToken player2;
	private GameToken cPlayer;
	private int cp1 = 0;
	private int cp2 = 0;
	
	private GridBagConstraints g;
	private JPanel gameField;
	private JMenuItem newGame;
	private JMenuItem help;
	private JMenuItem exit;
	private JMenuItem menuRound;
	
	private ButtonGroup group;
	private JButton position;
	private JButton lastPosition;
	private JButton remove;
	private JButton newRound;
	
	private JLabel currentPlayerLabelText;
	private JLabel player1LabelText;
	private JLabel player2LabelText;
	
	private static final int LENGTH = 20;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT= 8;
	private static final int NINE = 9;
	
	public GUI(){	
		JPanel choice;
		JMenuBar menuBar;
		JMenu menu;
	
		JLabel wins;
		JLabel currentPlayerLabel;
		JLabel player1Label;
		JLabel player2Label;
		
		this.setTitle("GoBang");
		this.setLayout(new BorderLayout());
		
		player1 = new TokenBlack();
		player2 = new TokenWhite();
		myGame = new GbLogic(player1, player2);
		cPlayer = myGame.getcPlayer();
		
		//MenuBar
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		newGame = new JMenuItem("new Game");
		help = new JMenuItem("help");
		exit = new JMenuItem("exit");
		menuRound = new JMenuItem("new Round");
		
		menuBar.add(menu);
		menuBar.setBackground(Color.WHITE);
		menu.add(newGame);
		menu.add(menuRound);
		menu.add(help);
		menu.add(exit);
		
		
		this.setJMenuBar(menuBar);
		
		//GameField
		gameField = new JPanel();
		gameField.setLayout(new GridBagLayout());
		group = new ButtonGroup();
		
		g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipadx = FIVE;
		g.ipady = FIVE;
		g.weightx = EIGHT;
		
		for(int i = 1; i < LENGTH; i++){
			for(int k = 1; k < LENGTH; k++){
				g.gridx = i;
				g.gridy = k;
				position = new JButton();
				position.setName(i + "," + k);
				gameField.add(position ,g);	
				position.addActionListener(this);
				group.add(position);
			}
		}
		
		g.gridx = LENGTH;
		g.gridy = ZERO;
		gameField.add(new JLabel(" ") ,g);
		
		g.gridx = LENGTH;
		g.gridy = LENGTH;
		gameField.add(new JLabel(" ") ,g);
		
		g.gridx = ZERO;
		g.gridy = LENGTH;
		gameField.add(new JLabel(" ") ,g);
				
		//Choice
		choice = new JPanel();
		choice.setLayout(new GridBagLayout());
		
		currentPlayerLabel = new JLabel("current Player: ");
		currentPlayerLabelText = new JLabel(cPlayer.getName());
		JLabel fakeLabel = new JLabel("black");
		fakeLabel.setForeground(new JButton().getBackground());
		wins = new JLabel("Wins: ");
		player1Label = new JLabel("Player black");
		player1Label.setForeground(player1.getColor());
		player2Label = new JLabel("Player blue");
		player2Label.setForeground(player2.getColor());
		player1LabelText = new JLabel(new Integer(cp1).toString());
		player2LabelText = new JLabel(new Integer(cp2).toString());
		player1LabelText.setForeground(player1.getColor());
		player2LabelText.setForeground(player2.getColor());
		remove = new JButton("remove last token");
		newRound = new JButton("new round");
		newRound.setEnabled(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = FIVE;
		c.ipady = FIVE;
		c.weightx = ONE;
		
		c.gridx = ZERO;
		c.gridy = ZERO;
		choice.add(currentPlayerLabel,c);
		
		c.gridx = ONE;
		c.gridy = ZERO;
		choice.add(currentPlayerLabelText,c);
		
		c.gridx = ONE;
		c.gridy = ONE;
		choice.add(fakeLabel, c);
		
		c.gridx = ZERO;
		c.gridy = TWO;
		choice.add(new JLabel(" "),c);
		
		c.gridx = ZERO;
		c.gridy = THREE;
		choice.add(wins,c);
		
		c.gridx = ZERO;
		c.gridy = FOUR;
		choice.add(player1Label, c);
		
		c.gridx = ONE;
		c.gridy = FOUR;
		choice.add(player1LabelText, c);
		
		c.gridx = ZERO;
		c.gridy = FIVE;
		choice.add(player2Label, c);
		
		c.gridx = ONE;
		c.gridy = FIVE;
		choice.add(player2LabelText, c);
		
		c.gridx = ZERO;
		c.gridy = SIX;
		choice.add(new JLabel(" "),c);
		
		c.gridx = ZERO;
		c.gridy = SEVEN;
		choice.add(remove, c);
		
		c.gridx = ZERO;
		c.gridy = EIGHT;
		choice.add(new JLabel(" "),c);
		
		c.gridx = ZERO;
		c.gridy = NINE;
		choice.add(newRound, c);
		
		c.gridx = FIVE;
		c.gridy = ZERO;
		choice.add(new JLabel(" "),c);
			
		remove.addActionListener(this);
		newRound.addActionListener(this);
		help.addActionListener(this);
		newGame.addActionListener(this);
		exit.addActionListener(this);
		menuRound.addActionListener(this);
		
		this.add(gameField, BorderLayout.CENTER);
		this.add(choice, BorderLayout.EAST);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new GUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == this.remove) {
			myGame.removeToken();
			lastPosition.setBackground(new JButton().getBackground());
			remove.setEnabled(false);
		} else if(e.getSource() == this.newRound) {
			createGame();
			return;
		} else if (e.getSource() == this.newGame) {
			newGame();
		} else if (e.getSource() == this.exit) {
			System.exit(0);
		} else if (e.getSource() == this.help) {
			help();
		} else if (e.getSource() == this.menuRound){
			createGame();
		} else {
			position = (JButton) e.getSource();
			lastPosition = position;
			putStone(position);
		}
		currentPlayerLabelText.setText(myGame.getcPlayer().getName());
		currentPlayerLabelText.setForeground(myGame.getcPlayer().getColor());
		
	}


	private void help() {
		JOptionPane.showMessageDialog(null, "Go Bang is a strategy board game for two players from Japane. "
				+ "\nIt is played on a board of 19 x 19 fields. The players aim to align five "
				+ "\nstones of the same token suite in vertical, horizontal or diagonal lines.", "Help", JOptionPane.OK_OPTION);
	}


	private void putStone(JButton tposition) {
		
		cPlayer = myGame.getcPlayer();
		String[] tmp = tposition.getName().split(",");
		char status = myGame.setToken(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		
		switch (status) {
			case 'b':
				JOptionPane.showMessageDialog(null,"Already used", "Wrong Field", JOptionPane.OK_OPTION);
				break;
			case 'e':
				position.setBackground(cPlayer.getColor());
				remove.setEnabled(true);
				break;
			case 'g':
				position.setBackground(cPlayer.getColor());
				JOptionPane.showMessageDialog(null,"Player " + cPlayer.getName() + " you won!", "Win", JOptionPane.OK_OPTION);
				if (cPlayer == player1) {
					cp1 += 1;
					player1LabelText.setText(new Integer(cp1).toString());
				}
				else {
					cp2 += 1;
					player2LabelText.setText(new Integer(cp2).toString());
				}
				changeButtons(false);
				newRound.setEnabled(true);
				break;
			default:
				break;
		}
	}

	private void changeButtons(boolean state){
		Enumeration<AbstractButton> tbutton = group.getElements();
		AbstractButton e = tbutton.nextElement();
		try {
			if (state){
				do {
					e.setEnabled(state);
					e.setBackground(new JButton().getBackground());
					e = tbutton.nextElement();		
				} while (e != null);
			} else {
				do {
					e.setEnabled(state);
					e = tbutton.nextElement();
				} while (e != null);
			}
		} catch (Exception NoSuchElementException) {
			
		}

		this.remove.setEnabled(state);
		this.newRound.setEnabled(false);
		currentPlayerLabelText.setVisible(state);
		currentPlayerLabelText.setForeground(myGame.getcPlayer().getColor());
	}

	private void createGame() {
		if (cPlayer == player1){
			myGame = new GbLogic(player1, player2);
		} else {
			myGame = new GbLogic(player2, player1);
		}
		
		myGame.reset();
		changeButtons(true);
		currentPlayerLabelText.setText(cPlayer.getName());
	}
	
	private void newGame(){
		createGame();
		cp1 = 0;
		cp2 = 0;
		player1LabelText.setText(new Integer(cp1).toString());
		player2LabelText.setText(new Integer(cp2).toString());
	}
}
