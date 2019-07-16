import java.util.InputMismatchException;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("How many rows would you like?");

		int rows = scan.nextInt();

		System.out.println("How many columns would you like?");

		int columns = scan.nextInt();
		
		boolean[][] uncover = new boolean[rows][columns];

		boolean[][] flags = new boolean[rows][columns];
		//System.out.println(flags.length);
		//System.out.println(flags[0].length);

		boolean[][] containsBomb = placeMines(scan, rows, columns);

		drawField(rows, columns);
		displayField(rows, columns);
		scan.nextLine();
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

	public static int whatRow(Scanner scan, int rows) {
		int rowInput = 0;
		boolean isValid = true;
		
		System.out.println("What cell would you like target? \nEnter row #: ");
		do {
			try {
				rowInput = scan.nextInt();
			} catch (InputMismatchException ex) {
				isValid = false;
				scan.nextLine();
			}
			if (rowInput < 1 || rowInput > rows) {  //rows should be reflecting flags.length from the whatRows() call
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and 9: ");
		} while (!isValid);
		
		return rowInput;
	}

	public static int whatColumn(Scanner scan, int columns) {
		int columnInput = 0;
		boolean isValid = true;
		
		System.out.println("Enter column #: ");
		do {
			try {
				columnInput = scan.nextInt();
			} catch (InputMismatchException ex) {
				isValid = false;
				scan.nextLine();
			}
			if (columnInput < 1 || columnInput > columns) { //columns should be reflecting flags[0].length
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and 9: ");
		} while (!isValid);
		
		return columnInput;
	}

	public static boolean[][] doFlag(Scanner scan, boolean[][] flags) {

		if (Validate.getFlag(scan)) {

			int rowInput = whatRow(scan, flags.length); //this isn't working to pass in the array length as an int
			int columnInput = whatColumn(scan, flags[0].length); //this isn't working to pass in the array length as an int
			flags[rowInput][columnInput] = true;
			return flags;

		}
		return flags;
	}

	public static boolean[][] doUncover(Scanner scan, boolean[][] containsBomb, boolean[][] flags, boolean[][] uncover) {

		int rowInput = whatRow(scan, flags.length);
		int columnInput = whatColumn(scan, flags[0].length);

		if (flags[rowInput][columnInput] == true) {
			System.out.println("This cell is flagged, try another!");

		} else if (containsBomb[rowInput][columnInput] == true) {
			System.out.println("Game Over");

		} else {
			uncover[rowInput][columnInput] = true;
		}
		return uncover;
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
		
		double input = Validate.getDouble(scan);
		
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
