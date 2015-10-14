package de.htwg.gobang.game;
import java.util.Observable;

import de.htwg.gobang.controller.GbLogic;
import de.htwg.gobang.observer.MyObserverable;
import de.htwg.gobang.ui.GUI;
import de.htwg.gobang.ui.TUI;

public class GoBangGame extends Observable{

	public static void main(String[] args) {
			
		GbLogic myController = new GbLogic(true);
		MyObserverable obs = new MyObserverable();
		GUI myGUI = new GUI(myController);
		obs.addObserver(myGUI);
		TUI myTUI = new TUI(myController);
		obs.addObserver(myTUI);
	}
}
