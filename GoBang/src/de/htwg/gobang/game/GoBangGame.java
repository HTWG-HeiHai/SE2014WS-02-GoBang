package de.htwg.gobang.game;
import java.util.Observable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.java_cup.internal.runtime.Scanner;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.ui.GUI;
import de.htwg.gobang.ui.TUI;

public class GoBangGame extends Observable{

	private static GoBangGame instance = null;
	private IGbLogic controller;
	private TUI tui;

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
		GoBangGame gbg = GoBangGame.getInstance();
	}

	public IGbLogic getController() {
		return controller;
	}

	public TUI getTui() {
		return tui;
	}

}
