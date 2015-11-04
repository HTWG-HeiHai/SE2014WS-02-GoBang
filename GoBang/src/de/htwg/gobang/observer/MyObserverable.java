package de.htwg.gobang.observer;

import java.util.ArrayList;
import java.util.List;

import de.htwg.gobang.entities.IGameToken;

public class MyObserverable implements IObservable {
	
	private List<IObserver> subscriber = new ArrayList<IObserver>(2); 

	public void addObserver(IObserver s) {
		subscriber.add(s);
	}
	
	public void removeObserver(IObserver s){
		subscriber.remove(s);
	}

	public void removeAllObservers(){
		subscriber.clear();
	}

	public void notifyObservers(char action, IGameToken player) {		
		for (IObserver myob : subscriber) {
			IObserver observer = myob;
			observer.update(action, player);
		}
	}
}
