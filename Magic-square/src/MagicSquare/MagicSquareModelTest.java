package MagicSquare;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MagicSquareModelTest {
	

	@Test
	void testSmallSizeGrid() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new MagicSquareModel(1));
	}
	
	@Test
	void testInvalidInput() {
		MagicSquareModel gameModel = new MagicSquareModel(3);
		Assertions.assertThrows(IllegalArgumentException.class, () -> gameModel.takeTurn(0, 0, 200));
		Assertions.assertThrows(IllegalArgumentException.class, () -> gameModel.takeTurn(0, 0, -200));
	}
	
	@Test
	void gridDimensions() {
		MagicSquareModel gameModel = new MagicSquareModel(3);
		Assertions.assertThrows(IllegalArgumentException.class, () -> gameModel.takeTurn(4, 4, 3));
		Assertions.assertThrows(IllegalArgumentException.class, () -> gameModel.takeTurn(-4, -4, 3));
	}
	
	
	@Test
	void testTakeTurn() {
		MagicSquareModel gameModel = new MagicSquareModel(3);
		gameModel.takeTurn(0, 0, 2);
		assertEquals(GameStatus.IN_PROGRESS, gameModel.getGameState());
	}
	
	@Test
	void testFindWinner() {
		MagicSquareModel gameModel = new MagicSquareModel(3);
		gameModel.takeTurn(0, 0, 2);
		gameModel.takeTurn(0, 1, 7);
		gameModel.takeTurn(0, 2, 6);
		gameModel.takeTurn(1, 0, 9);
		gameModel.takeTurn(1, 1, 5);
		gameModel.takeTurn(1, 2, 1);
		gameModel.takeTurn(2, 0, 4);
		gameModel.takeTurn(2, 1, 3);
		gameModel.takeTurn(2, 2, 8);
		assertEquals(GameStatus.VICTORY, gameModel.getGameState());
	}


}
