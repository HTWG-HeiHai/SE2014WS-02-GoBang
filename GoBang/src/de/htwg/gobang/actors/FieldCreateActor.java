package de.htwg.gobang.actors;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import de.htwg.gobang.actor_example.messages.Result;
import de.htwg.gobang.model.impl.Field;

public class FieldCreateActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object message) throws Exception {
		//log.info("Received String message: {}", message);
		if (message instanceof String) {

		} else if(message instanceof Result) {
			getSender().tell(new Field().getGameField()[1][2].getName(), getSelf());
		} else {
			unhandled(message);
		}
	}
}
