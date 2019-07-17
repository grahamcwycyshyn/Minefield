import java.util.InputMismatchException;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {
		boolean terminateGame = false;
		Play playChoice;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Let's create your field.");
		int rows = Validate.getRows(scan);
		int columns = Validate.getColumns(scan);
		
		Field[][] gameState = new Field[rows][columns];
		boolean[][] checked = new boolean[gameState.length][gameState[0].length];
		boolean[][] containsBomb = placeMines(scan, rows, columns);
		int[] userTarget = new int[2];

		displayField(gameState);
		scan.nextLine();
		

		while (!terminateGame) {
			
			userTarget = getTarget(scan, gameState);
			scan.nextLine();
			playChoice = choosePlay(scan);
			if (playChoice == Play.FLAG) {
				doFlag(gameState, userTarget);
			} else if (playChoice == Play.UNCOVER) {
				doUncover(checked, containsBomb, gameState, userTarget);
				terminateGame = winOrLose(userTarget, containsBomb, gameState, terminateGame);
			}
			
			if (weHaveAWinner(gameState, containsBomb)) {
				terminateGame = true;
				System.out.println("You Win!");
				displaySolution(containsBomb, gameState);
			}
			
			if (terminateGame == false) {
				displayField(gameState);
			}
			scan.nextLine();
		}//while

		
	}//main
	
	
	public static boolean weHaveAWinner(Field[][] gameState, boolean[][] containsBomb) {
		
		int row = gameState.length;
		int column =gameState[0].length;
		int countCovered = 0;
		int countFlag = 0;
		boolean winner = false;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(gameState[i][j] == null || gameState[i][j] == Field.covered) {
					++countCovered;
				}
			}
		}
		
		if (countCovered > 0) {
			winner = false;
			return winner;
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if ((gameState[i][j] == Field.flag && !containsBomb[i][j])) {
					++countFlag;
				}
			}
		}
		
		if (countCovered == 0 && countFlag == 0) {
			winner = true;
		}
		
		return winner;
	}

	public static boolean winOrLose(int[] userTarget, boolean[][] containsBomb, Field[][] gameState,
									boolean terminateGame) {

		if ((containsBomb[userTarget[0]][userTarget[1]]) == true 
				&& gameState[userTarget[0]][userTarget[1]] != Field.flag) {
			System.out.println("Game Over");
			displaySolution(containsBomb, gameState);
			terminateGame = true;
			return terminateGame;
		} else {
			terminateGame = false;
			return terminateGame;
		}
	}

	public static void displayField(Field gameState[][]) {
		for (int i = 0; i < gameState[0].length; ++i) {
			if (i == 0) {
				System.out.printf("%-3s","  ");
			}
			System.out.printf("%-3s", i + 1 + " ");
		}
		System.out.println();

		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[0].length; j++) {
				if (j == 0) {
					System.out.printf("%-3s", i + 1 + " ");
				}
				if (gameState[i][j] == null) {
					System.out.printf("%-3s", "- ");
				} else {
					System.out.printf("%-3s", print(gameState, i, j));
				}
			}
			System.out.println();
		}
	}

	public static String print(Field[][] gameState, int i, int j) {

		switch (gameState[i][j]) {
		case one:
			return "1";
		case two:
			return "2";
		case three:
			return "3";
		case four:
			return "4";
		case five:
			return "5";
		case six:
			return "6";
		case seven:
			return "7";
		case eight:
			return "8";
		case covered:
			return "-";
		case flag:
			return "*";
		default:
			return " ";
		}

	}

	public static int[] getTarget(Scanner scan, Field[][] gameState) {
		System.out.println("Time to target.");
		int rowInput = whatRow(scan, gameState.length, gameState);
		int columnInput = whatColumn(scan, gameState[0].length, gameState);
		int[] userTarget = { rowInput, columnInput };
		return userTarget;
	}

	public static Play choosePlay(Scanner scan) {
		System.out.println("Would you like to: "
						+ "\n1) flag a cell, or"
						+ "\n2) uncover it? "
						+ "\nEnter 1 or 2: ");
		int userInput = scan.nextInt();
		Play input;

		if (userInput == 1) {
			input = Play.FLAG;
		} else {
			input = Play.UNCOVER;
		}
		return input;
	}

	public static int whatRow(Scanner scan, int rows, Field[][] gameState) {
		int rowInput = 0;
		boolean isValid = true;

		System.out.println("What cell would you like target? "
						 + "\nEnter row #: ");
		do {
			try {
				rowInput = scan.nextInt();
			} catch (InputMismatchException ex) {
				isValid = false;
				scan.nextLine();
			}
			if (rowInput < 1 || rowInput > rows) { 
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and " + gameState.length + ": ");
		} while (!isValid);

		return (rowInput - 1);
	}

	public static int whatColumn(Scanner scan, int columns, Field[][] gameState) {
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
			if (columnInput < 1 || columnInput > columns) { 
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and " + gameState[0].length + ": ");
		} while (!isValid);

		return (columnInput - 1);
	}

	public static Field[][] doFlag(Field[][] gameState, int[] userTarget) {

		if (gameState[userTarget[0]][userTarget[1]] == Field.covered
				|| gameState[userTarget[0]][userTarget[1]] == null) {
			gameState[userTarget[0]][userTarget[1]] = Field.flag;
		} else if (gameState[userTarget[0]][userTarget[1]] == Field.flag) {
			gameState[userTarget[0]][userTarget[1]] = Field.covered;
		}
		return gameState;
	}

	public static Field[][] doUncover(boolean[][] checked, boolean[][] containsBomb, Field[][] gameState, int[] userTarget) {

		if (gameState[userTarget[0]][userTarget[1]] == Field.flag) {
			System.out.println("This cell is flagged, try another!");
		} else if (containsBomb[userTarget[0]][userTarget[1]] == true) {
			gameState[userTarget[0]][userTarget[1]] = Field.covered;
		} else if(minesNear(userTarget[0], userTarget[1], containsBomb) == Field.empty){
			gameState[userTarget[0]][userTarget[1]] = minesNear(userTarget[0], userTarget[1], containsBomb);
			clear(checked, containsBomb, gameState, userTarget[0], userTarget[1]);
		} else {
			gameState[userTarget[0]][userTarget[1]] = minesNear(userTarget[0], userTarget[1], containsBomb);
		}
		return gameState;
	}

	public static void clear(boolean[][] checked, boolean[][] containsBomb, Field[][] gameState, int x, int y) {
	
		try {
			gameState[x - 1][y - 1] = minesNear(x - 1, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x][y - 1] = minesNear(x, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x + 1][y - 1] = minesNear(x + 1, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x - 1][y] = minesNear(x - 1, y, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x + 1][y] = minesNear(x + 1, y, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x - 1][y + 1] = minesNear(x - 1, y + 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x][y + 1] = minesNear(x, y + 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			gameState[x + 1][y + 1] = minesNear(x + 1, y + 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
		try {
			if (minesNear(x - 1, y - 1, containsBomb) == Field.empty && checked[x - 1][y - 1] == false) {
				checked[x - 1][y - 1] = true;
				clear(checked, containsBomb, gameState, x - 1, y - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (minesNear(x, y - 1, containsBomb) == Field.empty && checked[x][y - 1] == false) {
				checked[x][y - 1] = true;
				clear(checked, containsBomb, gameState, x, y - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (minesNear(x + 1, y - 1, containsBomb) == Field.empty && checked[x + 1][y - 1] == false) {
				checked[x + 1][y - 1] = true;
				clear(checked, containsBomb, gameState, x + 1, y - 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if (minesNear(x - 1, y, containsBomb) == Field.empty && checked[x - 1][y] == false) {
				checked[x - 1][y] = true;
				clear(checked, containsBomb, gameState, x - 1, y);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if (minesNear(x + 1, y, containsBomb) == Field.empty && checked[x + 1][y] == false) {
				checked[x + 1][y] = true;
				clear(checked, containsBomb, gameState, x + 1, y);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if (minesNear(x-1, y+1, containsBomb) == Field.empty && checked[x-1][y+1] == false) {
				checked[x-1][y+1] = true;
				clear(checked, containsBomb, gameState, x - 1, y + 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if (minesNear(x, y + 1, containsBomb) == Field.empty && checked[x][y + 1] == false) {
				checked[x][y + 1] = true;
				clear(checked, containsBomb, gameState, x, y + 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if (minesNear(x + 1, y + 1, containsBomb) == Field.empty && checked[x + 1][y + 1] == false) {
				checked[x + 1][y + 1] = true;
				clear(checked, containsBomb, gameState, x + 1, y + 1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
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

		switch (mines) {
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
				System.out.printf("%-3s","  ");
			}
			System.out.printf("%-3s", i + 1 + " ");
		}
		System.out.println();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (j == 0) {
					System.out.printf("%-3s", i + 1 + " ");
				}
				if (containsBomb[i][j] == true) {
					System.out.printf("%-3s", "* ");
				} else {
					if (minesNear(i, j, containsBomb) == Field.one) {
						System.out.printf("%-3s", "1 ");
					} else if (minesNear(i, j, containsBomb) == Field.two) {
						System.out.printf("%-3s", "2 ");
					} else if (minesNear(i, j, containsBomb) == Field.three) {
						System.out.printf("%-3s", "3 ");
					} else if (minesNear(i, j, containsBomb) == Field.four) {
						System.out.printf("%-3s", "4 ");
					} else if (minesNear(i, j, containsBomb) == Field.five) {
						System.out.printf("%-3s", "5 ");
					} else if (minesNear(i, j, containsBomb) == Field.six) {
						System.out.printf("%-3s", "6 ");
					} else if (minesNear(i, j, containsBomb) == Field.seven) {
						System.out.printf("%-3s", "7 ");
					} else if (minesNear(i, j, containsBomb) == Field.eight) {
						System.out.printf("%-3s", "8 ");
					} else {
						System.out.printf("%-3s", "  ");
					}
				}
			}
			System.out.println();
		}
	}
	
	public static boolean[][] placeMines(Scanner scan, int rows, int columns) {
		double input = Validate.getDouble(scan);
		return placeMines(input, rows, columns);
	}

	public static boolean[][] placeMines(double density, int rows, int columns) {
		int bombDensity = (int) ((density / 100) * (rows * columns));

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
