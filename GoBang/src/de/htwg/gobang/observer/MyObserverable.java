package de.htwg.gobang.observer;

import java.util.ArrayList;
import java.util.List;

public class MyObserverable {
	
	protected List<IObserver> subscriber = new ArrayList<IObserver>(2); 

	public void addObserver(IObserver s) {
		subscriber.add(s);
	}
	
	void removeObserver(IObserver s){
		subscriber.remove(s);
	}

	void removeAllObservers(){
		subscriber.clear();
	}
	
	public void notifyObserver(char s, int x, int y) {
		
		for (IObserver myob : subscriber) {
			IObserver observer = myob;
			observer.update(s, x, y);
		}
	}
}
