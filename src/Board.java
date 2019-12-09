import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements Serializable {

	private static final long serialVersionUID = 2499641607903690667L;
	private Chess game;
	private Square[][] squares;
	private ArrayList<Piece> pieces;
	private Piece selectedPiece;
	private Time whiteTime;
	private Time blackTime;
	private boolean whiteCastle;
	private boolean blackCastle;
	private boolean whiteTurn;

	public Position wKingPos;
	public Position bKingPos;

	public Board(Chess game) {
		this.game = game;
		pieces = new ArrayList<Piece>();
		initBoard();
		newGame();
	}
	
	public Board(Board board) {
		this.game = board.game;
		this.squares = board.squares;
		this.pieces = board.pieces;
		this.selectedPiece = board.selectedPiece;
		this.whiteTime = board.whiteTime;
		this.blackTime = board.blackTime;
		this.whiteCastle = board.whiteCastle;
		this.blackCastle = board.blackCastle;
		this.whiteTurn = board.whiteTurn;
		this.wKingPos = board.wKingPos;
		this.bKingPos = board.bKingPos;
	}

	public void playAgainMenu() {
		String[] options = { "No", "Yes" };
		if (JOptionPane.showOptionDialog(null, "Would you like to play again?", "Play Again", 0, 0, null, options,
				null) == 1)
			newGame();
		else
			System.exit(0);
	}

	public void newGame() {
		int mins, secs;
		do {
			mins = Integer.parseInt(JOptionPane.showInputDialog("Enter number of minutes"));
			secs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seconds"));
			if (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0))
				JOptionPane.showMessageDialog(null, "Invalid time entered, enter time again.");
		} while (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0));
		whiteTime = new Time(mins, secs);
		blackTime = new Time(mins, secs);
		game.setText(whiteTime + "<br>White's turn.<br>" + blackTime);
		whiteTurn = true;
		whiteCastle = true;
		blackCastle = true;
		initPieces();
	}

	public void highlightMoves(Piece p) {
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN);
		for (Position pos : p.getMoveSet()) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
	}

	public void unhighlightMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (whiteTurn) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (whiteTurn) {
					squares[i][j].setBackground(Color.WHITE);
				} else {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
				squares[i][j].setInMoveSet(false);
			}
		}
		selectedPiece = null;
	}

	public void removePiece(Piece p) {
		pieces.remove(p);
	}
	
	private void initBoard() {
		squares = new Square[8][8];
		selectedPiece = null;
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(this, new Position(i, j));
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
		whiteCastle = true;
		blackCastle = true;
	}

	Rook whiteR1;
	Rook whiteR2;
	Rook blackR1;
	Rook blackR2;

	private void initPieces() {
		blackR1 = new Rook(new Position(0, 0), this, false);
		blackR2 = new Rook(new Position(0, 7), this, false);
		whiteR1 = new Rook(new Position(7, 0), this, true);
		whiteR2 = new Rook(new Position(7, 7), this, true);

		pieces = new ArrayList<Piece>();
		pieces.add(whiteR1);
		pieces.add(whiteR2);
		pieces.add(blackR1);
		pieces.add(blackR2);
		// black pieces
		pieces.add(new Knight(new Position(0, 1), this, false));
		pieces.add(new Bishop(new Position(0, 2), this, false));
		pieces.add(new Queen(new Position(0, 3), this, false));
		pieces.add(new King(new Position(0, 4), this, false, whiteR1, whiteR2));
		pieces.add(new Bishop(new Position(0, 5), this, false));
		pieces.add(new Knight(new Position(0, 6), this, false));
		pieces.add(new Pawn(new Position(1, 0), this, false));
		pieces.add(new Pawn(new Position(1, 1), this, false));
		pieces.add(new Pawn(new Position(1, 2), this, false));
		pieces.add(new Pawn(new Position(1, 3), this, false));
		pieces.add(new Pawn(new Position(1, 4), this, false));
		pieces.add(new Pawn(new Position(1, 5), this, false));
		pieces.add(new Pawn(new Position(1, 6), this, false));
		pieces.add(new Pawn(new Position(1, 7), this, false));
		// white pieces
		pieces.add(new Pawn(new Position(6, 0), this, true));
		pieces.add(new Pawn(new Position(6, 1), this, true));
		pieces.add(new Pawn(new Position(6, 2), this, true));
		pieces.add(new Pawn(new Position(6, 3), this, true));
		pieces.add(new Pawn(new Position(6, 4), this, true));
		pieces.add(new Pawn(new Position(6, 5), this, true));
		pieces.add(new Pawn(new Position(6, 6), this, true));
		pieces.add(new Pawn(new Position(6, 7), this, true));
		pieces.add(new Knight(new Position(7, 1), this, true));
		pieces.add(new Bishop(new Position(7, 2), this, true));
		pieces.add(new Queen(new Position(7, 3), this, true));
		pieces.add(new King(new Position(7, 4), this, true, blackR1, blackR2));
		pieces.add(new Bishop(new Position(7, 5), this, true));
		pieces.add(new Knight(new Position(7, 6), this, true));

		wKingPos = new Position(7, 4);
		bKingPos = new Position(0, 4);

		whiteCastle = true;
		blackCastle = true;

		updatePic();
	}

	public Rook getWhiteR1() {
		return whiteR1;
	}

	public Rook getWhiteR2() {
		return whiteR2;
	}

	public Rook getBlackR1() {
		return blackR1;
	}

	public Rook getBlackR2() {
		return blackR2;
	}

	public Piece getPieceAtPos(Position pos) {
		for (Piece p : pieces) {
			if (p.getPos().equals(pos)) {
				return p;
			}
		}
		return null;
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
		for (Piece p : pieces) {
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

	public void addPiece(Piece p) {
		pieces.add(p);
	}

	public ArrayList<Position> getAllFriendlyPiecePos(boolean isWhite) {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 0; i < pieces.size(); i++) {

			Piece p = pieces.get(i);

			if (p.isWhite && isWhite)
				ret.add(p.getPos());
			else if (!p.isWhite && !isWhite)
				ret.add(p.getPos());
		}
		return ret;
	}

	public boolean testCheck(boolean isWhite) { // checking if the king of isWhite color is in check
		for (Piece p : pieces) {
			if (p.isWhite != isWhite) {
				for (Position pos : p.getMoveSet()) {
					if (isWhite) {
						if (pos.equals(wKingPos)) {
							return true;
						}
					} else {
						if (pos.equals(bKingPos)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean testCheckmate(boolean isWhite) {

		if (testCheck(true) || testCheck(false)) { // if neither of the kings are in check, don't check for checkmate
			for (int i = 0; i < pieces.size(); i++) { // iterate through all pieces

				Piece p = pieces.get(i);

				if (p.isWhite == isWhite) { // only check move sets of color of king in check

					Position original = p.getPos(); // save original position of piece

					for (Position pos : p.getMoveSet()) { // iterate through all available moves

						Piece removed = p.simMove(pos); // save piece removed to put back later. Sim move the piece

						moveKingPos(isWhite, p, p.pos); // if the piece is a king, the kingPos needs to be updated

						if (isWhite) {
							if (!testCheck(true)) { // test if king of isWhite color is in check
								p.simMove(original); // move the piece to it's original position

								moveKingPos(isWhite, p, original); // if the piece is a king, the kingPos needs to be
																	// updated

								replacePiece(removed); // replace removed piece

								return false; // if not in check anymore, the king is not in check
							}
						} else {
							if (!testCheck(false)) { // test if king of isWhite color is in check

								p.simMove(original); // move the piece to it's original position

								moveKingPos(isWhite, p, original); // if the piece is a king, the kingPos needs to be
																	// updated

								replacePiece(removed); // replace the removed piece

								return false; // if not in check anymore, the king is not in check
							}
						}

						p.simMove(original); // even if not in check, move the piece to it's original position

						moveKingPos(isWhite, p, original); // if the piece is a king, the kingPos needs to be updated

						replacePiece(removed); // even if not in check, replace removed piece

					}
				}
			}
		} else { // if neither of the kings are in check, don't check for checkmate
			return false;
		}

		return true;
	}

	public void replacePiece(Piece p) { // for checkmate
		if (p != null) {
			pieces.add(p);
		}
	}

	public void moveKingPos(boolean isWhite, Piece p, Position pos) { // for checkmate
		if (p.isKing()) {
			if (isWhite) {
				wKingPos = pos;
			} else {
				bKingPos = pos;
			}
		}
	}

	public boolean whiteCanCastle() {
		return whiteCastle;
	}

	public boolean blackCanCastle() {
		return blackCastle;
	}

	public void setWhiteCastle(boolean whiteCastle) {
		this.whiteCastle = whiteCastle;
	}

	public void setBlackCastle(boolean blackCastle) {
		this.blackCastle = blackCastle;
	}

	public void nextTurn() {
		if (whiteTurn) {
			whiteTime.endTurn();
			game.setText(blackTime + "<br>Black's Turn<br>" + whiteTime);
			if (whiteTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - Black wins!");
				playAgainMenu();
			} else {
				whiteTurn = false;
				blackTime.startTurn();
			}
		} else {
			blackTime.endTurn();
			game.setText(blackTime + "<br>White's Turn<br>" + whiteTime);
			if (blackTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - White wins!");
				playAgainMenu();
			} else {
				whiteTurn = true;
				whiteTime.startTurn();
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (whiteTurn) {
						squares[i][j].setBackground(Color.LIGHT_GRAY);
					} else {
						squares[i][j].setBackground(Color.GRAY);
					}
				} else if (whiteTurn) {
					squares[i][j].setBackground(Color.WHITE);
				} else {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	public boolean getWhiteTurn() {
		return whiteTurn;
	}

	public Chess getGame() {
		return game;
	}
}