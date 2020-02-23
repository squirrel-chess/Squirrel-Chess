import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chess {
	
	private JFrame frame;
	private Menu menu;
	private TimerMenu timeMenu;
	private JLabel text;
	private Board board;
	private int mins, secs;

	
	public Chess() {
		frame = new JFrame("Squirrel Chess");
		menu = new Menu(this);
		frame.add(menu);
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setText(String str) {
		text.setText("<html>" + str + "</html>");
		frame.pack();
	}
	public void setupGame() {
		text = new JLabel();
		timeMenu = new TimerMenu(this);
		frame.setSize(500, 500);
		frame.remove(menu);
		frame.add(timeMenu);
	}
	
	public void playGame() {
		board = new Board(this);
		frame.setLayout(new BorderLayout());
		frame.remove(timeMenu);
		frame.add(board, BorderLayout.CENTER);
		frame.add(text, BorderLayout.EAST);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
	}
	public Dimension getFrameDimension() {
		return frame.getSize();
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
