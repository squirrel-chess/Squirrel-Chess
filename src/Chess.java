import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chess {
	
	private JFrame frame;
	private JLabel text;
	private Board board;
	private int mins, secs;
	
	public Chess() {
		frame = new JFrame("Squirrel Chess");
		text = new JLabel();
		board = new Board(this);
		JPanel rightSide = new JPanel();
		JButton saveGame = new JButton("Save Game");
		saveGame.addActionListener((e) -> {
			try (FileOutputStream fos = new FileOutputStream(new File("src/savedGame.dat"));
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(new SavedGame(board.get));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		rightSide.setLayout(new BorderLayout());
		rightSide.add(text, BorderLayout.SOUTH);
		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.add(rightSide, BorderLayout.EAST);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
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
