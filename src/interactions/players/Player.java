package interactions.players;

import model.Board;
import model.Move;

public abstract class Player {
public final String NAME;
	
	public Player(String name) {
		NAME = name;
	}
	
	@Override
	public String toString() {
		return NAME;
	}
	
	abstract public Move getMove(Board board);
	abstract public boolean playAgain();
}
