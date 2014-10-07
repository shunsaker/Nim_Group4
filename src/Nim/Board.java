package Nim;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Board implements Serializable{

	private static final long serialVersionUID = 1;
	public static final int ROWS = 3;
	private Integer[] rows = {3, 5, 7};
	
	public Board(){}
	private Board(Board other, Move move) {
		this.rows[0] = other.rows[0];
		this.rows[1] = other.rows[1];
		this.rows[2] = other.rows[2];
		rows[move.ROW] -= move.REMOVE;
	}
	
	public int getRow(int row) {
		return rows[row];
	}
	
	public Board makeMove(Move move) {
		return new Board(this, move);
	}
	
	public boolean isEndGame() {
		return rows[0] == 0 && rows[1] == 0 && rows[2]== 0;
	}
	
	public Iterator<Board> getAllMoves() {
		return new PossibleMovesIterator(this);
	}
	
	@Override 
	public boolean equals(Object other) {
		return this.hashCode() == other.hashCode();
	}
	
	@Override 
	public int hashCode() {
		List<Integer> rowsAsList = Arrays.asList(Arrays.copyOf(rows, rows.length));
		Collections.sort(rowsAsList);
		return rowsAsList.get(0) * 23 + rowsAsList.get(1) * 29 + rowsAsList.get(2) * 31;
	}
	
	@Override
	public String toString() {
		return rows[0] + " " + rows[1] + " " + rows[2];
	}
}
