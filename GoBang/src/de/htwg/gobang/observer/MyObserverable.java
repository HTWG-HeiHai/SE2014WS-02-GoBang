package de.htwg.gobang.observer;

import java.util.ArrayList;
import java.util.List;

public class MyObserverable implements IObservable {
	
	protected List<IObserver> subscriber = new ArrayList<IObserver>(2); 

	public void addObserver(IObserver s) {
		subscriber.add(s);
	}
	
	public void removeObserver(IObserver s){
		subscriber.remove(s);
	}

	public void removeAllObservers(){
		subscriber.clear();
	}

	public void notifyObservers(char s, int x, int y) {
		
		for (IObserver myob : subscriber) {
			IObserver observer = myob;
			observer.update();
		}
	}
}
