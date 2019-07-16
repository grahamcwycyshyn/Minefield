import java.util.InputMismatchException;
import java.util.Scanner;

public class MinefieldApp {
	public static void main(String[] args) {
		boolean terminateGame = false;
		
		Play playChoice;

		Scanner scan = new Scanner(System.in);

		System.out.println("How many rows would you like?");

		int rows = scan.nextInt();

		System.out.println("How many columns would you like?");

		int columns = scan.nextInt();

		Field[][] gameState = new Field[rows][columns];

		boolean[][] uncover = new boolean[rows][columns];

		boolean[][] flags = new boolean[rows][columns];
		// System.out.println(flags.length);
		// System.out.println(flags[0].length);

		boolean[][] containsBomb = placeMines(scan, rows, columns);
		int[] userTarget = new int[2];

		drawField(rows, columns);
		// displayField(gameState);
		scan.nextLine();
		
		displaySolution(containsBomb, gameState);

		while (!terminateGame) {
			// displayField(gameState);
			userTarget = getTarget(scan, gameState);
			scan.nextLine();
			playChoice = choosePlay(scan);
			if (playChoice == Play.FLAG) {
				doFlag(gameState, userTarget);
			} else if (playChoice == Play.UNCOVER) {
				doUncover(containsBomb, gameState, userTarget);
				terminateGame = winOrLose(userTarget, containsBomb, gameState, terminateGame);
			}
			
			if(weHaveAWinner(gameState, containsBomb)) {
				terminateGame = true;
				System.out.println("You Win!");
				
			}
			
			if (terminateGame == false) {
				displayField(gameState);
			}
			scan.nextLine();
			// displayField(gameState);
		}

		// displaySolution(containsBomb, gameState);
	}
	
	public static void search(Field[][] gameState, int x, int y) {
		
	}
	
	public static boolean weHaveAWinner(Field[][] gameState, boolean[][] containsBomb) {
		
		int row = gameState.length;
		int column =gameState[0].length;
		int count = 0;
		boolean winner = false;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if((gameState[i][j] != Field.covered) && (gameState[i][j] == Field.flag && containsBomb[i][j])) {
					winner = true;
				}
			}
		}
		
		return winner;
		
