import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SquirrelChess implements Serializable {

	private static final long serialVersionUID = -4112312333502533585L;
	private JFrame frame;
	private Menu menu;
	private JLabel text;
	private JButton saveGame;
	private Board board;

	public SquirrelChess() {
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

	public void setupGame(Game game) {
		text = new JLabel();
		if (game == null) {
			board = new Board(this);
		} else {
			board = new Board(this, game);
		}
		gameGUISetup();
	}

	private void gameGUISetup() {
		saveGame = new JButton("Save Game");
		saveGame.addActionListener((al) -> {
			try (FileOutputStream fos = new FileOutputStream(new File("src/savedGame.dat"));
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(board.getGame());
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		frame.setLayout(new BorderLayout());
		frame.remove(menu);
		frame.add(board, BorderLayout.CENTER);
		frame.add(text, BorderLayout.EAST);
		frame.add(saveGame, BorderLayout.SOUTH);
		frame.setVisible(true);
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
		new SquirrelChess();
	}
}
