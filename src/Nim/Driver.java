package Nim;

import interactions.MainMenu;
import interactions.players.Computer;
import interactions.players.ConsoleHuman;
import interactions.players.Player;

public class Driver {
	private static MainMenu mainMenu = new MainMenu();
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.drive();
	}
	
	private void drive() {
		int selection;
		do {
			selection = mainMenu.getSelection();
			if(selection != 0) {
				if(selection == 5) {
					mainMenu.setAI();
				}
				else {
					Player playerOne = getPlayerOne(selection);
					Player playerTwo = (selection == 1 || selection == 3 ? new ConsoleHuman("Player2") : new Computer("Player2 (Computer)"));
					int playerToAsk = (selection == 3 ? 2 : 1);
					Game game = new Game(playerOne, playerTwo, playerToAsk);
					game.play();
				}
			}
		}
		while(selection != 0);
		System.out.println("Thanks for playing!");
	}
	
	private Player getPlayerOne(int menuSelection) {
		Player p;
		if(menuSelection == 3) {
			p = new Computer("Player1 (Computer)");
		}
		else if (menuSelection == 4) {
			p = new Computer("Player1 (Computer)", mainMenu.getNumTimes());
		}
		else {
			p = new ConsoleHuman("Player1");
		}
		return p;
	}
}
