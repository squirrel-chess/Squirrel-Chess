import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	private Chess game;
	JLabel title;
	JButton play;
	JButton instruct;
	Font titleFont = new Font("didot", Font.ITALIC, 60);
	BufferedImage img;
	BufferedImage back;
	JLabel picLabel;
	public Menu(Chess game) {
		this.game = game;
		setLayout(null);
		try {
			back = ImageIO.read(new File("src/angryimg(1).png"));
			JLabel picLabel = new JLabel(new ImageIcon(img));
			picLabel.setBounds(0, 200, 1024, 518);
			add(picLabel);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			JOptionPane.showMessageDialog(null, "http://www.uschess.org/content/view/7324/");
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
		add(play);
		play.setBounds(100, 800, 200, 40);
		add(instruct);
		instruct.setBounds(400, 800, 200, 40);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
