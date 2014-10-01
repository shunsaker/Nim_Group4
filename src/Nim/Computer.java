package Nim;

import java.util.ArrayList;
import java.util.List;

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
		List<Board> moves = board.getAllMoves();
		Board bestBoard = map.getBestBoard(moves);
		List<Board> bestBoards = getBestBoards(map.getValue(bestBoard), moves);
		int randIndex = (int) (Math.random() * bestBoards.size());
		Board bestMove = bestBoards.get(randIndex);
		return boardToMove(board, bestMove);
	}
	
	public List<Board> getBestBoards(double bestBoard, List<Board> boards) {
		List<Board> bestBoards = new ArrayList<Board>();
		double tollerance = .001;
		for(Board board : boards) {
			double valueDifference = Math.abs(KnowledgeMap.getInstance().getValue(board) - bestBoard);
			if(valueDifference < tollerance) {
				bestBoards.add(board);
			}
		}
		return bestBoards;
	}
	
	
	public Move boardToMove(Board from, Board to) {
		Move move = null;
		for(int row = 0; row < Board.ROWS && move == null; row++) {
			if(from.getRow(row) != to.getRow(row)) {
				move = new Move(row, from.getRow(row) - to.getRow(row));
			}
		}
		return move;
	}
	
	@Override
	public boolean playAgain() {
		return --playTimes > 0;
	}
	
}
