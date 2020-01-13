import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements Serializable {

	private static final long serialVersionUID = 2499641607903690667L;
	private Main main;
	private Game game;
	private Square[][] squares;
	private Piece selectedPiece;
	private boolean isWhiteTurn;

	public Board(Main main) {
		this.main = main;
		game = new Game(null, this);
		initBoard();
	}

	public void playAgainMenu() {
		String[] options = { "No", "Yes" };
		if (JOptionPane.showOptionDialog(null, "Would you like to play again?", "Play Again", 0, 0, null, options,
				null) == 1)
			game = new Game(null, this);
		else
			System.exit(0);
	}

	public void highlightMoves(Piece p) {
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN);
		for (Position pos : p.getMoveSet(true)) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
	}

	public void unhighlightMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (isWhiteTurn) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (isWhiteTurn) {
					squares[i][j].setBackground(Color.WHITE);
				} else {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
				squares[i][j].setInMoveSet(false);
			}
		}
		selectedPiece = null;
	}

	public void initBoard() {
		squares = new Square[8][8];
		selectedPiece = null;
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(game, this, new Position(i, j));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				if ((i + j) % 2 == 1) {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				} else {
					squares[i][j].setBackground(Color.WHITE);
				}
				int a = i;
				int b = j;
				squares[i][j].addActionListener((e) -> {
					squares[a][b].click();
				});
				add(squares[i][j]);
			}
		}
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece p) {
		selectedPiece = p;
	}

	public void updatePic() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].setIcon(null);
			}
		}
		for (Piece p : game.getPieces()) {
			try {
				BufferedImage img;
				if (p.isWhite())
					img = ImageIO.read(this.getClass().getResourceAsStream(p.getClass().getName() + "W.png"));
				else
					img = ImageIO.read(this.getClass().getResourceAsStream(p.getClass().getName() + "B.png"));
				squares[p.getPos().getRow()][p.getPos().getCol()].setIcon(new ImageIcon(img));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Resources not located, stopping game");
				System.exit(0);
			}
		}
	}
	
	public void nextTurn(Time whiteTime, Time blackTime) {
		if (isWhiteTurn) {
			main.setText(blackTime + "<br>Black's Turn<br>" + whiteTime);
			if (whiteTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - Black wins!");
				playAgainMenu();
			}
		} else {
			main.setText(blackTime + "<br>White's Turn<br>" + whiteTime);
			if (blackTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - White wins!");
				playAgainMenu();
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (isWhiteTurn) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (isWhiteTurn) {
					squares[i][j].setBackground(Color.WHITE);
				} else {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		updatePic();
	}

	public Main getMain() {
		return main;
	}
	
	public Game getGame() {
		return game;
	}
}