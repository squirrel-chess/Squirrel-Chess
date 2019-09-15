import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chess {
	
	private JFrame frame;
	private JPanel textPanel;
	private JLabel text;
	private Board board;
	private Dimension frameDim;
	private int mins, secs;
	private int width, height;
	
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
		do {
		width = Integer.parseInt(JOptionPane.showInputDialog("Enter frame width"));
		height = Integer.parseInt(JOptionPane.showInputDialog("Enter frame height"));
		frameDim = new Dimension(width, height);
		frame.setPreferredSize(frameDim);
		frame.setSize(frameDim);
		} while (JOptionPane.showConfirmDialog(null, "Is this resolution OK?") != JOptionPane.OK_OPTION);
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
