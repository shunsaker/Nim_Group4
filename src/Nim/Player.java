package Nim;

public abstract class Player {
public final String NAME;
	
	public Player(String name) {
		NAME = name;
	}
	
	@Override
	public String toString() {
		return NAME;
	}
	
	abstract Move getMove(Board board);
	abstract boolean playAgain();
}
