package de.htwg.gobang.actor_example.actors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import akka.actor.UntypedActor;
import de.htwg.gobang.actor_example.messages.MapData;
import de.htwg.gobang.actor_example.messages.WordCount;

public class MapActor extends UntypedActor {

	public final String[] STOP_WORDS = {"a", "am", "an", "and", "are", "as", "at",
			"be", "do", "go", "if", "in", "is", "it", "of", "on", "the", "to"};
	private final List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String) {
			String work = (String) message;
			getSender().tell(evaluateExpression(work), getSelf());
		} else {
			unhandled(message);
		}
	}

	private MapData evaluateExpression(String line) {
		List<WordCount> dataList = new ArrayList<WordCount>();
		StringTokenizer parser = new StringTokenizer(line);
		while(parser.hasMoreTokens()) {
			String word = parser.nextToken().toLowerCase();
			if(!STOP_WORDS_LIST.contains(word)) {
				dataList.add(new WordCount(word, Integer.valueOf(1)));
			}
		}
		return new MapData(dataList);
	}
}
