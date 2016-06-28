package de.htwg.gobang.actors;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.util.Timeout;
import de.htwg.gobang.messages.RemoveToken;
import de.htwg.gobang.messages.SetToken;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class CheckActor extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	final ActorRef checkWorker = getContext().actorOf(Props.create(CheckDirectionActor.class).withRouter(new RoundRobinPool(4)), "checkWorker");

	Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

	@Override
	public void onReceive(Object message) throws Exception {
		//log.info("Received message: {}", message.getClass());
		if(message instanceof SetToken) {
			SetToken msg = (SetToken) message;
			Future<Object> future1 = Patterns.ask(checkWorker, msg.withDirection(1), timeout);
			Future<Object> future2 = Patterns.ask(checkWorker, msg.withDirection(2), timeout);
			Future<Object> future3 = Patterns.ask(checkWorker, msg.withDirection(3), timeout);
			Future<Object> future4 = Patterns.ask(checkWorker, msg.withDirection(4), timeout);
			boolean res1 = (boolean) Await.result(future1, timeout.duration());
			boolean res2 = (boolean) Await.result(future2, timeout.duration());
			boolean res3 = (boolean) Await.result(future3, timeout.duration());
			boolean res4 = (boolean) Await.result(future4, timeout.duration());
			getSender().tell(res1 || res2 || res3 || res4, getSelf());
		} else if(message instanceof RemoveToken) {
			
		} else {
			unhandled(message);
		}
		
	}

}
