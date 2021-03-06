package de.htwg.gobang.actors;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import akka.routing.RoundRobinPool;
import akka.util.Timeout;
import de.htwg.gobang.messages.NewGame;
import de.htwg.gobang.messages.RemoveToken;
import de.htwg.gobang.messages.SetToken;
import de.htwg.gobang.model.IPlayer;

public class GameActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	final ActorRef fieldCreator = getContext()
			.actorOf(Props.create(FieldCreateActor.class).withRouter(new RoundRobinPool(5)), "fieldCreator");

	final ActorRef playerBuilder = getContext()
			.actorOf(Props.create(PlayerBuildActor.class).withRouter(new RoundRobinPool(5)), "playerBuilder");

	final ActorRef checker = getContext().actorOf(Props.create(CheckActor.class).withRouter(new RoundRobinPool(5)),
			"checker");

	final ActorRef dao = getContext().actorOf(Props.create(CouchDbDaoActor.class).withRouter(new RoundRobinPool(5)),
			"dao");

	Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

	Procedure<Object> player1 = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			if (message.equals("bar")) {
				getSender().tell("I am already angry?", getSelf());
			} else if (message.equals("foo")) {
				getContext().become(player2);
			}
		}
	};

	Procedure<Object> player2 = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			if (message.equals("bar")) {
				getSender().tell("I am already happy :-)", getSelf());
			} else if (message.equals("foo")) {
				getContext().become(player1);
			}
		}
	};

	@Override
	public void onReceive(Object message) throws Exception {
		//log.info("Received String message: {}", message);
		if (message instanceof NewGame) {
			fieldCreator.forward(message, getContext());
//			getContext().become(((NewGame)message).getLastPlayer());
		} else if (message instanceof SetToken) {
			checker.forward(message, getContext());
		} else if (message instanceof RemoveToken) {
			checker.forward(message, getContext());
		} else if (message instanceof String) {
			dao.forward(message, getContext());
		} else if (message instanceof IPlayer) {
			dao.forward(message, getContext());
		} else
			unhandled(message);
	}

}
