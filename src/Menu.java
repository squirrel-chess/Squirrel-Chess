import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	private Chess game;
	JLabel title;
	JButton play;
	JButton instruct;
	Font titleFont = new Font("Dyeline", Font.ITALIC, 50);
	public Menu(Chess game) {
		this.game = game;
		setLayout(null);
		setSize(1000, 1000);
		title = new JLabel();
		add(title);
		title.setText("Squirrel Chess");
		title.setFont(titleFont);
		title.setBounds(500, 100, 500, 100);
		play = new JButton();
		play.setText("Play Game");
		play.addActionListener((e) -> {
				this.game.setupGame();
		});
		instruct = new JButton();
		instruct.setText("Instuctions");
		add(play);
		play.setBounds(500, 200, 100, 20);
		add(instruct);
		instruct.setBounds(500, 300, 100, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
