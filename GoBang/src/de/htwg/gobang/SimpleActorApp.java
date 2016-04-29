package de.htwg.gobang;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.gobang.controller.impl.ActorController;
import de.htwg.gobang.model.IPlayer;
import de.htwg.gobang.model.impl.Player;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class SimpleActorApp {

	public static void main(String[] args) throws Exception {
//		Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
//		ActorSystem _system = ActorSystem.create("GoBangActorSystem");
//		ActorRef master = _system.actorOf(Props.create(GameActor.class), "game");
//		
////		Future<Object> future = Patterns.ask(master,  new Player("asdf"), timeout);
////		IPlayer result = (IPlayer) Await.result(future, timeout.duration());
////		System.out.println(result.getId());
//		IPlayer result = new Player("asdf");
//		master.tell(new NewGame(result), ActorRef.noSender());
//		
//		Thread.sleep(5000);
		new ActorController();
	}
}
