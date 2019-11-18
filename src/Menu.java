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
	public Menu() {
		setSize(1000, 1000);
		title = new JLabel();
		add(title);
		title.setText("Squirrel Chess");
		title.setFont(titleFont);
		title.setLocation(250, 100);
		play = new JButton();
		instruct = new JButton();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
