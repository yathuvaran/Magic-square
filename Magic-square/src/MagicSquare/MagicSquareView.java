package MagicSquare;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MagicSquareView extends JFrame implements MagicSquareListener{

	protected JButton[][] buttons;
	private MagicSquareModel gameModel;
	

	MagicSquareView(MagicSquareModel gameModel) {
		this.setTitle("Magic Square");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 300);
		JPanel buttonPanel = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		buttons = new JButton[gameModel.getDimension()][gameModel.getDimension()];
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				buttons[i][j].addActionListener(new MagicSquareController(gameModel,this));
				buttonPanel.add(buttons[i][j]);
			}
		}
		buttonPanel.setLayout(new GridLayout(3, 3));
		this.gameModel = gameModel;
		this.add(buttonPanel);
		gameModel.addView(this);
		this.setVisible(true);
	}
	
	public JButton[][] getButtons() {
		return buttons;
	}
	
	public int displayDialogBox() {
		return Integer.parseInt(JOptionPane.showInputDialog("Enter Num:"));
	}
	
	public void displayErrorMessage(IllegalArgumentException e) {
		JOptionPane.showMessageDialog(this, e.getMessage());
	}

	@Override
	public void handleMagicSquareEvent(MagicSquareEvent e) {
		buttons[e.getX()][e.getY()].setText(Integer.toString(e.getNum()));
	}

}
