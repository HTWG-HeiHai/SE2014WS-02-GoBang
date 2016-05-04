package de.htwg.gobang.messages;

import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.IToken;

public final class SetToken {

	private IPlayer player;
	private int x;
	private int y;
	private IToken[][] matrix;
	private int direction;

	public SetToken(IPlayer player, int x, int y, IToken[][] matrix) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.matrix = matrix;
	}

	public SetToken(IPlayer player, int x, int y, IToken[][] matrix, int direction) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.matrix = matrix;
		this.direction = direction;
	}

	public IPlayer getPlayer() {
		return player;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public IToken[][] getMatrix() {
		return matrix;
	}

	public int getDirection() {
		return direction;
	}

	public SetToken withDirection(int direction) {
		this.direction = direction;
		return new SetToken(player, x, y, matrix, direction);
	}
}
