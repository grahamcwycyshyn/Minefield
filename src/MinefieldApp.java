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

		placeMines(scan, rows, columns);

	}

	public static int[][] drawField(int rows, int columns) {
		int[][] field = new int[rows][columns];
		return field;
	}
	
	public static void displayField() {
		
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
