import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private int panelWidth;
	private int panelHeight;
	private int panelX;
	
	private JPanel topGrid;
	private JPanel center;
	private JPanel bottomGrid;
	
	private int gridWidth;
	private int gridHeight;
	private int centerHeight;
	
	ArrayList<Piece> whiteTaken;
	ArrayList<Piece> blackTaken;
	
	public GamePanel(int width, int height, int x) {
		
		// set values passed in from Chess
		panelWidth = width;
		panelHeight = height;
		panelX = x;
		
		// calculate width and height of grid
		gridHeight = panelHeight / 8;
		gridWidth = panelWidth / 8;
		
		// calculate height of the center JPanel
		centerHeight = panelHeight - (gridHeight * 4);
		
		// instantiating JPanels and setting bounds
		topGrid = new JPanel();
		topGrid.setLayout(new GridLayout(2, 8));
		topGrid.setBounds(panelX, 0, panelWidth, (gridHeight * 2));
		
		center = new JPanel();
		center.setBounds(panelX, (gridHeight * 2), panelWidth, centerHeight);
		
		bottomGrid = new JPanel();
		bottomGrid.setLayout(new GridLayout(2, 8));
		bottomGrid.setBounds(panelX, ((gridHeight * 2) + centerHeight), panelWidth, (gridHeight * 2));
		
		// instantiate ArrayLists - white taken is a list of WHITE PIECES that are dead
		whiteTaken = new ArrayList<Piece>();
		blackTaken = new ArrayList<Piece>();
		
	}
	
	public void addPiece(Piece p) {
		if (p.isWhite) {
			whiteTaken.add(p);
		} else {
			blackTaken.add(p);
		}
	}
	
	public void displayTaken() {
		
	}
	
	/* List of pieces displayed:
	 * Queen
	 * Rooks
	 * Bishops
	 * Knights
	 * Pawns
	 */
	
}
