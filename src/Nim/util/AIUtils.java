package Nim.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Board;
import model.KnowledgeMap;
import model.Move;

public class AIUtils {
	public static Board getBestBoard(Iterator<Board> boards, KnowledgeMap map) {
		Board bestMove = null;
		while(boards.hasNext()) {
			Board next = boards.next();
			double value = map.getValue(next);
			if(bestMove == null || value > map.getValue(bestMove)) {
				bestMove = next;
			}
		}
		return bestMove;
	}
	
	public static List<Board> getBestBoards(double bestBoard, Iterator<Board> boards) {
		List<Board> bestBoards = new ArrayList<Board>();
		double tollerance = .001;
		while(boards.hasNext()) {
			Board board = boards.next();
			double valueDifference = Math.abs(KnowledgeMap.getInstance().getValue(board) - bestBoard);
			if(valueDifference < tollerance) {
				bestBoards.add(board);
			}
		}
		return bestBoards;
	}	
	
	public static Move boardToMove(Board from, Board to) {
		Move move = null;
		for(int row = 0; row < Board.ROWS && move == null; row++) {
			if(from.getRow(row) != to.getRow(row)) {
				move = new Move(row, from.getRow(row) - to.getRow(row));
			}
		}
		return move;
	}
}
