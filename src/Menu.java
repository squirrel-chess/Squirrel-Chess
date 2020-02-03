import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 4253062957905769372L;
	private SquirrelChess main;
	JLabel title;
	JButton play;
	JButton instruct;
	JButton loadGame;
	Font titleFont = new Font("Dyeline", Font.ITALIC, 50);

	public Menu(SquirrelChess main) {
		this.main = main;
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
			main.setupGame(null);
		});
		instruct = new JButton();
		instruct.setText("Instructions");
		loadGame = new JButton();
		loadGame.setText("Load Game");
		loadGame.addActionListener((e) -> {
			try (FileInputStream fis = new FileInputStream(new File("src/savedGame.dat"));
					ObjectInputStream ois = new ObjectInputStream(fis)) {
				 main.setupGame((Game) ois.readObject());
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
