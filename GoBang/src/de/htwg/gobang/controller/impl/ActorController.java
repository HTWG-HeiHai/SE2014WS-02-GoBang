package de.htwg.gobang.controller.impl;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.gobang.GameActor;
import de.htwg.gobang.GoBangModule;
import de.htwg.gobang.SetToken;
import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.model.IField;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;
import de.htwg.gobang.util.observer.impl.Observerable;
import scala.concurrent.Await;
import scala.concurrent.Future;

@Singleton
public class ActorController extends Observerable implements IGbLogic {

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
	private Injector injector;

	@Inject
	public ActorController() {
		this(true);
	}

	public ActorController(boolean pStartplayer) {
		timeout = new Timeout(1, TimeUnit.SECONDS);
		goBangActorSystem = ActorSystem.create("GoBangActorSystem");
		master = goBangActorSystem.actorOf(Props.create(GameActor.class), "game");

		injector = Guice.createInjector(new GoBangModule());
		dao = injector.getInstance(IPlayerDao.class);
		player1 = createOrLoadPlayer("asdf");
		player2 = createOrLoadPlayer("qwer");
		newGame(pStartplayer);
		// cPlayer = new Player("tztztu");
		// setToken(2, 3);
		// setToken(2, 4);
		// setToken(2, 5);
		// setToken(2, 6);
		// setToken(2, 7);
	}

	private IPlayer createOrLoadPlayer(String name) {
		List<IPlayer> list = dao.getPlayersByName(name);
		IPlayer player;
		if (list == null) {
			player = injector.getInstance(IPlayer.class);
			player.setName(name);
		} else {
			player = list.get(0);// ?naja
		}
		return player;
	}

	private char getWin(int x, int y, IPlayer player) {
		changePlayer(counter);
		Future<Object> future = Patterns.ask(master, new SetToken(cPlayer, x, y, myField.getGameField()), timeout);
		boolean result = false;
		try {
			result = (boolean) Await.result(future, timeout.duration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result) {
			if (player.equals(player1)) {
				player.addWinAgainst(player2.getId());
				player2.addLossAgainst(player.getId());
			} else {
				player.addWinAgainst(player1.getId());
				player1.addLossAgainst(player.getId());
			}
			status = 'g';
			
			master.tell(player1, ActorRef.noSender());
			master.tell(player2, ActorRef.noSender());
//			dao.saveOrUpdatePlayer(player1);//
//			dao.saveOrUpdatePlayer(player2);//
		} else {
			status = 'e';
		}
		System.out.println(result);
		return status;
	}

	@Override
	public void newGame(boolean pStartplayer) {
		myField = injector.getInstance(IField.class);
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
		getWin(x, y, cPlayer);
		notifyObservers();
		return status;
	}

	@Override
	public void changePlayer(int counter) {
		if (counter % 2 == 0) {
			cPlayer = player1;
		} else {
			cPlayer = player2;
		}
	}

	@Override
	public IPlayer getcPlayer() {
		return cPlayer;
	}

	@Override
	public IToken[][] getField() {
		return myField.getGameField();
	}

	@Override
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

	@Override
	public IPlayer getPlayer1() {
		return player1;
	}

	@Override
	public IPlayer getPlayer2() {
		return player2;
	}

	@Override
	public Color getColor1() {
		return Color.BLACK;
	}

	@Override
	public Color getColor2() {
		return Color.BLUE;
	}

	@Override
	public int getWinPlayer1() {
		return player1.getWinsAgainst(player2.getId());
	}

	@Override
	public int getWinPlayer2() {
		return player2.getWinsAgainst(player1.getId());
	}

	@Override
	public char getStatus() {
		return status;
	}

	@Override
	public Color getcColor() {
		if (cPlayer.equals(player1)) {
			return Color.BLACK;
		}
		return Color.BLUE;
	}

	@Override
	public IField getGameField() {
		return myField;
	}

}
