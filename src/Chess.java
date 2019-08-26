import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chess {
	
	private JFrame frame;
	private JLabel text;
	
	public Chess() {
		frame = new JFrame("Squirrel Chess");
		text = new JLabel();
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.add(new Board(this));
		frame.setVisible(true);
		frame.add(text);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setText(String str) {
		text.setText("<html>" + str + "</html>");
		frame.pack();
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
