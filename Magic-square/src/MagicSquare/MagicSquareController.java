package MagicSquare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MagicSquareController implements ActionListener {

	private MagicSquareModel gameModel;
	private MagicSquareView gameView;
	private JButton[][] buttons;

	public MagicSquareController(MagicSquareModel gameModel, MagicSquareView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		this.buttons = gameView.buttons;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if (buttons[i][j].equals(e.getSource())) {
					try {
						int num = gameView.displayDialogBox();
						gameModel.takeTurn(i, j, num);
					} catch (IllegalArgumentException error) {
						gameView.displayErrorMessage(error);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		MagicSquareModel gameModel = new MagicSquareModel(3);
		MagicSquareView gameView = new MagicSquareView(gameModel);
	}

}
