package interactions.players;

import java.util.List;

import model.Board;
import model.Move;
import Nim.util.AIUtils;
import Nim.util.KnowledgeMap;

public class Computer extends Player{
	private int playTimes = 0;

	public Computer(String name) {
		super(name);
	}
	
	public Computer(String name, int playTimes) {
		super(name);
		this.playTimes = playTimes;
	}

	@Override
	public Move getMove(Board board) {
		KnowledgeMap map = KnowledgeMap.getInstance();
		Board bestBoard = AIUtils.getBestBoard(board.getAllMoves());
		List<Board> bestBoards = AIUtils.getBestBoards(map.getValue(bestBoard), board.getAllMoves());
		int randIndex = (int) (Math.random() * bestBoards.size());
		Board bestMove = bestBoards.get(randIndex);
		return AIUtils.boardToMove(board, bestMove);
	}
	
	@Override
	public boolean playAgain() {
		return --playTimes > 0;
	}
	
}
