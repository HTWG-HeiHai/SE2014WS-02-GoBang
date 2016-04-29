package de.htwg.gobang.controller.impl;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.gobang.GameActor;
import de.htwg.gobang.SetToken;
import de.htwg.gobang.controller.IChecker;
import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.model.impl.Field;
import de.htwg.gobang.model.impl.Player;
import de.htwg.gobang.util.observer.impl.MyObserverable;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class ActorController extends MyObserverable implements IGbLogic {

	private Timeout timeout;
	private ActorSystem goBangActorSystem;
	private ActorRef master;
	
	private IField myField;
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer cPlayer;
	private int lastX;
	private int lastY;
	private int counter;
	private char status;
	private IPlayerDao dao;
	
	public ActorController() {
		timeout = new Timeout(1, TimeUnit.SECONDS);
		goBangActorSystem = ActorSystem.create("GoBangActorSystem");
		master = goBangActorSystem.actorOf(Props.create(GameActor.class), "game");
		myField = new Field();
		cPlayer = new Player("tztztu");
		setToken(2, 3);
		setToken(2, 4);
		setToken(2, 5);
		setToken(2, 6);
		setToken(2, 7);
		System.out.println("feddich");
	}

	@Override
	public void newGame(boolean pStartplayer) {
		Future<Object> future = Patterns.ask(master,  "", timeout);
		IPlayer result = null;
		try {
			result = (IPlayer) Await.result(future, timeout.duration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

	@Override
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

		Future<Object> future = Patterns.ask(master,  new SetToken(cPlayer, x, y, myField.getGameField()), timeout);
		boolean result = false;
		try {
			result = (boolean) Await.result(future, timeout.duration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result) {
			status = 'g';
		} else {
			status = 'e';
		}
		System.out.println(result);
		notifyObservers();
		return status;
	}

	@Override
	public void changePlayer(int counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPlayer getcPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IToken[][] getField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeToken() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IPlayer getPlayer1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPlayer getPlayer2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWinPlayer1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWinPlayer2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getcColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField getGameField() {
		// TODO Auto-generated method stub
		return null;
	}

}
