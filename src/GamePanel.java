import java.awt.GridLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private int panelWidth;
	private int panelHeight;
	
	private JPanel topGrid;
	private JPanel center;
	private JPanel bottomGrid;
	
	private int gridWidth;
	private int gridHeight;
	
	public GamePanel(int width, int height) {
		
		panelWidth = width;
		panelHeight = height;
		
		topGrid = new JPanel();
		topGrid.setLayout(new GridLayout(2, 8));
		
		center = new JPanel();
		
		bottomGrid = new JPanel();
		bottomGrid.setLayout(new GridLayout(2, 8));
		
	}
	
}
