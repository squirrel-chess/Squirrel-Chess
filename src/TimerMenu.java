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
	private int hourNum = 0;
	private JTextField mins;
	private JButton nMin;
	private JButton pMin;
	private JLabel min;
	private JButton nSec;
	private JButton pSec;
	private JTextField sec;
	private int secNum = 0;
	private int minNum = 0;
	private int yVal = 80;

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
		hours.setBounds(50, yVal, 30, 40);
		add(hours);
		nHour = new JButton();
		nHour.addActionListener((e)-> {
			if (hours.getText().matches("[0-9]+$") && Integer.parseInt(hours.getText())>0) {
				int i = Integer.parseInt(hours.getText());
				i--;
				hours.setText(""+i);	
			}else if(hours.getText().isEmpty()) {
				hours.setText("1");	
			}
		});
		nHour.setBounds(20, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("leftAr.png"));
			nHour.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		add(nHour);
		pHour = new JButton();
		pHour.setBounds(70, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("rightAr.png"));
			pHour.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		pHour.addActionListener((e)-> {
			if (hours.getText().matches("[0-9]+$") && Integer.parseInt(hours.getText())<60) {
				hourNum = Integer.parseInt(hours.getText());
				hourNum++;
				hours.setText(""+hourNum);	
			}else if(hours.getText().isEmpty()) {
				hours.setText("1");	
			}
		});
		add(pHour);
		
		
		
		mins = new JTextField();
		mins.setBounds(170, yVal, 30, 40);
		add(mins);
		nMin = new JButton();
		nMin.addActionListener((e)-> {
			if (mins.getText().matches("[0-9]+$") && Integer.parseInt(mins.getText())>0) {
				int i = Integer.parseInt(mins.getText());
				i--;
				mins.setText(""+i);	
			}else if(mins.getText().isEmpty()) {
				mins.setText("1");	
			}
		
		});
		nMin.setBounds(140, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("leftAr.png"));
			nMin.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		add(nMin);
		pMin = new JButton();
		pMin.setBounds(190, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("rightAr.png"));
			pMin.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		pMin.addActionListener((e)-> {
			if (mins.getText().matches("[0-9]+$") && Integer.parseInt(mins.getText())<59) {
				minNum = Integer.parseInt(mins.getText());
				minNum++;
				mins.setText(""+minNum);	
			}else if(mins.getText().isEmpty()) {
				mins.setText("1");	
			}
			else if(Integer.parseInt(mins.getText())==59) {
				if (hours.getText().matches("[0-9]+$")) {
					hourNum = Integer.parseInt(hours.getText());
					hourNum++;
					hours.setText(""+hourNum);	
				}else if(hours.getText().isEmpty()) {
					hours.setText("1");	
				}
				mins.setText("0");
			}
		
		});
		add(pMin);
		

		
		
		sec = new JTextField();
		sec.setBounds(290, yVal, 30, 40);
		add(sec);
		nSec = new JButton();
		nSec.addActionListener((e)-> {
			if (sec.getText().matches("[0-9]+$") && Integer.parseInt(sec.getText())>0) {
				int i = Integer.parseInt(sec.getText());
				i--;
				sec.setText(""+i);	
			}else if(sec.getText().isEmpty()) {
				sec.setText("1");	
			}
		
		});
		nSec.setBounds(260, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("leftAr.png"));
			nSec.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		add(nSec);
		pSec = new JButton();
		pSec.setBounds(310, yVal, 40, 40);
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("rightAr.png"));
			pSec.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		pSec.addActionListener((e)-> {
			if (sec.getText().matches("[0-9]+$") && Integer.parseInt(sec.getText())<59) {
				secNum = Integer.parseInt(sec.getText());
				secNum++;
				sec.setText(""+secNum);	
			}else if(sec.getText().isEmpty()) {
				sec.setText("1");	
			}else if(Integer.parseInt(sec.getText())==59) {
				if (mins.getText().matches("[0-9]+$") && Integer.parseInt(mins.getText())<59) {
					minNum = Integer.parseInt(mins.getText());
					minNum++;
					mins.setText(""+minNum);	
				}else if(mins.getText().isEmpty()) {
					mins.setText("1");	
				}
				else if(Integer.parseInt(mins.getText())==59) {
					hourNum++;
					hours.setText(""+hourNum);	
					mins.setText("0");
				}
				sec.setText("0");
			}
		
		});
		add(pSec);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}