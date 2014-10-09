package interactions;

import java.util.Scanner;

public class ConsoleDisplay {
	private static Scanner scan = new Scanner(System.in);
	
	public static void printString(String message) {
		System.out.println(message);
	}
	
	public static void delay() {
		System.out.println("Press enter to continue");
		scan.nextLine();
	}
}
