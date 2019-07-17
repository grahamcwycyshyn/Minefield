import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {
	
	public static int getRows(Scanner scan) {
		int rows = 0;
		
		do {
		System.out.println("How many rows would you like? ");
//				+ "\nPlease enter a number between 1 and 9: ");
		try {
			rows = scan.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("That was not a valid input. "
								+ "Let's try again.\n");
			scan.nextLine();
		} 
		} while (rows < 1 || rows > 50);
		return rows;
	}
	
	public static int getColumns(Scanner scan) {
		int columns = 0;
		
		do {
		System.out.println("How many columns would you like?");
//				+ "\nPlease enter a number between 1 and 9: ");
		try {
			columns = scan.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("That was not a valid input. "
								+ "Let's try again.\n");
			scan.nextLine();
		}
		} while (columns < 1 || columns > 50);
		return columns;

	}

	public static double getDouble(Scanner scan) {
		Double input = 0.0;
		scan.nextLine();
		System.out.println("What bomb density would you like? (enter a percentage)");
		do {
			try {
				input = scan.nextDouble();
			} catch (InputMismatchException ex) {
				System.out.println("Please enter a number between 1 and 100: ");
				scan.nextLine();
				continue;
			}
			if (input < 1 || input > 100) {
				System.out.println("Please enter a number between 1 and 100: ");
			}
		} while (input < 1 || input > 100);
		return input;
	}
	
	public static boolean getFlag(Scanner scan) {
		String input = null;
		boolean isValid = true;

		System.out.println("Would you like to flag? (y/n)");

		do {
			try {
			input = scan.nextLine();
			} catch (InputMismatchException ex) {
				System.out.println("Please enter \"y\" or \"n\": ");
				isValid = false;
			}
		if 	(input.substring(0, 1).equalsIgnoreCase("n")) {
			isValid = false;
			break;
		}
		if (!(input.substring(0, 1).equalsIgnoreCase("y"))) {
			isValid = false;
			System.out.println("Please enter \"y\" or \"n\": ");
		}
		
		} while (!isValid);
		return isValid;
	}
	
}
