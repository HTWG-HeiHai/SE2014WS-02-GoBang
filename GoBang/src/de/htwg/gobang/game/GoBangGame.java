package de.htwg.gobang.game;
import java.util.Observable;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.dao.IGameDao;
import de.htwg.gobang.dao.hibernate.HibernateGameDao;
import de.htwg.gobang.ui.GUI;
import de.htwg.gobang.ui.TUI;

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
		injector.getInstance(GUI.class);
		tui = injector.getInstance(TUI.class);
	}

	public static void main(String[] args) {
		GoBangGame game = GoBangGame.getInstance();
		IGameDao dao = new HibernateGameDao();
		dao.saveOrUpdateGame(game.getController());
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
