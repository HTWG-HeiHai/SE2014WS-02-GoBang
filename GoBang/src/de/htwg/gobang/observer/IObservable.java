package de.htwg.gobang.observer;

import de.htwg.gobang.entities.IGameToken;

public interface IObservable {

	void addObserver(IObserver s);

	void removeObserver(IObserver s);

	void removeAllObservers();

	void notifyObservers();
}
