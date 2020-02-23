import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
		hours.setBounds(40, 50, 30, 20);
		add(hours);
		nHour = new JButton();
		nHour.addActionListener((e)-> {
			if (hours.getText().matches("[0-9] + $")) {
				int i = Integer.parseInt(hours.getText())+1;
				hours.setText(Integer.toString(i));	
			}else if(hours.getText().isEmpty()) {
				hours.setText("1");	
			}
		
		});
		nHour.setBounds(20, 50, 20, 20);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("leftAr.png"));
			nHour.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		add(nHour);
		pHour = new JButton();
		// add action listener to nHour
		pHour.setBounds(70, 50, 20, 20);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("rightAr.png"));
			pHour.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		add(pHour);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
