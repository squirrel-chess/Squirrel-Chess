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
	
	private GamePanel gamePanel;
	
	public int frameHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 130;
	public int frameWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
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
		board = new Board(this);
		gamePanel = new GamePanel(frameWidth - frameHeight, frameHeight, frameHeight);
		
		frame.setLayout(null);
		frame.remove(menu);
		frame.add(board/*, BorderLayout.CENTER*/);
		//frame.add(text/*, BorderLayout.EAST*/);
		frame.add(gamePanel);
		
		board.setBounds(0, 0, frameHeight, frameHeight);
		gamePanel.setBounds(frameHeight, 0, frameWidth - frameHeight, frameHeight);
		
		//text.setBounds(frameHeight, 0, frameWidth-frameHeight, frameHeight);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		//frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		
		frame.pack();
	}
	
	public Dimension getFrameDimension() {
		return frame.getSize();
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
