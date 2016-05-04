package de.htwg.gobang.messages;

import de.htwg.gobang.model.IPlayer;

public class NewGame {

	private IPlayer lastPlayer;

	public NewGame(IPlayer lastPlayer) {
		this.lastPlayer = lastPlayer;
	}

	public IPlayer getLastPlayer() {
		return lastPlayer;
	}
}
