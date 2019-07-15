import java.util.Arrays;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("How many rows would you like?");

		int rows = scan.nextInt();

		System.out.println("How many columns would you like?");

		int columns = scan.nextInt();

		boolean[][] flags = new boolean[rows][columns];

		boolean[][] containsBomb = placeMines(scan, rows, columns);

		drawField(rows, columns);
		displayField(rows, columns);
		flags = doFlag(scan, flags);
		displaySolution(containsBomb);
	}

	public static int[][] drawField(int rows, int columns) {
		int[][] field = new int[rows][columns];
		return field;
	}

	public static void displayField(int rows, int columns) {
		for (int i = 0; i < columns; ++i) {
			if (i == 0) {
				System.out.print("  ");
			}
			System.out.print(i + 1 + " ");
		}

		System.out.println();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == 0) {
					System.out.print(i + 1 + " ");
				}
				System.out.print("- ");
			}
			System.out.println();
		}
	}

	public static int whatRow(Scanner scan) {
		System.out.println("What cell would you like target? \nEnter row #: ");
		int rowInput = scan.nextInt();
		return rowInput;
	}

	public static int whatColumn(Scanner scan) {

		System.out.println("Enter column #: ");
		int columnInput = scan.nextInt();
		return columnInput;
	}

	public static boolean[][] doFlag(Scanner scan, boolean[][] flags) {

		System.out.println("Would you like to flag? (y/n)");
		scan.nextLine();
		String input = scan.nextLine();

		if (input.substring(0, 1).equalsIgnoreCase("y")) {

			int rowInput = whatRow(scan);
			int columnInput = whatColumn(scan);
			flags[rowInput][columnInput] = true;
			return flags;

		}
		return flags;
	}

	public static void doUncover(Scanner scan, boolean[][] containsBomb, boolean[][] flags) {

		int rowInput = whatRow(scan);
		int columnInput = whatColumn(scan);

		if (flags[rowInput][columnInput] == true) {
			System.out.println("This cell is flagged, try another!");

		} else if (containsBomb[rowInput][columnInput] == true) {
			System.out.println("Game Over");

		} else {

		}
	}

	public static char minesNear(int x, int y, boolean[][] containsBomb) {

		int mines = 0;

		try {
			if (containsBomb[x - 1][y - 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x][y - 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x + 1][y - 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x - 1][y] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x + 1][y] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x - 1][y + 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x][y + 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		try {
			if (containsBomb[x + 1][y + 1] == true) {
				mines += 1;
			}
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		if (mines > 0) {
			return (char) (mines + 48);

		} else {

			return ' ';
		}
	}

	public static void displaySolution(boolean[][] containsBomb) {

		int row = containsBomb.length;

		int column = containsBomb[0].length;

		for (int i = 0; i < column; ++i) {
			if (i == 0) {
				System.out.print("  ");
			}
			System.out.print(i + 1 + " ");
		}
		System.out.println();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (j == 0) {
					System.out.print(i + 1 + " ");
				}
				if (containsBomb[i][j] == true) {
					System.out.print("* ");
				} else {
					System.out.print(minesNear(i, j, containsBomb) + " ");
				}
			}
			System.out.println();
		}
	}

	public static boolean[][] placeMines(Scanner scan, int rows, int columns) {

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

		return containsBomb;
	}
}
