import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Chess {
	
	private JFrame frame;
	private JLabel text;
	private Board board;
	private int mins, secs;
	
	public Chess() {
		frame = new JFrame("Squirrel Chess");
		text = new JLabel();
		do {
			mins = Integer.parseInt(JOptionPane.showInputDialog("Enter number of minutes"));
			secs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seconds"));
		} while (!(mins >= 0 && secs >= 0 && secs < 60));
		board = new Board(this, mins, secs);
		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.add(text, BorderLayout.EAST);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		board.startTime();
	}
	
	public void setText(String str) {
		text.setText("<html>" + str + "</html>");
		frame.pack();
	}
	
	public Dimension getFrameDimension() {
		return frame.getSize();
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
