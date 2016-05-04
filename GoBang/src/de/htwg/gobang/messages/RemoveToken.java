package de.htwg.gobang.messages;

import de.htwg.gobang.model.IPlayer;

public final class RemoveToken {

	private IPlayer player;
	private int x;
	private int y;

	public RemoveToken(IPlayer player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
	}

	IPlayer getPlayer() {
		return player;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}
}
