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
import de.htwg.gobang.observer.IObserver;

public class GUI extends JFrame implements ActionListener, IObserver {

	private static final long serialVersionUID = 1L;

	private IGbLogic controller;
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
	private static final int EIGHT = 8;
	private static final int NINE = 9;

	@Inject
	public GUI(IGbLogic engine) {
		controller = engine;
		controller.addObserver(this);

		JPanel choice;
		JMenuBar menuBar;
		JMenu menu;

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
		group = new ButtonGroup();

		g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.ipadx = FIVE;
		g.ipady = FIVE;
		g.weightx = EIGHT;

		for (int i = 1; i < LENGTH; i++) {
			for (int k = 1; k < LENGTH; k++) {
				g.gridx = i;
				g.gridy = k;
				position = new JButton();
				position.setName((i-1) + "," + (k-1));
				gameField.add(position, g);
				position.addActionListener(this);
				group.add(position);
			}
		}

		g.gridx = LENGTH;
		g.gridy = ZERO;
		gameField.add(new JLabel(" "), g);

		g.gridx = LENGTH;
		g.gridy = LENGTH;
		gameField.add(new JLabel(" "), g);

		g.gridx = ZERO;
		g.gridy = LENGTH;
		gameField.add(new JLabel(" "), g);

		// Choice
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
		player1LabelText = new JLabel(new Integer(cp1).toString());
		player2LabelText = new JLabel(new Integer(cp2).toString());
		player1LabelText.setForeground(controller.getPlayer1().getColor());
		player2LabelText.setForeground(controller.getPlayer2().getColor());
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
		choice.add(currentPlayerLabel, c);

		c.gridx = ONE;
		c.gridy = ZERO;
		choice.add(currentPlayerLabelText, c);

		c.gridx = ONE;
		c.gridy = ONE;
		choice.add(fakeLabel, c);

		c.gridx = ZERO;
		c.gridy = TWO;
		choice.add(new JLabel(" "), c);

		c.gridx = ZERO;
		c.gridy = THREE;
		choice.add(wins, c);

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
		choice.add(new JLabel(" "), c);

		c.gridx = ZERO;
		c.gridy = SEVEN;
		choice.add(remove, c);

		c.gridx = ZERO;
		c.gridy = EIGHT;
		choice.add(new JLabel(" "), c);

		c.gridx = ZERO;
		c.gridy = NINE;
		choice.add(newRound, c);

		c.gridx = FIVE;
		c.gridy = ZERO;
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

		char status = s;

		switch (status) {
		case 'b':
			JOptionPane.showMessageDialog(null, "Already used", "Wrong Field", JOptionPane.OK_OPTION);
			break;
		case 'e':
			position.setBackground(controller.getcPlayer().getColor());
			remove.setEnabled(true);
			break;
		case 'g':
			position.setBackground(controller.getcPlayer().getColor());
			JOptionPane.showMessageDialog(null, "Player " + controller.getcPlayer().getName() + " you won!", "Win",
					JOptionPane.OK_OPTION);
			if (controller.getcPlayer() == controller.getPlayer1()) {
				cp1 += 1;
				player1LabelText.setText(new Integer(cp1).toString());
			} else {
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

	private void changeButtons(boolean state) {
		Enumeration<AbstractButton> tbutton = group.getElements();
		AbstractButton e = tbutton.nextElement();
		try {
			if (state) {
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
		cp1 = 0;
		cp2 = 0;
		player1LabelText.setText(new Integer(cp1).toString());
		player2LabelText.setText(new Integer(cp2).toString());
	}

	@Override
	public void update(char s, int x, int y) {
		currentPlayerLabelText.setText("blaaaa");
		if (s != 'r') {
			String tmp = Integer.toString(x) + "," + Integer.toString(y);
			Enumeration<AbstractButton> tbutton = group.getElements();
			JButton e = (JButton) tbutton.nextElement();
			do {
				if (e.getName().equals(tmp)) {
					position = e;
					break;
				}
				e = (JButton) tbutton.nextElement();
			} while (e != null);
			putStone(s);
		} else
			removeToken();
		// currentPlayerLabelText.setText(myGame.getcPlayer().getName());
		// currentPlayerLabelText.setForeground(myGame.getcPlayer().getColor());
	}

}
