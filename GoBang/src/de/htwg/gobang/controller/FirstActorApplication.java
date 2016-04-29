package de.htwg.gobang.controller;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.htwg.gobang.actor_example.actors.MasterActor;
import de.htwg.gobang.actor_example.messages.Result;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class FirstActorApplication {

	public static void main(String[] args) throws Exception {
		Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
		ActorSystem _system = ActorSystem.create("FirstActorApp");

		ActorRef master = _system.actorOf(Props.create(MasterActor.class, "master"));

		master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", ActorRef.noSender());
		master.tell("Dog is man's best friend", ActorRef.noSender());
		master.tell("Dog and Fox belong to the same family", ActorRef.noSender());

		Thread.sleep(5000);

		Future<Object> future = Patterns.ask(master,  new Result(), timeout);
		String result = (String) Await.result(future, timeout.duration());
		System.out.println(result);
//		_system.shutdown();
	}
}
