package de.htwg.gobang.controller.impl;

import java.awt.Color;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.htwg.gobang.GoBangModule;
import de.htwg.gobang.controller.IChecker;
import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.util.observer.impl.Observerable;

@Singleton
public class GbLogic extends Observerable implements IGbLogic {

	private IField myField;
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer cPlayer;
	private IChecker myChecker;
	private int lastX;
	private int lastY;
	private int counter;
	private char status;
	private IPlayerDao dao;
	private Injector injector;

	@Inject
	public GbLogic() {
		this(true);// hm,
	}

	public GbLogic(boolean pStartplayer) {
		injector = Guice.createInjector(new GoBangModule());
		dao = injector.getInstance(IPlayerDao.class);
		player1 = createOrLoadPlayer("qwer");
		player2 = createOrLoadPlayer("asdf");
		// player1 = dao.getPlayerById(1888186442);//
		// player2 = dao.getPlayerById(2010019457);//
		newGame(pStartplayer);
	}

	private IPlayer createOrLoadPlayer(String name) {
		List<IPlayer> list = dao.getPlayersByName(name);
		IPlayer player;
		if(list == null) {
			player = injector.getInstance(IPlayer.class);
			player.setName(name);
		} else {
			player = list.get(0);//?naja
		}
		return player;
	}

	public void newGame(boolean pStartplayer) {
		myField = injector.getInstance(IField.class);
		myChecker = new Checker(myField.getGameField());
		counter = 0;
		if (pStartplayer) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
			counter++;
		}
		lastX = 1;
		lastY = 1;
		status = 'n';
		notifyObservers();
		for (IPlayer gp : dao.listAllPlayers()) {
			System.out.println(gp.getId());
			System.out.println(gp.getName());
			System.out.println(gp.getWinsTotal());
			System.out.println(gp.getLossesTotal());
			System.out.println(gp.getResults() + "\n");
		}
	}

	public char getStatus() {
		return status;
	}

	public char setToken(int x, int y) {
		status = myField.putStone(x, y);
		myField.getGameField()[x][y].setColor(getcColor());
		myField.getGameField()[x][y].setName(cPlayer.getName());
		if (status == 'f' || status == 'b') {
			notifyObservers();
			return status;
		}
		counter++;
		lastX = x;
		lastY = y;
		status = getWin(x, y, cPlayer);
		notifyObservers();
		return status;
	}

	public Color getcColor() {
		if (cPlayer.equals(player1)) {
			return Color.BLACK;
		}
		return Color.BLUE;
	}

	public void changePlayer(int counter) {
		if (counter % 2 == 0) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
		}
	}

	public IPlayer getcPlayer() {
		return cPlayer;
	}

	public int getWinPlayer1() {
		return player1.getWinsAgainst(player2.getId());
	}

	public int getWinPlayer2() {
		return player2.getWinsAgainst(player1.getId());
	}

	public boolean removeToken() {
		status = myField.removeToken(lastX, lastY);
		if (status == 'f') {
			notifyObservers();
			return false;
		}
		counter--;
		changePlayer(counter);
		notifyObservers();
		return true;
	}

	public IPlayer getPlayer1() {
		return player1;
	}

	public IPlayer getPlayer2() {
		return player2;
	}

	public Color getColor1() {
		return Color.BLACK;
	}

	public Color getColor2() {
		return Color.BLUE;
	}

	private char getWin(int x, int y, IPlayer player) {
		changePlayer(counter);
		if (myChecker.checkWin(x, y, myField.getGameField()[x][y])) {
			if (player.equals(player1)) {
				player.addWinAgainst(player2.getId());
				player2.addLossAgainst(player.getId());
			} else {
				player.addWinAgainst(player1.getId());
				player1.addLossAgainst(player.getId());
			}
			 dao.saveOrUpdatePlayer(player1);//
			 dao.saveOrUpdatePlayer(player2);//
			return 'g';
		}
		return 'e';
	}

	@Override
	public IToken[][] getField() {
		return myField.getGameField();
	}

	@Override
	public IField getGameField() {
		return myField;
	}
}
