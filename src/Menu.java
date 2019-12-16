import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 4253062957905769372L;
	private Chess game;
	JLabel title;
	JButton play;
	JButton instruct;
	JButton loadGame;
	Font titleFont = new Font("Dyeline", Font.ITALIC, 50);

	public Menu(Chess game) {
		this.game = game;
		setLayout(null);
		setSize(1000, 1000);
		title = new JLabel();
		add(title);
		title.setText("Squirrel Chess");
		title.setFont(titleFont);
		title.setBounds(0, 100, 1000, 100);
		play = new JButton();
		play.setText("Play Game");
		play.addActionListener((e) -> {
			this.game.setupGame();
		});
		instruct = new JButton();
		instruct.setText("Instuctions");
		loadGame = new JButton();
		loadGame.setText("Load Game");
		loadGame.addActionListener((e) -> {
			try (FileInputStream fis = new FileInputStream(new File("src/savedGame.dat"));
					ObjectInputStream ois = new ObjectInputStream(fis)) {
				 SavedGame sg = (SavedGame) ois.readObject();
				 Board temp = sg.getBoard();
				 this.game.setupGame(new Board(game, temp.getAllPieces(), temp.getWhiteTime(), temp.getBlackTime(), temp.getWhiteTurn(), temp.getWKingPos(), temp.getBKingPos()));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
		});
		add(play);
		play.setBounds(500, 200, 100, 20);
		add(instruct);
		instruct.setBounds(500, 300, 100, 20);
		add(loadGame);
		loadGame.setBounds(500, 400, 100, 20);
	}
}
