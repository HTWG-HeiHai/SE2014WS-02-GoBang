package de.htwg.gobang.controller.impl;

import java.awt.Color;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.gobang.controller.IChecker;
import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.dao.couchdb.CouchDbPlayerDao;
import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGamePlayer;
import de.htwg.gobang.entities.IGameToken;
import de.htwg.gobang.entities.impl.GameField;
import de.htwg.gobang.entities.impl.GamePlayer;
import de.htwg.gobang.observer.MyObserverable;
import de.htwg.gobang.persistence.IGameSaver;

@Singleton
public class GbLogic extends MyObserverable implements IGbLogic {

	private IGameField myField;
	private IGamePlayer player1;
	private IGamePlayer player2;
	private IGamePlayer cPlayer;
	private IChecker myChecker;
	private int lastX;
	private int lastY;
	private int counter;
	private char status;
	private IPlayerDao dao;

	@Inject
	public GbLogic() {
		this(true, new CouchDbPlayerDao());//hm,
	}

	public GbLogic(boolean pStartplayer, IPlayerDao dao) {
		player1 = new GamePlayer("qwer");
		player2 = new GamePlayer("asdf");
		this.dao = dao;
//		player1 = dao.getPlayerById(1888186442);//
//		player2 = dao.getPlayerById(2010019457);//
		newGame(pStartplayer);
	}

	public void newGame(boolean pStartplayer) {
		myField = new GameField();
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
		for(IGamePlayer gp : dao.listAllPlayers()) {
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
		if(cPlayer.equals(player1)) {
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

	public IGamePlayer getcPlayer() {
		return cPlayer;
	}
	
	public int getWinPlayer1(){
		return player1.getWinsAgainst(player2.getId());
	}
	
	public int getWinPlayer2(){
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

	public IGamePlayer getPlayer1() {
		return player1;
	}

	public IGamePlayer getPlayer2() {
		return player2;
	}
	
	public Color getColor1() {
		return Color.BLACK;
	}

	public Color getColor2() {
		return Color.BLUE;
	}

	private char getWin(int x, int y, IGamePlayer player) {
		changePlayer(counter);
		if (myChecker.checkWin(x, y, myField.getGameField()[x][y]))
		{ 
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
	public IGameToken[][] getField() {
		return myField.getGameField();
	}
	
    @Override
    public final void restoreGame(IGameSaver saver) {
        player1.setName(saver.getPlayer1().getName());
        player2.setName(saver.getPlayer2().getName());
        status = saver.getStatus();
        myField = saver.getGameField();
    }

	@Override
	public IGameField getGameField() {
		return myField;
	}
}
