package Nim;

import java.util.Scanner;

public class MainMenu {
	private final int OPTIONS = 5;
	private Scanner scan = new Scanner(System.in);
	String menu = "0) Quit\n" +
				"1) Human Vs. Human\n" +
				"2) Human Vs. Computer (Human First)\n" +
				"3) Computer Vs. Human (Computer First)\n" +
				"4) Computer Vs. Computer\n" + 
				"5) Settings\n";
	
	public int getSelection(String menuOptions, int options) {
		boolean valid = false;
		int selection = 0;
		while(!valid) {
			System.out.println(menuOptions);
			String response = scan.nextLine();
			try{
				selection = Integer.parseInt(response);
				valid = selection >= 0 && selection <= options;
				if(!valid) {
					System.out.println("Invalid entry");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Must enter a number");
			}
		}
		return selection;
	}
	
	public int getSelection() {
		return getSelection(menu, OPTIONS);
	}
	
	public int getNumTimes() {
		boolean valid = false;
		int times = 0;
		while(!valid) {
			System.out.println("How many times should they play?");
			String response = scan.nextLine();
			try{
				times = Integer.parseInt(response);
				valid = times > 0;
				if(!valid) {
					System.out.println("Invalid entry");
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Must enter a number");
			}
		}
		return times;
	}
	
	public void setAI() {
		int selection;
		do {
			System.out.println("Would you like a new AI or a perfect AI?");
			String menuOptions = "0) Back\n" + 
								"1) New AI\n" + 
								"2) Perfect AI\n\n" + 
								"3) Allow AI to experiment (More learning, but more losses):   " + 
								(KnowledgeMap.isExperimentationAllowed() ? "TRUE" : "FALSE"); 
			selection = getSelection(menuOptions, 3);
			if(selection == 1 || selection == 2) {
				KnowledgeMap.setAI(selection == 2);
			}
			else if(selection == 3 && !KnowledgeMap.isUsingPerfectAI()) {
				KnowledgeMap.toggleExperimentation();
			}
		}
		while(selection == 3);
	}
}
