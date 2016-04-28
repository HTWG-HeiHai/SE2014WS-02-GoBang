package de.htwg.gobang;
import java.util.Observable;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IPlayerDao;
import de.htwg.gobang.dao.hibernate.HibernatePlayerDao;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.view.GUI;
import de.htwg.gobang.view.TUI;

public class GoBangGame extends Observable{

	private static GoBangGame instance = null;

	private TUI tui;
	private Injector injector;
	private IGbLogic controller;

	public static GoBangGame getInstance() {
	if (instance == null) {
		instance = new GoBangGame();
	}
		return instance;
	}

	private GoBangGame() {
		Injector injector = Guice.createInjector(new GoBangModule());
		controller = injector.getInstance(IGbLogic.class);
		injector.getInstance(GUI.class);
		tui = injector.getInstance(TUI.class);
	}

	public static void main(String[] args) {
		GoBangGame.getInstance();
//		IPlayerDao dao = new HibernatePlayerDao();
//		dao.saveOrUpdatePlayer(game.getController().getPlayer1());
//		dao.saveOrUpdatePlayer(game.getController().getPlayer2());
//		for(IGamePlayer gp : dao.listAllPlayers()) {
//			System.out.println(gp.getId());
//			System.out.println(gp.getName());
//			System.out.println(gp.getWins());
//			System.out.println(gp.getLosses());
//			System.out.println(gp.getEnemies() + "\n");
//		}
		
//		IGameDao dao = new HibernateGameDao();
//		dao.saveOrUpdateGame(game.getController());
	}

    public Injector getIn() {
        return injector;
    }

	public TUI getTui() {
		return tui;
	}
	
	public IGbLogic getController() {
		return controller;
	}
}
