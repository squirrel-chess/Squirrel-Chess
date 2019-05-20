import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Board extends JPanel {
	JButton[][] buttons = new JButton[8][8];

	public Board() {
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setOpaque(true);
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].setText(i + ", " + j);
				if ((i + j) % 2 == 1) {
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
				}
				add(buttons[i][j]);
			}
		}
	}

	public void highlightMoves(ArrayList<Position> moveSet) {
		for (Position p : moveSet)
			buttons[p.getCol()][p.getRow()].setBackground(new Color(160, 255, 160));
	}
}
