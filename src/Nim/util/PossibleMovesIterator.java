package Nim.util;

import java.util.Iterator;

import model.Board;
import model.Move;

public class PossibleMovesIterator implements Iterator<Board>{
	private Board board;
	private int row = 0, numToRemove = 1;
	
	public PossibleMovesIterator(Board board) {
		this.board = board;
		row = 0; 
		while(board.getRow(row) == 0 && row != Board.ROWS) {
			row++;
		}
	}
	
	@Override
	public boolean hasNext() {
		return !(row == Board.ROWS - 1 && numToRemove > board.getRow(row));
	}

	@Override
	public Board next() {
		Move move = new Move(row, numToRemove);
		if(numToRemove == board.getRow(row) && row != Board.ROWS - 1) {
			numToRemove = 1;
			incrementRow();
		}
		else {
			numToRemove++;
		}
		return board.makeMove(move);
	}
	
	private void incrementRow() {
		if(row != Board.ROWS - 1) {
			row++;
		}
		while(board.getRow(row) == 0 && row != Board.ROWS - 1) {
			row++;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
