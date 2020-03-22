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
	//private JLabel text;
	private Board board;
	private int mins, secs;
	
	private GamePanel gamePanel;
	
	public int frameHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 130;
	public int frameWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	// TESTING TESTING
	public int frameHeight2 = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public int frameWidth2 = frameHeight2;
	
	public Chess() {
		frame = new JFrame("Squirrel Chess");
		menu = new Menu(this, frameWidth2, frameHeight2);
		frame.add(menu);
		frame.setVisible(true);
		//frame.setSize(1000, 1000);
		frame.setSize(frameWidth2, frameHeight2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void setText(String str) {
		gamePanel.setTimeText("<html>" + str + "</html>");
		frame.pack();
	}
	
	public void setupGame() {
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(frameWidth, frameHeight2));
		
		int cfHeight = frame.getContentPane().getHeight(); // (content frame height)
		
		//text = new JLabel();
		gamePanel = new GamePanel(this, new JFrame(), 0, 0, 0);     //to make sure the bottomGrid panel doesn't jump to the top left
		board = new Board(this);
		//gamePanel = new GamePanel(this, frame, frameWidth - frameHeight, frameHeight, frameHeight); 
		gamePanel = new GamePanel(this, frame, frameWidth - cfHeight, cfHeight, cfHeight);
		
		frame.remove(menu);
		frame.setLayout(null);
		frame.add(board/*, BorderLayout.CENTER*/);
		//frame.add(text/*, BorderLayout.EAST*/);
		//board.setBounds(0, 0, frameHeight, frameHeight);
		board.setBounds(0, 0, cfHeight, cfHeight);
		
		//text.setBounds(frameHeight, 0, frameWidth-frameHeight, frameHeight);
		
		System.out.println(frameWidth + ",s " + frameHeight);
		System.out.println(frame.getContentPane().getHeight());
		System.out.println(frame.getHeight());
		
		//frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		//frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		
		setText(board.getText());

		frame.pack();
	}
	
	public void returnMenu() {
		
		frame.remove(board);
		menu = new Menu(this, frameHeight, frameHeight);
		frame.add(menu);
		frame.setSize(1000, 1000);
		
	}
	
	public void newGame() {
		board.newGame();
	}
	
	public void undoMove() {
		board.loadMove();
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
