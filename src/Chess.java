import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Chess {

	private static final long serialVersionUID = -4112312333502533585L;
	private JFrame frame;
	private Menu menu;
	//private JLabel text;
	private Board board;
	private int mins, secs;
	private PauseScreen pauseScreen;

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
		gamePanel.setTimeText("<html>" + str + "</html>");
		frame.pack();
	}
	
	public Board getBoard() {
		return board;
	}

	public void setupGame() {
		// text = new JLabel();
		gamePanel = new GamePanel(this, new JFrame(), 0, 0, 0); // to make sure the bottomGrid panel doesn't jump to the
																// top left
		board = new Board(this);
		gamePanel = new GamePanel(this, frame, frameWidth - frameHeight, frameHeight, frameHeight);

		frame.remove(menu);
		frame.setLayout(null);
		frame.add(board/* , BorderLayout.CENTER */);
		// frame.add(text/*, BorderLayout.EAST*/);

		board.setBounds(0, 0, frameHeight, frameHeight);

		// text.setBounds(frameHeight, 0, frameWidth-frameHeight, frameHeight);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		// frame.setSize(new Dimension((int)
		// Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));

		setText(board.getText());
	}

	public void setupGame(SavedGame sg) {
		board = new Board(this, sg);
	}

	public void returnMenu() {
		frame.remove(board);
		menu = new Menu(this);
		frame.add(menu);
		frame.setSize(1000, 1000);
		frame.remove(board);
		menu = new Menu(this);
		frame.add(menu);
		frame.setSize(1000, 1000);
	}
	
	private ArrayList<String> pieceArrayToStringArray(ArrayList<Piece> pieces) {
		ArrayList<String> ret = new ArrayList<String>();
		for (Piece p : pieces) {
			ret.add(p.getClass().getName().substring(0, 2) + p.getPos() + p.isWhite());
		}
		return ret;
	}

	public void pauseClicked() {
		board.updateGraphics();

		frame.remove(board);
		pauseScreen = new PauseScreen();
		frame.setSize(frameWidth, frameHeight);
		frame.add(pauseScreen);
		// pausedTime = System.currentTimeMillis();
		JOptionPane.showMessageDialog(null, "Game Paused.");
		board.getWhiteTime().setPaused(true);
		board.getBlackTime().setPaused(true);

	}

	public void playClicked() {
		board.updateGraphics();
		if (board.getWhiteTurn()) {
			board.getWhiteTime().startTurn();
		} else {
			board.getBlackTime().startTurn();
		}
		System.out.println(board.getWhiteTime().getLastTime() + "last");
		frame.add(board);
		board.getWhiteTime().setPaused(false);
		board.getBlackTime().setPaused(false);
		frame.pack();
		pauseScreen = new PauseScreen();
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
