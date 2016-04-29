package de.htwg.gobang.controller;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MasterActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	final ActorRef child = getContext().actorOf(Props.create(FirstUntypedActor.class), "firstChild");

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			log.info("Received String message: {}", message);
			getSender().tell(message, getSelf());
		} else
			unhandled(message);
	}
}
