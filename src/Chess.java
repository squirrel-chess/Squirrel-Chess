import java.awt.BorderLayout;
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
	static long pausedTime=0;
	long minChange=0;
	static Time timePausedWhite;
	static Time timePausedBlack;
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
			
			if (pause.getText().equals("Pause")) {
				pause.setText("Play");
				pausedTime = System.currentTimeMillis();
				int minsWhite = board.getWhiteTime().getMins();
				int secsWhite = board.getWhiteTime().getSecs();
				int minsBlack = board.getBlackTime().getMins();
				int secsBlack = board.getBlackTime().getSecs();
				JOptionPane.showMessageDialog(null, "Game Paused.");
				timePausedWhite = new Time(minsWhite,secsWhite);
				timePausedBlack = new Time(minsBlack,secsBlack);
			
				System.out.println(pausedTime);
			} else 
				if (pause.getText().equals("Play")) {
					pause.setText("Pause");
					board.setBlackTime(timePausedBlack);
					board.setWhiteTime(timePausedWhite);
					System.out.println(board.getBlackTime());
					if (board.getWhiteTurn() == true) {
						setText(timePausedBlack  + " (Black)" + "<br>White's Turn<br>" + timePausedWhite + " (White)");
					} else {
						setText(timePausedBlack  + " (Black)" + "<br>Black's Turn<br>" + timePausedWhite + " (White)");
					}
					System.out.println(pausedTime);
					
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
