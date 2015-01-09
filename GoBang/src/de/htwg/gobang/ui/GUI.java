package de.htwg.gobang.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.util.Which;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.entities.GameToken;
import de.htwg.gobang.entities.TokenBlack;
import de.htwg.gobang.entities.TokenWhite;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private GbLogic myGame;
	private GameToken player1;
	private GameToken player2;
	private GameToken cPlayer;
	private int winP1;
	private int winP2;

	
	private JButton position;
	private JButton lastPosition;
	private JButton remove;
	private JButton newRound;
	private JTextField currentPlayerText;
	
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

		JPanel gameField;
		JPanel choice;
		
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem newGame;
		JMenuItem help;
		JMenuItem exit;
		JLabel currentPlayerLabel;

		JLabel wins;
		JLabel player1Label;
		JLabel player2Label;
		JTextField player1Text;
		JTextField player2Text;

		
		this.setTitle("GoBang");
		this.setLayout(new BorderLayout());
		
		player1 = new TokenWhite();
		player2 = new TokenBlack();
		myGame = new GbLogic(player1, player2);
		winP1 = 0;
		winP2 = 0;
		
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
		c.ipadx = FIVE;
		c.ipady = FIVE;
		c.weightx = ONE;
		
		c.gridx = ZERO;
		c.gridy = ZERO;
		choice.add(currentPlayerLabel,c);
		
		c.gridx = ONE;
		c.gridy = ZERO;
		choice.add(currentPlayerText,c);
		
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
		choice.add(player1Text, c);
		
		c.gridx = ZERO;
		c.gridy = FIVE;
		choice.add(player2Label, c);
		
		c.gridx = ONE;
		c.gridy = FIVE;
		choice.add(player2Text, c);
		
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
		
		
		if(e.getSource() == this.remove){
			myGame.removeToken();
			lastPosition.setBackground(new JButton().getBackground());
		} else if(e.getSource() == this.newRound){
			clearGame();
		} else {
			position = (JButton) e.getSource();
			lastPosition = position;
			putStone(position);
		}
		currentPlayerText.setText(myGame.getcPlayer().getName());
		
		
	}


	private void putStone(JButton tposition) {
		
		cPlayer = myGame.getcPlayer();
		String[] tmp = tposition.getName().split(",");
		char status = myGame.setToken(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		
		switch (status) {
			case 'b':
				JOptionPane.showMessageDialog(null,"Already used", "Wrong Field", JOptionPane.OK_OPTION);
				break;
			case 'g':
				//wPlayer = cPlayer;
			case 'e':
				position.setBackground(cPlayer.getColor());
				break;
			default:
				break;
		}
	}


	private void clearGame() {
		myGame = new GbLogic(player1, player2);
		
	}

	
}
