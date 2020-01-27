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
	private SquirrelChess main;
	private Game game;
	private Square[][] squares;
	private Piece selectedPiece;

	public Board(SquirrelChess main) {
		this.main = main;
		game = new Game(this);
		main.setText(game.getBlackTime() + "<br>White's Turn<br>" + game.getWhiteTime());
		initBoard();
	}
	
	public Board(SquirrelChess main, Game game) {
		this.main = main;
		this.game = game;
		main.setText(game.getBlackTime() + "<br>White's Turn<br>" + game.getWhiteTime());
		initBoard();
	}

	public void playAgainMenu() {
		String[] options = { "No", "Yes" };
		if (JOptionPane.showOptionDialog(null, "Would you like to play again?", "Play Again", 0, 0, null, options,
				null) == 1)
			game = new Game(this);
		else
			System.exit(0);
	}

	public void highlightMoves(Piece p) {
		System.out.println("Got here 2");
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN);
		for (Position pos : p.getMoveSet(true)) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
	}

	public void unhighlightMoves() {
		System.out.println(game.getWhiteTurn());
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (game.getWhiteTurn()) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (game.getWhiteTurn()) {
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
		updatePic();
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
		if (game.getWhiteTurn()) {
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
					if (game.getWhiteTurn()) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (game.getWhiteTurn()) {
					squares[i][j].setBackground(Color.WHITE);
				} else {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		updatePic();
	}

	public SquirrelChess getMain() {
		return main;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}