//		if(count > 0) {
//			winner = false;
//			return winner;
//		} 
//	
//		for(int i = 0; i < row; i++) {
//			for(int j = 0; j < column; j++) {
//				if(gameState[i][j] == Field.flag && !containsBomb[i][j]) {
//					winner = false;
//					return winner;
//				}
//			}
//		}
//		if(count == 0 && winner != false) {
//		return true;
//		} else {
//			return false;
//		}
	}

	public static boolean winOrLose(int[] userTarget, boolean[][] containsBomb, Field[][] gameState,
			boolean terminateGame) {

		if (containsBomb[userTarget[0]][userTarget[1]] == true) {
			System.out.println("Game Over");
			displaySolution(containsBomb, gameState);
			terminateGame = true;
			return terminateGame;
		} else {
			terminateGame = false;
			return terminateGame;
		}
	}

	public static int[][] drawField(int rows, int columns) {
		int[][] field = new int[rows][columns];
		return field;
	}

	public static void displayField(Field gameState[][]) {
		for (int i = 0; i < gameState[0].length; ++i) {
			if (i == 0) {
				System.out.print("  ");
			}
			System.out.print(i + 1 + " ");
		}

		System.out.println();

		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[0].length; j++) {
				if (j == 0) {
					System.out.print(i + 1 + " ");
				}
				if (gameState[i][j] == null) {
					System.out.print("- ");
				} else {
					System.out.print(print(gameState, i, j));
				}
			}
			System.out.println();
		}

	}

	public static String print(Field[][] gameState, int i, int j) {

		switch (gameState[i][j]) {
		case one:
			return "1 ";
		case two:
			return "2 ";
		case three:
			return "3 ";
		case four:
			return "4 ";
		case five:
			return "5 ";
		case six:
			return "6 ";
		case seven:
			return "7 ";
		case eight:
			return "8 ";
		case covered:
			return "- ";
		case flag:
			return "* ";
		default:
			return "  ";
		}

	}

	public static int[] getTarget(Scanner scan, Field[][] gameState) {
		System.out.println("Time to target.");
		int rowInput = whatRow(scan, gameState.length);
		int columnInput = whatColumn(scan, gameState[0].length);
		int[] userTarget = { rowInput, columnInput };
		return userTarget;
	}

	public static Play choosePlay(Scanner scan) {
		System.out.println("Would you like to: \n1) flag a cell, or\n2) uncover it? \nEnter 1 or 2: ");
		// scan.nextLine();
		int userInput = scan.nextInt();
		Play input;

		if (userInput == 1) {
			input = Play.FLAG;
		} else {
			input = Play.UNCOVER;
		}

		return input;

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
			if (rowInput < 1 || rowInput > rows) { // rows should be reflecting flags.length from the whatRows() call
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and 9: ");
		} while (!isValid);

		return (rowInput - 1);
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
			if (columnInput < 1 || columnInput > columns) { // columns should be reflecting flags[0].length
				isValid = false;
			} else {
				isValid = true;
				break;
			}
			System.out.println("Please enter a number between 1 and 9: ");
		} while (!isValid);

		return (columnInput - 1);
	}

	public static Field[][] doFlag(Field[][] gameState, int[] userTarget) {

//		if (Validate.getFlag(scan)) {
//
//			int rowInput = whatRow(scan, gameState.length); //this isn't working to pass in the array length as an int

//			int columnInput = whatColumn(scan, gameState[0].length); //this isn't working to pass in the array length as an int

		if (gameState[userTarget[0]][userTarget[1]] == Field.covered
				|| gameState[userTarget[0]][userTarget[1]] == null) {
			gameState[userTarget[0]][userTarget[1]] = Field.flag;

		} else if (gameState[userTarget[0]][userTarget[1]] == Field.flag) {
			gameState[userTarget[0]][userTarget[1]] = Field.covered;
		}
		return gameState;

		// }
		// return gameState;
	}

	public static Field[][] doUncover(boolean[][] containsBomb, Field[][] gameState, int[] userTarget) {

//		System.out.println("Time to target.");
//		int rowInput = whatRow(scan, gameState.length);
//		int columnInput = whatColumn(scan, gameState[0].length);

		if (gameState[userTarget[0]][userTarget[1]] == Field.flag) {
			System.out.println("This cell is flagged, try another!");

		} else if (containsBomb[userTarget[0]][userTarget[1]] == true) {
			gameState[userTarget[0]][userTarget[1]] = Field.covered;
		} else if(minesNear(userTarget[0], userTarget[1], containsBomb) == Field.empty){
			gameState[userTarget[0]][userTarget[1]] = minesNear(userTarget[0], userTarget[1], containsBomb);
			clear(containsBomb, gameState, userTarget[0], userTarget[1]);
		}else{
		
			gameState[userTarget[0]][userTarget[1]] = minesNear(userTarget[0], userTarget[1], containsBomb);

		}
		return gameState;
	}

	public static void clear(boolean[][] containsBomb, Field[][] gameState, int x, int y) {
//		x=x+1;
//		y=y+1;
		try {
//			if (minesNear(x-1, y-1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x - 1, y - 1);
//				gameState[x - 1][y - 1] = minesNear(x - 1, y - 1, containsBomb);
//			}
			gameState[x - 1][y - 1] = minesNear(x - 1, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x, y-1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x, y - 1);
//				gameState[x][y - 1] = minesNear(x, y - 1, containsBomb);
//			}
			gameState[x][y - 1] = minesNear(x, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x+1, y-1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x + 1, y - 1);
//				gameState[x + 1][y - 1] = minesNear(x + 1, y - 1, containsBomb);
//			}
			gameState[x + 1][y - 1] = minesNear(x + 1, y - 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x-1, y, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x - 1, y);
//				gameState[x - 1][y] = minesNear(x - 1, y, containsBomb);
//			}
			gameState[x - 1][y] = minesNear(x - 1, y, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x+1, y, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x + 1, y);
//				gameState[x + 1][y] = minesNear(x + 1, y, containsBomb);
//			}
			gameState[x + 1][y] = minesNear(x + 1, y, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x-1, y+1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x - 1, y + 1);
//				gameState[x - 1][y + 1] = minesNear(x - 1, y + 1, containsBomb);
//			}
			gameState[x - 1][y + 1] = minesNear(x - 1, y + 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x, y+1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x, y + 1);
//				gameState[x][y + 1] = minesNear(x, y + 1, containsBomb);
//			}
			gameState[x][y + 1] = minesNear(x, y + 1, containsBomb);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
//			if (minesNear(x+1, y+1, containsBomb) == Field.empty) {
//				clear(containsBomb, gameState, x + 1, y + 1);
//				gameState[x + 1][y + 1] = minesNear(x + 1, y + 1, containsBomb);
//			}
			gameState[x + 1][y + 1] = minesNear(x + 1, y + 1, containsBomb);
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
					if (minesNear(i, j, containsBomb) == Field.one) {
						System.out.print("1 ");
					} else if (minesNear(i, j, containsBomb) == Field.two) {
						System.out.print("2 ");
					} else if (minesNear(i, j, containsBomb) == Field.three) {
						System.out.print("3 ");
					} else if (minesNear(i, j, containsBomb) == Field.four) {
						System.out.print("4 ");
					} else if (minesNear(i, j, containsBomb) == Field.five) {
						System.out.print("5 ");
					} else if (minesNear(i, j, containsBomb) == Field.six) {
						System.out.print("6 ");
					} else if (minesNear(i, j, containsBomb) == Field.seven) {
						System.out.print("7 ");
					} else if (minesNear(i, j, containsBomb) == Field.eight) {
						System.out.print("8 ");
					} else {
						System.out.print("  ");
					}
					;
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
