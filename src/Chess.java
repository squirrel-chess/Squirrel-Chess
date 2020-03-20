import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chess {

	private JFrame frame;
	JPanel panelButtons;
	private Menu menu;
	private JLabel text;
	private Board board;
	JButton pause;
	static long pausedTime = 0;
	long minChange = 0;
	static Time timePausedWhite;
	static Time timePausedBlack;
	private int mins, secs;
	private PauseScreen pauseScreen;
	private Board bor;

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
		panelButtons = new JPanel();
		pause = new JButton();

		frame.setLayout(new BorderLayout());
		frame.remove(menu);
		frame.add(panelButtons, BorderLayout.WEST);
		frame.add(board, BorderLayout.CENTER);
		frame.add(text, BorderLayout.EAST);
		panelButtons.setLayout(new BorderLayout());

		panelButtons.add(pause, BorderLayout.NORTH);
		pause.setText("Pause");
		pause.setPreferredSize(new Dimension(100, 500));

		pause.addActionListener((e) -> {
			bor = board;
			if (pause.getText().equals("Pause")) {
				board.updatePic();
				pauseScreen = new PauseScreen();
				frame.remove(board);
				frame.add(pauseScreen);
				pause.setText("Play");
				pausedTime = System.currentTimeMillis();
				JOptionPane.showMessageDialog(null, "Game Paused.");
				board.getWhiteTime().setPaused(true);
				board.getBlackTime().setPaused(true);
				if (board.getWhiteTurn()) {
					board.getWhiteTime().endTurn();
					setText(board.getBlackTime() + " (Black)" + "<br>White's Turn<br>" + board.getWhiteTime()
					+ " (White)");
//					board.getBlackTime().startTurn();
//					board.getBlackTime().endTurn();
					
				} else {
					board.getBlackTime().endTurn();
					board.getWhiteTime().startTurn();
					board.getWhiteTime().endTurn();
					setText(board.getBlackTime() + " (Black)" + "<br>Black's Turn<br>" + board.getWhiteTime()
							+ " (White)");
				}
//				board.getWhiteTime().setSecs(board.getWhiteTime().getSecs()+1);
//				board.getBlackTime().setSecs(board.getBlackTime().getSecs()+1);
			} else if (pause.getText().equals("Play")) {
				if (board.getWhiteTurn()) {
					board.getWhiteTime().startTurn();
				} else {
					board.getBlackTime().startTurn();
				}
				System.out.println(board.getWhiteTime().getLastTime() + "last");
				frame.add(board);
				board.updatePic();

				// for (int i = 0; i < 8; i++) {
				// for (int j = 0; j < 8; j++) {
				// if(board.getPieceAtPos(new Position(i, j))==null) {
				//
				// if ((i + j) % 2 == 1) {
				// if (board.getWhiteTurn()) {
				// board.getSquares()[i][j].setBackground(Color.LIGHT_GRAY);
				//
				// } else {
				// board.getSquares()[i][j].setBackground(Color.GRAY);
				// }
				// } else if (board.getWhiteTurn()) {
				// board.getSquares()[i][j].setBackground(Color.WHITE);
				// } else {
				// board.getSquares()[i][j].setBackground(Color.LIGHT_GRAY);
				// }
				//
				// }
				// }
				// }

				board.getWhiteTime().setPaused(false);
				board.getBlackTime().setPaused(false);
				pause.setText("Pause");
				frame.pack();
				pauseScreen = new PauseScreen();

			}

		});

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));

	}

	public Dimension getFrameDimension() {
		return frame.getSize();
	}

	public static void main(String[] args) {
		new Chess();
	}
}
