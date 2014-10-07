package Nim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private Scanner scan = new Scanner(System.in);
	private Player playerOne, playerTwo;
	private Board board;
	private List<Board> playerOneBoards, playerTwoBoards;
	private int playerToAskAgain;
	private int playerOneWins = 0, totalGamesPlayed = 0;
	
	public Game(Player playerOne, Player playerTwo, int playerToAskAgain) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.playerToAskAgain = playerToAskAgain;
		if(KnowledgeMap.isUsingPerfectAI()) {
			KnowledgeMap.load();
		}
	}
	
	public void play() {
		boolean again;
		do{
			setUp();
			Player winner = getWinner(board);
			endGame(winner);
			again = (playerToAskAgain == 1 ? playerOne : playerTwo).playAgain();
			learn(winner);
		}
		while(again);
		KnowledgeMap.save();
		displayWinStats();
	}
	
	private void setUp() {
		board = new Board();
		playerOneBoards = new ArrayList<Board>();
		playerTwoBoards = new ArrayList<Board>();
	}
	
	private void endGame(Player winner) {
		System.out.println(winner + " Is the winner!\n");
		totalGamesPlayed++;
		playerOneWins += (winner == playerOne ? 1 : 0);
	}
	
	private void displayWinStats() {
		System.out.println(playerOne.NAME + " won " + ((float)playerOneWins / totalGamesPlayed * 100) + "% of the games");
		System.out.println("Press enter to continue");
		scan.nextLine();
	}
	
	private Player getWinner(Board board) { // Plays the game
		Player winner = null;
		while(!board.isEndGame()) {
			board = nextTurn(playerOne);
			if(!board.isEndGame()) {
				board = nextTurn(playerTwo);
			}
			else {
				winner = playerTwo;
			}
		}
		return winner == null ? playerOne : winner;
	}
	
	private Board nextTurn(Player player) {
		Move move = (player == playerOne ? playerOne : playerTwo).getMove(board);
		board = board.makeMove(move);
		(player == playerOne ? playerOneBoards : playerTwoBoards).add(board);
		return board;
	}
	
	private void learn(Player winner) {
		List<Board> winningBoards, losingBoards;
		
		if(winner == playerOne) {
			winningBoards = playerOneBoards;
			losingBoards = playerTwoBoards;
		}
		else {
			winningBoards = playerTwoBoards;
			losingBoards = playerOneBoards;
		}
		
		for(Board board : winningBoards) {
			KnowledgeMap.getInstance().add(board, 1);
		}
		for(Board board : losingBoards) {
			KnowledgeMap.getInstance().add(board, 0);
		}
	}
}
