package de.htwg.gobang.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

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

import com.google.inject.Inject;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.observer.IObserver;

public class GUI extends JFrame implements ActionListener, IObserver {

	private static final long serialVersionUID = 1L;

	private IGbLogic controller;

	private GridBagConstraints g;
	private JPanel gameField;
	private JMenuItem newGame;
	private JMenuItem help;
	private JMenuItem exit;
	private JMenuItem menuRound;
	
	private JButton[][] buttonField;
	private IGameToken[][] tokenField;

	private JButton position;
	private JButton lastPosition;
	private JButton remove;
	private JButton newRound;

	private JLabel currentPlayerLabelText;
	private JLabel player1LabelText;
	private JLabel player2LabelText;

	@Inject
	public GUI(IGbLogic engine) {
		
		controller = engine;
		controller.addObserver(this);

		JPanel choice;
		JMenuBar menuBar;
		JMenu menu;
		buttonField = new JButton[19][19];

		JLabel wins;
		JLabel currentPlayerLabel;
		JLabel player1Label;
		JLabel player2Label;

		this.setTitle("GoBang");
		this.setLayout(new BorderLayout());

		// MenuBar
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		newGame = new JMenuItem("new Game");
		help = new JMenuItem("help");
		exit = new JMenuItem("exit");
		menuRound = new JMenuItem("new Round");

		menuBar.add(menu);
		menu.add(newGame);
		menu.add(menuRound);
		menu.add(help);
		menu.add(exit);

		this.setJMenuBar(menuBar);

		// GameField
		gameField = new JPanel();
		gameField.setLayout(new GridBagLayout());

		g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipadx = 5;
		g.ipady = 5;
		g.weightx = 8;
		
		

		for (int i = 0; i < 19; i++) {
			for (int k = 0; k < 19; k++) {
				g.gridx = i;
				g.gridy = k;
				position = new JButton();
				position.setName((i) + "," + (k));
				gameField.add(position, g);
				position.addActionListener(this);
				buttonField[i][k] = position;
			}
		}

		choice = new JPanel();
		choice.setLayout(new GridBagLayout());

		currentPlayerLabel = new JLabel("current Player: ");
		currentPlayerLabelText = new JLabel(controller.getcPlayer().getName());
		JLabel fakeLabel = new JLabel("black");
		fakeLabel.setForeground(new JButton().getBackground());
		wins = new JLabel("Wins: ");
		player1Label = new JLabel("Player black");
		player1Label.setForeground(controller.getPlayer1().getColor());
		player2Label = new JLabel("Player blue");
		player2Label.setForeground(controller.getPlayer2().getColor());
		player1LabelText = new JLabel(new Integer(controller.getWinPlayer1()).toString());
		player2LabelText = new JLabel(new Integer(controller.getWinPlayer2()).toString());
		player1LabelText.setForeground(controller.getPlayer1().getColor());
		player2LabelText.setForeground(controller.getPlayer2().getColor());
		remove = new JButton("remove last token");
		newRound = new JButton("new round");
		newRound.setEnabled(false);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 5;
		c.ipady = 5;
		c.weightx = 1;

		c.gridx = 0;
		c.gridy = 0;
		choice.add(currentPlayerLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		choice.add(currentPlayerLabelText, c);

		c.gridx = 1;
		c.gridy = 1;
		choice.add(fakeLabel, c);

		c.gridx = 0;
		c.gridy = 2;
		choice.add(new JLabel(" "), c);

		c.gridx = 0;
		c.gridy = 3;
		choice.add(wins, c);

		c.gridx = 0;
		c.gridy = 4;
		choice.add(player1Label, c);

		c.gridx = 1;
		c.gridy = 4;
		choice.add(player1LabelText, c);

		c.gridx = 0;
		c.gridy = 5;
		choice.add(player2Label, c);

		c.gridx = 1;
		c.gridy = 5;
		choice.add(player2LabelText, c);

		c.gridx = 0;
		c.gridy = 6;
		choice.add(new JLabel(" "), c);

		c.gridx = 0;
		c.gridy = 7;
		choice.add(remove, c);

		c.gridx = 0;
		c.gridy = 8;
		choice.add(new JLabel(" "), c);

		c.gridx = 0;
		c.gridy = 9;
		choice.add(newRound, c);

		c.gridx = 5;
		c.gridy = 0;
		choice.add(new JLabel(" "), c);

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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.remove) {
			removeToken();
		} else if (e.getSource() == this.newRound) {
			createGame();
			return;
		} else if (e.getSource() == this.newGame) {
			newGame();
		} else if (e.getSource() == this.exit) {
			System.exit(0);
		} else if (e.getSource() == this.help) {
			help();
		} else if (e.getSource() == this.menuRound) {
			createGame();
		} else {
			position = (JButton) e.getSource();
			lastPosition = position;
			String[] tmp = position.getName().split(",");
			char status = controller.setToken(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
			putStone(status);
			controller.changePlayer(controller.getCounter());

		}
		currentPlayerLabelText.setText(controller.getcPlayer().getName());
		currentPlayerLabelText.setForeground(controller.getcPlayer().getColor());
	}

	private void removeToken() {
		controller.removeToken();
		lastPosition.setBackground(new JButton().getBackground());
		remove.setEnabled(false);
	}

	private void help() {
		JOptionPane.showMessageDialog(null,
				"Go Bang is a strategy board game for two players from Japane. "
						+ "\nIt is played on a board of 19 x 19 fields. The players aim to align five "
						+ "\nstones of the same token suite in vertical, horizontal or diagonal lines.",
				"Help", JOptionPane.OK_OPTION);
	}

	private void putStone(char s) {

		switch (s) {
		case 'b':
			JOptionPane.showMessageDialog(null, "Already used", "Wrong Field", JOptionPane.OK_OPTION);
			break;
		case 'e':
			remove.setEnabled(true);
			break;
		case 'g':
			win(position);
			break;
		default:
			break;
		}
	}
	
	private void win(JButton position){
		JOptionPane.showMessageDialog(null, "Player " + controller.getcPlayer().getName() + " you won!", "Win",
				JOptionPane.OK_OPTION);
		player1LabelText.setText(new Integer(controller.getWinPlayer1()).toString());
		player2LabelText.setText(new Integer(controller.getWinPlayer2()).toString());
		changeButtons(false);
		newRound.setEnabled(true);
	}

	private void changeButtons(boolean state) {
		
		for (int i = 0; i < 19; i++){
			for (int y = 0; y < 19; y++){
				if (state) {
					buttonField[i][y].setBackground(new JButton().getBackground());
				}
				buttonField[i][y].setEnabled(state);
			}
		}
		this.remove.setEnabled(state);
		this.newRound.setEnabled(false);
		currentPlayerLabelText.setVisible(state);
		currentPlayerLabelText.setForeground(controller.getcPlayer().getColor());
	}

	private void createGame() {
		if (controller.getcPlayer() == controller.getPlayer1()) {
			controller.newGame(true);
		} else {
			controller.newGame(false);
		}
		changeButtons(true);
		currentPlayerLabelText.setText(controller.getcPlayer().getName());
	}

	private void newGame() {
		createGame();
		player1LabelText.setText(new Integer(controller.getWinPlayer1()).toString());
		player2LabelText.setText(new Integer(controller.getWinPlayer2()).toString());
	}

	@Override
	public void update() {
		tokenField = controller.getField();
		for (int i = 0; i < 19; i++){
			for (int n = 0; n < 19; n++){
				if (tokenField[i][n].getName().equals("none")){
					buttonField[i][n].setBackground(new JButton().getBackground());
				} else {
					buttonField[i][n].setBackground(tokenField[i][n].getColor());
				}
			}
		}
	}

}
