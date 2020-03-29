 import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {
	private Chess game;
	private Square[][] squares;
	private ArrayList<Piece> pieces;
	private Piece selectedPiece;
	private Time whiteTime;
	private Time blackTime;
	private boolean whiteTurn;
	private King king;

	public Position wKingPos;
	public Position bKingPos;
	
	private Stack<BoardLayout> moves;
	
	// Colors
	Color blackSquareColor = new Color(133, 77, 20);
	Color whiteSquareColor = new Color(255, 239, 204);
	Color darkColor = new Color(77, 40, 0);

	public Board(Chess game) {
		this.game = game;
		pieces = new ArrayList<Piece>();
		moves = new Stack<BoardLayout>();
		initBoard();
		newGame();
	}
	public ArrayList<Piece> getPieces() {
		return pieces;
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
		
		game.setText(getText());
		
		whiteTurn = true;
		initPieces();
	}

	public King getKing() {
		return king;
	}

	public void highlightMoves(Piece p) {
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN);		// dark green
		for (Position pos : p.getMoveSet(true)) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));	// light green
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
	}

	public void unhighlightMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					if (whiteTurn) {
						squares[i][j].setBackground(blackSquareColor);
					} else {
						squares[i][j].setBackground(blackSquareColor);
					}
				} else if (whiteTurn) {
					squares[i][j].setBackground(whiteSquareColor);
				} else {
					squares[i][j].setBackground(whiteSquareColor);
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
		//setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		squares = new Square[8][8];
		selectedPiece = null;	
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(this, new Position(i, j));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				if ((i + j) % 2 == 1) {
					squares[i][j].setBackground(blackSquareColor);
				} else {
					squares[i][j].setBackground(whiteSquareColor);
				}
				int a = i;
				int b = j;
				squares[i][j].addActionListener((e) -> {
					squares[a][b].click();
				});
				add(squares[i][j]);
			}
		}
		setBackground(darkColor);
	}

	Rook whiteR1;
	Rook whiteR2;
	Rook blackR1;
	Rook blackR2;

	private void initPieces() {
		blackR1 = new Rook(new Position(0, 0), this, false, "rookB.png");
		blackR2 = new Rook(new Position(0, 7), this, false, "rookB.png");
		whiteR1 = new Rook(new Position(7, 0), this, true, "rookW.png");
		whiteR2 = new Rook(new Position(7, 7), this, true, "rookW.png");

		pieces = new ArrayList<Piece>();
		pieces.add(whiteR1);
		pieces.add(whiteR2);
		pieces.add(blackR1);
		pieces.add(blackR2);

		// black pieces

		pieces.add(new Knight(new Position(0, 1), this, false, "knightB.png"));
		pieces.add(new Bishop(new Position(0, 2), this, false, "bishopB.png"));
		pieces.add(new Queen(new Position(0, 3), this, false, "queenB.png"));
		pieces.add(new King(new Position(0, 4), this, false, blackR1, blackR2, "kingB.png"));
		pieces.add(new Bishop(new Position(0, 5), this, false, "bishopB.png"));
		pieces.add(new Knight(new Position(0, 6), this, false, "knightB.png"));
		pieces.add(new Pawn(new Position(1, 0), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 1), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 2), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 3), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 4), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 5), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 6), this, false, "pawnB.png"));
		pieces.add(new Pawn(new Position(1, 7), this, false, "pawnB.png"));
		// white pieces
		pieces.add(new Pawn(new Position(6, 0), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 1), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 2), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 3), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 4), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 5), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 6), this, true, "pawnW.png"));
		pieces.add(new Pawn(new Position(6, 7), this, true, "pawnW.png"));
		pieces.add(new Knight(new Position(7, 1), this, true, "knightW.png"));
		pieces.add(new Bishop(new Position(7, 2), this, true, "bishopW.png"));
		pieces.add(new Queen(new Position(7, 3), this, true, "queenW.png"));
		pieces.add(new King(new Position(7, 4), this, true, whiteR1, whiteR2, "kingW.png"));
		pieces.add(new Bishop(new Position(7, 5), this, true, "bishopW.png"));
		pieces.add(new Knight(new Position(7, 6), this, true, "knightW.png"));

		wKingPos = new Position(7, 4);
		bKingPos = new Position(0, 4);

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
			squares[p.getPos().getRow()][p.getPos().getCol()].setIcon(new ImageIcon(p.getImage()));
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

	public ArrayList<Position> moveIntoCheck(Piece piece, ArrayList<Position> ret) {

		boolean pWhite = piece.isWhite;

		ArrayList<Position> list = ret;
		int size = ret.size();

		for (int i = size - 1; i >= 0; i--) { // iterate through possible move positions
			Position pos = list.get(i);

			// simmove the piece and save the take piece and original position
			Position original = piece.getPos();
			Piece taken = piece.simMove(pos);
			// if the piece is a king, the kingPos needs to be updated
			moveKingPos(pWhite, piece, pos);

			// test if it would move into check
			if (testCheck(pWhite)) {
				ret.remove(i);
			}

			// replace kingpos and simmoved piece
			moveKingPos(pWhite, piece, original);
			piece.simMove(original);
			replacePiece(taken);

		}
		
		return ret;

	}

	public boolean testCheck(boolean isWhite) { // checking if the king of isWhite color is in check
		for (Piece p : pieces) {
			if (p.isWhite != isWhite) {
				for (Position pos : p.getMoveSet(false)) {
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

					for (Position pos : p.getMoveSet(true)) { // iterate through all available moves

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

								moveKingPos(isWhite, p, original); // if the piece is a king, the kingPos needs to be updated

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
	
	public void saveMove() {
		
		System.out.println("save move");
		
		BoardLayout layout = new BoardLayout(this);
		
		moves.add(new BoardLayout(this));
		
		// TESTING
		System.out.println("WHAT'S BEING SAVED");
		for (Piece p : b.getPieces()) {
			System.out.println(p.toString());
		}
		System.out.println("\n ==== \n");
		
		System.out.println("\n");
		
	}
	
	public void loadMove() {
		
		System.out.println("load move");
		
		if (moves.size() > 0) {
			BoardLayout layout = moves.pop();
			
			System.out.println("PIECES");
			for (Piece p : pieces) {
				System.out.println(p.toString());
			}
			System.out.println("\n ==== \n");
			
			System.out.println("BOARDLAYOUT");
			for (Piece p : layout.pieces) {
				System.out.println(p.toString());
			}
			System.out.println("\n ==== \n");
			
			pieces = new ArrayList<Piece>(layout.pieces);
			whiteTurn = layout.whiteTurn;
			wKingPos = layout.wKingPos;
			bKingPos = layout.bKingPos;
			
			System.out.println("UNDO");
			for (Piece p : pieces) {
				System.out.println(p.toString());
			}
			System.out.println("\n ==== \n");
		}

		game.setText(getText());
		updatePic();
	
		System.out.println("\n");

	}

	public void nextTurn() {
		if (whiteTurn) {
			whiteTime.endTurn();
			
			if (whiteTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - Black wins!");
				
				playAgainMenu();
				
			} else {
				whiteTurn = false;
				blackTime.startTurn();
			}
		} else {
			blackTime.endTurn();
			
			if (blackTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - White wins!");
				
				playAgainMenu();
				
			} else {
				whiteTurn = true;
				whiteTime.startTurn();
			}
		}
		
		game.setText(getText());

	}

	public boolean getWhiteTurn() {
		return whiteTurn;
	}

	public Chess getGame() {
		return game;
	}
	
	public String getText() {
		if (whiteTurn) {
			return "<>" + blackTime + "<br>White's Turn<br>" + whiteTime;
		} else {
			return "<>" + blackTime + "<br>Black's Turn<br>" + whiteTime;
		}
			
	}
}