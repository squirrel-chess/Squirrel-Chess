import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chess implements Serializable {

	private static final long serialVersionUID = -4112312333502533585L;
	private JFrame frame;
	private JLabel text;
	private JButton saveGame;
	private JButton loadGame;
	private JPanel bottomPanel;
	private Board board;

	public Chess() {
		frame = new JFrame("Squirrel Chess");
		text = new JLabel();
		board = new Board(this);
		bottomPanel = new JPanel();

		saveGame = new JButton("Save Game");
		saveGame.addActionListener((al) -> {
			try (FileOutputStream fos = new FileOutputStream(new File("src/savedGame.dat"));
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(new SavedGame(board));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		loadGame = new JButton("Load Game");
		loadGame.addActionListener((al) -> {
			System.out.println(board);
			try (FileInputStream fis = new FileInputStream(new File("src/savedGame.dat"));
					ObjectInputStream ois = new ObjectInputStream(fis)) {
				 SavedGame sg = (SavedGame) ois.readObject();
				 frame.remove(board);
				 //board = new Board(sg.getBoard());
				 board = sg.getBoard();
				 //frame.add(board, BorderLayout.CENTER);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(saveGame, BorderLayout.NORTH);
		bottomPanel.add(loadGame, BorderLayout.SOUTH);
		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.CENTER);
		frame.add(text, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		frame.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
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
