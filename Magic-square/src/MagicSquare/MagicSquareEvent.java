package MagicSquare;

import java.util.EventObject;

public class MagicSquareEvent extends EventObject {

	private int x, y, num;
	private GameStatus status;
	private char[][] grid;

	public MagicSquareEvent(MagicSquareModel source, int x, int y, int num, GameStatus status) {
		super(source);
		this.x = x;
		this.y = y;
		this.num = num;
		this.status = status;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getNum() {
		return num;
	}

	public char[][] getGrid() {
		return grid;
	}
	
	public GameStatus getStatus() {
		return status;
	}


}

