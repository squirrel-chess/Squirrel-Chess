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
		System.out.println(str);
		text.setText("<html>" + str + "</html>");
		frame.pack();
	}
	public void setupGame() {
		System.out.println("setup game");
		text = new JLabel();
		board = new Board(this);
		frame.setLayout(new BorderLayout());
		frame.remove(menu);
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
