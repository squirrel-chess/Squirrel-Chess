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

public class SquirrelChess implements Serializable {

	private static final long serialVersionUID = -4112312333502533585L;
	private JFrame frame;
	private Menu menu;
	private JLabel text;
	private JButton saveGame;
	private Game game;

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

	public void setupGame() {
		text = new JLabel();
		game = new Game(this);
		gameGUISetup();
	}

	public void setupGame(SavedGame sg) {
		text = new JLabel();
		game = new Game(this, sg);
		gameGUISetup();
	}

	private void gameGUISetup() {
		saveGame = new JButton("Save Game");
		saveGame.addActionListener((al) -> {
			try (FileOutputStream fos = new FileOutputStream(new File("src/savedGame.dat"));
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(new SavedGame(pieceArrayToStringArray(game.getPieces()), game.getWhiteTime(), game.getBlackTime(), game.getWhiteTurn()));
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		frame.setLayout(new BorderLayout());
		frame.remove(menu);
		frame.add(game, BorderLayout.CENTER);
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
	
	private ArrayList<String> pieceArrayToStringArray(ArrayList<Piece> pieces) {
		ArrayList<String> ret = new ArrayList<String>();
		for (Piece p : pieces) {
			ret.add(p.getClass().getName().substring(0, 2) + p.getPos() + p.isWhite());
		}
		return ret;
	}

	public Dimension getFrameDimension() {
		return frame.getSize();
	}

	public static void main(String[] args) {
		new SquirrelChess();
	}
}
