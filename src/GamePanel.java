import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private int panelWidth;
	private int panelHeight;
	private int panelX;

	// these grids are just the panels
	private JPanel topGrid;
	private JPanel center;
	private JPanel bottomGrid;

	private int gridWidth;
	private int gridHeight;
	private int centerHeight;

	private ArrayList<Piece> whiteTaken;
	private ArrayList<Piece> blackTaken;
	private ArrayList<Integer> whiteDisplay;
	private ArrayList<Integer> blackDisplay;
	
	// 2D array of Labels
	private JLabel[][] whiteGrid;
	private JLabel[][] blackGrid;
	
	// Images
	private BufferedImage whiteQueenImg;
	private BufferedImage whiteRookImg;
	private BufferedImage whiteBishopImg;
	private BufferedImage whiteKnightImg;
	private BufferedImage whitePawnImg;
	private BufferedImage blackQueenImg;
	private BufferedImage blackRookImg;
	private BufferedImage blackBishopImg;
	private BufferedImage blackKnightImg;
	private BufferedImage blackPawnImg;

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
		
		add(topGrid);
		add(center);
		add(bottomGrid);

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

		setupGrids();
		setupImages();
	}
	
	public void setupGrids() {
		whiteGrid = new JLabel[2][8];
		blackGrid = new JLabel[2][8];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				
				//TESTING
				System.out.println("i: " + i + ", j: " + j);
				
				whiteGrid[i][j] = new JLabel();
				blackGrid[i][j] = new JLabel();
				
				// TEST
				whiteGrid[i][j].setOpaque(true);
				blackGrid[i][j].setOpaque(true);

				whiteGrid[i][j].setBackground(Color.CYAN);
				
				topGrid.add(whiteGrid[i][j]);
				bottomGrid.add(blackGrid[i][j]);
			}
		}
	}
	
	public void setupImages() {
		try {
			whiteQueenImg = ImageIO.read(this.getClass().getResourceAsStream("queenW.png"));
			whiteRookImg = ImageIO.read(this.getClass().getResourceAsStream("rookW.png"));
			whiteBishopImg = ImageIO.read(this.getClass().getResourceAsStream("bishopW.png"));
			whiteKnightImg = ImageIO.read(this.getClass().getResourceAsStream("knightW.png"));
			whitePawnImg = ImageIO.read(this.getClass().getResourceAsStream("pawnW.png"));
			
			blackQueenImg = ImageIO.read(this.getClass().getResourceAsStream("queenB.png"));
			blackRookImg = ImageIO.read(this.getClass().getResourceAsStream("rookB.png"));
			blackBishopImg = ImageIO.read(this.getClass().getResourceAsStream("bishopB.png"));
			blackKnightImg = ImageIO.read(this.getClass().getResourceAsStream("knightB.png"));
			blackPawnImg = ImageIO.read(this.getClass().getResourceAsStream("pawnB.png"));
		} catch (IOException e) {
			
		}
	}

	public void addPiece(Piece p) {
		if (p.isWhite) {
			whiteTaken.add(p);
		} else {
			blackTaken.add(p);
		}

		sortPieces();
		displayTaken();
	}

	// TAKEN = PIECES, DISPLAY = INTS
	public void sortPieces() {
		whiteDisplay = new ArrayList<Integer>();
		blackDisplay = new ArrayList<Integer>();

		for (int j = 1; j <= 5; j++) {
			for (int i = 0; i < whiteTaken.size(); i++) {
				if (whiteTaken.get(i).pieceType() == j) {
					whiteDisplay.add(j);
				}
			}
		}

		// each piece assigned a number (see bottom) - checks for all of them to put in right order
		for (int j = 1; j <= 5; j++) {
			for (int i = 0; i < blackTaken.size(); i++) {
				if (blackTaken.get(i).pieceType() == j) {
					blackDisplay.add(j);
				}
			}
		}

		// TESTING TESTING TESTING
		System.out.println("\n blackDisplay:");
		for (int i = 0; i < blackDisplay.size(); i++) {
			System.out.println(i + ": " + blackDisplay.get(i));
		}
		System.out.println("===============\n");
		
		System.out.println("\n whiteDisplay:");
		for (int i = 0; i < whiteDisplay.size(); i++) {
			System.out.println(i + ": " + whiteDisplay.get(i));
		}
		System.out.println("===============\n");
		// TESTING TESTING TESTING
		
	}

	public void displayTaken() {
		
		// TESTING TESTING TESTING
		System.out.println("displaying taken");
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				whiteGrid[i][j].setIcon(null);
				blackGrid[i][j].setIcon(null);
			}
		}
		
		for (int i = 0; i < whiteDisplay.size(); i++) {
			if (i < 8) {
				whiteGrid[0][i].setIcon(new ImageIcon(whiteImg(whiteDisplay.get(i))));
			} else {
				whiteGrid[1][i - 8].setIcon(new ImageIcon(whiteImg(whiteDisplay.get(i))));
			}
		}
		
		for (int i = 0; i < blackDisplay.size(); i++) {
			if (i < 8) {
				blackGrid[0][i].setIcon(new ImageIcon(whiteImg(blackDisplay.get(i))));
			} else {
				blackGrid[1][i - 8].setIcon(new ImageIcon(whiteImg(blackDisplay.get(i))));
			}
		}
		
	}
	
	// hashmap with if statements :)
	public BufferedImage whiteImg(int i) {
		if (i == 1) {
			return whiteQueenImg;
		} else if (i == 2) {
			return whiteRookImg;
		} else if (i == 3) {
			return whiteBishopImg;
		} else if (i == 4) {
			return whiteKnightImg;
		} else if (i == 5) {
			return whitePawnImg;
		} else {
			return null;
		}
	}
	
	public BufferedImage blackImg(int i) {
		if (i == 1) {
			return blackQueenImg;
		} else if (i == 2) {
			return blackRookImg;
		} else if (i == 3) {
			return blackBishopImg;
		} else if (i == 4) {
			return blackKnightImg;
		} else if (i == 5) {
			return blackPawnImg;
		} else {
			return null;
		}
	}

	/*
	 * List of pieces displayed: 
	 * Queen = 1 
	 * Rooks = 2 
	 * Bishops = 3 
	 * Knights = 4 
	 * Pawns = 5
	 */

}
