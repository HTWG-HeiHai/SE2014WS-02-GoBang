package de.htwg.gobang;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import de.htwg.gobang.model.IToken;

public class CheckDirectionActor extends UntypedActor {

	private final int fieldsize = 19;
	private IToken[][] matrix;

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object message) throws Exception {
		log.info("Received message: {}", message);
		if(message instanceof SetToken) {
			SetToken msg = (SetToken) message;
			matrix = msg.getMatrix();
			int x = msg.getX();
			int y = msg.getY();
			IToken token = matrix[x][y];

			switch(msg.getDirection()) {
				case 1:
					getSender().tell(checkDirection(x, 0, 0, y, -1, 1, token), getSelf());
					break;
				case 2:
					getSender().tell(checkDirection(x, -1, 1, y, 0, 0, token), getSelf());
					break;
				case 3:
					getSender().tell(checkDirection(x, -1, 1, y, -1, 1, token), getSelf());
					break;
				case 4:
					getSender().tell(checkDirection(x, 1, -1, y, -1, 1, token), getSelf());
					break;
				default:
					unhandled(message);
			}
		} else {
			unhandled(message);
		}
	}

	public boolean checkDirection(int x, int dX, int dX2, int y, int dY, int dY2, IToken token) {
		if (checkWay(x, dX, y, dY, token) + checkWay(x, dX2, y, dY2, token) >= 4) {
			return true;
		}
		return false;
	}

	public int checkWay(int x, int dX, int y, int dY, IToken token) {
		int tmpCounter = 0;
		if (checkStep(x + dX, y + dY, token)) {
			tmpCounter++;
			tmpCounter += checkWay(x+dX, dX, y+dY, dY, token);
		}
		return tmpCounter;
	}

	public boolean checkStep(int x, int y, IToken token) {
		if (isValid(x, y) && matrix[x][y].getName().equals(token.getName())) {
			return true;
		}
		return false;
	}

	public boolean isValid(int x, int y) {

		if (x >= fieldsize || x < 0 || y >= fieldsize || y < 0) {
			return false;
		}
		return true;
	}

}
