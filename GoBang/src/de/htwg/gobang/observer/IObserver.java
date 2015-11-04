package de.htwg.gobang.observer;

import de.htwg.gobang.entities.IGameToken;

public interface IObserver {

	public void update(char action, IGameToken player, int x, int y);
}
