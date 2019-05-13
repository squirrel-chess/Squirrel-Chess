import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel {
	JButton[][] buttons = new JButton[8][8];
	
	public Board() {
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setOpaque(true);
				buttons[i][j].setBorderPainted(false);
				if ((i + j) % 2 == 1) {
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
					System.out.println("here");
				}
				add(buttons[i][j]);
			}
		}
	}
}
