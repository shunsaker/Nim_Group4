package interactions.players;

import java.util.Scanner;

import model.Board;
import model.Move;

public class ConsoleHuman extends Player{
	private Scanner scan = new Scanner(System.in);
	private final int MAX_ROW = 7;
	
	public ConsoleHuman(String name) {
		super(name);
	}
	
	@Override 
	public boolean playAgain() {
		System.out.println("Rematch? [Y/N]");
		return scan.nextLine().toUpperCase().startsWith("Y");
	}

	@Override
	public Move getMove(Board board) {
		System.out.println(NAME + "'s turn\n");
		displayBoard(board);
		int row = getRow(board);
		int remove = getRemove(board, row);
		return new Move(row, remove);
	}
	
	private int getRow(Board board) {
		boolean valid = false;
		int row = 0;
		while(!valid) {
			System.out.println("What row to remove from?");
			try {
				row = Integer.parseInt(scan.nextLine()) - 1;
				if(0 <= row && row < Board.ROWS){
					if(board.getRow(row) > 0) {
						valid = true;
					}
					else {
						System.out.println("That row is already empty");
					}
				}
				else {
					System.out.println("Pick a row between 1 and 3");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid row. Pick a row between 1 and 3");
			}
		}
		return row;
	}
	
	private int getRemove(Board board, int row) {
		boolean valid = false;
		int remove = 0;
		while(!valid) {
			System.out.println("How many would you like to remove?");
			try {
				remove = Integer.parseInt(scan.nextLine());
				if(remove > 0 && board.getRow(row) >= remove){
					valid = true;
				}
				else {
					System.out.println("Cannot remove that many.");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid number.");
			}
		}
		return remove;
	}
	
	private void displayBoard(Board board) {
		for(int i = 0; i < Board.ROWS; i++) {
			System.out.print(i + 1 + ": ");
			centerLine(board.getRow(i));
			for(int j = 0; j < board.getRow(i); j++) {
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void centerLine(int size) {
		int spacesNeeded = (MAX_ROW - size) / 2;
		for(int i = 0; i < spacesNeeded; i++) {
			System.out.print(" ");
		}
	}

}
