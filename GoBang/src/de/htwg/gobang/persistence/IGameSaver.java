package de.htwg.gobang.persistence;

import com.google.inject.Injector;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGameField;
import de.htwg.gobang.entities.IGamePlayer;

public interface IGameSaver {

	IGamePlayer getPlayer1();

	void setPlayer1(IGamePlayer player1);

	IGamePlayer getPlayer2();

	void setPlayer2(IGamePlayer player2);

	char getStatus();

	void setStatus(char status);

	IGamePlayer getCurrentPlayer();
	
	void setCurrentPlayer(IGamePlayer player);

	IGameField getGameField();

	void setGameField(IGameField field);

	IGbLogic restoreGame(Injector injector);

	void saveGame(IGbLogic controller);
}
