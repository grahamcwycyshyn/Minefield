import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {

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
			//scan.nextLine();
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
