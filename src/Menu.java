import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	private Chess game;
	private JLabel title;
	private JButton play;
	private JButton instruct;
	private JButton load;
	private Font titleFont = new Font("didot", Font.ITALIC, 60);
	private BufferedImage img;
	private BufferedImage back;
	private JLabel picLabel;

	public Menu(Chess game) {
		this.game = game;
		setLayout(null);

		setSize(1000, 1000);
		title = new JLabel();
		add(title);
		title.setText("Squirrel Chess");
		title.setFont(titleFont);
		title.setBounds(600, 75, 500, 100);
		play = new JButton();
		play.setText("Play Game");
		play.addActionListener((e) -> {
			this.game.setupGame();
		});
		instruct = new JButton();
		instruct.setText("Instructions");
		instruct.addActionListener((e) -> {
			try {
				URI uri = new URI("http://www.uschess.org/content/view/7324/");
				java.awt.Desktop.getDesktop().browse(uri);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		load = new JButton();
		load.setText("Load Game");
		load.addActionListener((e) -> {
			try {
				FileInputStream fis = new FileInputStream("src/savedGame.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.game.setupGame((SavedGame) ois.readObject());
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		try {
			img = ImageIO.read(new File("src/pic.png"));
			JLabel picLabel = new JLabel(new ImageIcon(img));
			picLabel.setBounds(0, 200, 1024, 518);
			add(picLabel);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		play.setBounds(100, 800, 200, 40);
		add(play);
		instruct.setBounds(400, 800, 200, 40);
		add(instruct);
		load.setBounds(700, 800, 200, 40);
		add(load);
		try {
			back = ImageIO.read(new File("src/angryimg (1).png"));
			JLabel picLabels = new JLabel(new ImageIcon(back));
			picLabels.setBounds(0, 0, 1000, 1000);
			add(picLabels);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
