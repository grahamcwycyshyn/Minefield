import java.util.Arrays;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("How many rows would you like?");

		int rows = scan.nextInt();

		System.out.println("How many columns would you like?");

		int columns = scan.nextInt();

		drawField(rows, columns);
		displayField(rows, columns);

		placeMines(scan, rows, columns);
		
		//doUncover(scan);
		doFlag(scan, rows, columns);
		
		

	}

	public static int[][] drawField(int rows, int columns) {
		int[][] field = new int[rows][columns];
		return field;
	}
	
	public static void displayField(int rows, int columns) {
		for (int i = 0; i < columns; ++i) {
			if (i == 0) {
				System.out.print(" ");
			}
			System.out.print(i + 1 + " ");
		}
		
		System.out.println();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0 ; j < columns; j++) {
				if (j == 0) {
					System.out.print(i + 1);
				}
				System.out.print("- ");
			}
			System.out.println();
		}
	}
	
	public static void doFlag(Scanner scan, int rows, int columns) {
		System.out.println("What cell would you like target? \nEnter row #: ");
		int rowInput = scan.nextInt();
		System.out.println("Enter column #: ");
		int columnInput = scan.nextInt();
		System.out.println("Would you like to flag or uncover?");
		scan.nextLine();
		String input = scan.nextLine();
		boolean[][] flags = new boolean[rows][columns];
		
		if (input.equals("flag")) {
			 flags[rowInput][columnInput] = true;
		} 
		System.out.println(flags[rowInput][columnInput]);
	}
	
	public static void placeFlag() {
		
	}

	public static void placeMines(Scanner scan, int rows, int columns) {

		System.out.println("What bomb density would you like? (enter a percentage)");

		Double input = scan.nextDouble();

		int bombDensity = (int) ((input / 100) * (rows * columns));

		boolean[][] containsBomb = new boolean[rows][columns];

		while (bombDensity > 0) {

			int bombRow = (int) (Math.random() * rows);

			int bombColumn = (int) (Math.random() * columns);

			if (containsBomb[bombRow][bombColumn] != true) {
				containsBomb[bombRow][bombColumn] = true;
				bombDensity--;
			} else {
				
				continue;
				
			}
		}
	}
}
