package de.htwg.gobang;
import java.util.Observable;

import com.google.inject.Guice;
import com.google.inject.Injector;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import de.htwg.gobang.controller.IGbLogic;
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
		ActorSystem _system = ActorSystem.create("GoBangActorSystem");
		ActorRef master = _system.actorOf(Props.create(GameActor.class), "game");
		master.tell("start", ActorRef.noSender());
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
