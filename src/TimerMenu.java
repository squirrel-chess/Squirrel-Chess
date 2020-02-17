import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimerMenu extends JPanel implements ActionListener {
	private Chess game;
	private JButton start;
	private JButton back;
	private JTextField hours;
	private JButton nHour;
	private JButton pHour;
	private JLabel hour;

	public TimerMenu(Chess game) {
		this.game = game; 
		setLayout(null);
		setSize(500, 500);
		start = new JButton();
		start.setText("Start Game");
		start.addActionListener((e) -> {
			game.playGame();
		});
		start.setBounds(360, 400, 100, 40);
		add(start);
		back = new JButton();
		back.setText("Back");
		back.addActionListener((e) -> {
			game.playGame();
		});
		back.setBounds(40, 400, 100, 40);
		add(back);
		hours = new JTextField();
		hours.setBounds(50, 50, 20, 20);
		add(hours);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
