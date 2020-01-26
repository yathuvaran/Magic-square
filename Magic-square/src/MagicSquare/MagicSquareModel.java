package MagicSquare;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicSquareModel {
	private int dimension; // Grid dimensions
	private char grid[][];
	private GameStatus gameState;
	private ArrayList<MagicSquareListener> views;

	public MagicSquareModel(int dimension) {
		if (dimension < 3) {
			throw new IllegalArgumentException("n must be greater than or equal to 3");
		}
		this.dimension = dimension;
		this.grid = new char[dimension][dimension];
		this.views = new ArrayList<MagicSquareListener>();
		reset();
	}

	public void addView(MagicSquareListener view) {
		this.views.add(view);
	}

	public void reset() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				this.grid[i][j] = ' ';
			}
		}
		this.gameState = GameStatus.IN_PROGRESS;
	}

	public GameStatus takeTurn(int row, int column, int num) {
		if (this.gameState != GameStatus.IN_PROGRESS) {
			throw new IllegalArgumentException("Game Over " + this.gameState);
		}
		if (num > (dimension * dimension) || num < 1) {
			throw new IllegalArgumentException("Bad Number!");
		}
		if (row < 0 || row > this.dimension || column < 0 || column > this.dimension) {
			throw new IllegalArgumentException("Grid is " + this.dimension + " by " + this.dimension);
		}
		grid[row][column] = Character.forDigit(num, 10);
		this.gameState = findWinner();
		MagicSquareEvent e = new MagicSquareEvent(this, row, column, num, this.gameState);
		for (MagicSquareListener listeners : views) {
			listeners.handleMagicSquareEvent(e);
		}
		return this.gameState;
	}

	private GameStatus findWinner() {
		GameStatus newGameState = findWinnerFrom(0, 0);
		if (newGameState != GameStatus.IN_PROGRESS) {
			return newGameState;
		}
		return GameStatus.IN_PROGRESS;
	}

	private GameStatus findWinnerFrom(int row, int column) {

		Set<Integer> rowSums = new HashSet<Integer>();
		Set<Integer> columnSums = new HashSet<Integer>();
		Set<Integer> diagonalSums = new HashSet<Integer>();

		// sum of each row
		for (int r = row; r < dimension; r++) {
			int rowSum = 0;
			for (int c = column; c < dimension; c++) {
				rowSum += Character.getNumericValue(grid[r][c]);
			}
			rowSums.add(rowSum); // add sum of row to row sum array
		}

		// sum of each column
		for (int c = column; c < dimension; c++) {
			int colSum = 0;
			for (int r = row; r < dimension; r++) {
				colSum += Character.getNumericValue(grid[r][c]);
			}
			columnSums.add(colSum); // add sum of column to col sum array
		}

		// Sum of diagonal Left-down
		int diagSumLeft = 0;
		for (int r = row, c = column; r < dimension && c < dimension; r++, c++) {
			diagSumLeft += Character.getNumericValue(grid[r][c]);
		}
		diagonalSums.add(diagSumLeft);

		// Sum of diagonal Right-down
		int diagSumRight = 0;
		for (int r = row, c = dimension - 1; r < dimension && c >= 0; r++, c--) {
			diagSumRight += Character.getNumericValue(grid[r][c]);
		}
		diagonalSums.add(diagSumRight);

		if (rowSums.size() == 1 && columnSums.size() == 1 && diagonalSums.size() == 1) {
			return GameStatus.VICTORY;
		}

		return GameStatus.IN_PROGRESS;
	}

	public GameStatus getGameState() {
		return this.gameState;
	}
	
	public int getDimension() {
		return this.dimension;
	}

	public String toString() {
		String s = "";
		for (int r = 0; r < dimension; r++) {
			for (int c = 0; c < dimension; c++) {
				s += grid[r][c] + " | ";
			}
			s += "\n";
		}
		return s;
	}

	public static void main(String args[]) {
		MagicSquareModel game = new MagicSquareModel(3);
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println(game.toString());
			System.out.println("Where do you want to mark? Enter row column num");
			int row = scanner.nextInt();
			int column = scanner.nextInt();
			int num = scanner.nextInt();
			scanner.nextLine();
			game.takeTurn(row, column, num);

		} while (game.getGameState() == GameStatus.IN_PROGRESS);
		System.out.println(game.getGameState());

	}

}
