package Nim;

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
					Player playerOne = (selection == 3 || selection == 4 ? (selection == 4 ? new Computer("Player1 (Computer)", mainMenu.getNumTimes()) : new Computer("Player1 (Computer)")) : new ConsoleHuman("Player1"));
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
}
