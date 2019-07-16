import java.util.InputMismatchException;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("How many rows would you like?");

		int rows = scan.nextInt();

		System.out.println("How many columns would you like?");

		int columns = scan.nextInt();
		
		Field[][] gameState = new Field[rows][columns];
		
		boolean[][] uncover = new boolean[rows][columns];

		boolean[][] flags = new boolean[rows][columns];
		//System.out.println(flags.length);
		//System.out.println(flags[0].length);

		boolean[][] containsBomb = placeMines(scan, rows, columns);

		drawField(rows, columns);
		displayField(rows, columns);
		scan.nextLine();
//		flags = doFlag(scan, flags);
		displaySolution(containsBomb, gameState);
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

	public static Field[][] doFlag(Scanner scan, Field[][] gameState) {

		if (Validate.getFlag(scan)) {

			int rowInput = whatRow(scan, gameState.length); //this isn't working to pass in the array length as an int
			
			int columnInput = whatColumn(scan, gameState[0].length); //this isn't working to pass in the array length as an int
			if(gameState[rowInput][columnInput] == Field.covered) {
			gameState[rowInput][columnInput] = Field.flag;
			} else if(gameState[rowInput][columnInput] == Field.flag) {
				gameState[rowInput][columnInput] = Field.covered;
			}
			return gameState;

		}
		return gameState;
	}

	public static Field[][] doUncover(Scanner scan, boolean[][] containsBomb, Field[][] gameState) {

		int rowInput = whatRow(scan, gameState.length);
		int columnInput = whatColumn(scan, gameState[0].length);

		if (gameState[rowInput][columnInput] == Field.flag) {
			System.out.println("This cell is flagged, try another!");

		} else if (containsBomb[rowInput][columnInput] == true) {
			System.out.println("Game Over");

		} else {
			gameState[rowInput][columnInput] = minesNear(rowInput, columnInput, containsBomb);
			
		}
		return gameState;
	}

	public static Field minesNear(int x, int y, boolean[][] containsBomb) {

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

		switch(mines) {
			
		case 1:
			return Field.one;
		case 2:
			return Field.two;
		case 3:
			return Field.three;
		case 4:
			return Field.four;
		case 5:
			return Field.five;
		case 6:
			return Field.six;
		case 7:
			return Field.seven;
		case 8:
			return Field.eight;
		default:
			return Field.empty;
		}
	}

	public static void displaySolution(boolean[][] containsBomb, Field[][] gameState) {

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
					if(minesNear(i, j, containsBomb) == Field.one) {
						System.out.print("1 ");
					} else if(minesNear(i, j, containsBomb) == Field.two) {
						System.out.print("2 ");
					} else if(minesNear(i, j, containsBomb) == Field.three) {
						System.out.print("3 ");
					} else if(minesNear(i, j, containsBomb) == Field.four) {
						System.out.print("4 ");
					}else if (minesNear(i, j, containsBomb) == Field.five) {
						System.out.print("5 ");
					}else if(minesNear(i, j, containsBomb) == Field.six) {
						System.out.print("6 ");
					}else if(minesNear(i, j, containsBomb) == Field.seven) {
						System.out.print("7 ");
					} else if(minesNear(i, j, containsBomb) == Field.eight) {
						System.out.print("8 ");
					} else {
						System.out.print("  ");
					};
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
