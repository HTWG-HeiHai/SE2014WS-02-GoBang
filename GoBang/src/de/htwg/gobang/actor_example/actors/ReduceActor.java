package de.htwg.gobang.actor_example.actors;

import java.util.HashMap;
import java.util.List;

import akka.actor.UntypedActor;
import de.htwg.gobang.actor_example.messages.MapData;
import de.htwg.gobang.actor_example.messages.ReduceData;
import de.htwg.gobang.actor_example.messages.WordCount;

public class ReduceActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof MapData) {
			MapData mapData = (MapData) message;
			getSender().tell(reduce(mapData.getDataList()), getSelf());
		} else {
			unhandled(message);
		}
	}

	private ReduceData reduce(List<WordCount> dataList) {
		HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
		for(WordCount wordCount : dataList) {
			if(reducedMap.containsKey(wordCount.getWord())) {
				Integer value = (Integer) reducedMap.get(wordCount.getWord());
				value++;
				reducedMap.put(wordCount.getWord(), value);
			} else {
				reducedMap.put(wordCount.getWord(), Integer.valueOf(1));
			}
		}
		return new ReduceData(reducedMap);
	}
}
