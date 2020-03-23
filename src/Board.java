import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private Chess main;
	private Square[][] squares;
	private Piece selectedPiece;
	private ArrayList<Piece> pieces;
	private Time whiteTime;
	private Time blackTime;
	private boolean whiteTurn;
	public Position wKingPos;
	public Position bKingPos;
	Rook whiteR1;
	Rook whiteR2;
	Rook blackR1;
	Rook blackR2;

	// Colors
	Color blackSquareColor = new Color(133, 77, 20);
	Color whiteSquareColor = new Color(255, 239, 204);
	Color darkColor = new Color(77, 40, 0);

	public Board(Chess main) {
		selectedPiece = null;
		this.main = main;
		newGame();
		initBoard();
	}

	public Board(Chess main, SavedGame sg) {
		selectedPiece = null;
		this.main = main;
		this.whiteTime = sg.getWhiteTime();
		this.blackTime = sg.getBlackTime();
		this.whiteTurn = sg.getWhiteTurn();
		Position pos;
		boolean isWhite;
		String wKingString = "";
		String bKingString = "";
		pieces = new ArrayList<Piece>();
		for (String s : sg.getPieceStrings()) {
			isWhite = (s.length() == 12);
			pos = new Position(s.substring(2, 8));
			switch (s.substring(0, 2)) {
			case "Bi":
				pieces.add(new Bishop(pos, this, isWhite));
				break;

			case "Ki":
				if (isWhite)
					wKingString = s;
				else
					bKingString = s;
				break;

			case "Kn":
				pieces.add(new Knight(pos, this, isWhite));
				break;

			case "Pa":
				pieces.add(new Pawn(pos, this, isWhite));
				break;

			case "Qu":
				pieces.add(new Queen(pos, this, isWhite));
				break;

			case "Ro":
				if (isWhite) {
					if (whiteR1 == null) {
						whiteR1 = new Rook(pos, this, true);
						pieces.add(whiteR1);
					} else {
						whiteR2 = new Rook(pos, this, true);
						pieces.add(whiteR2);
					}
				} else {
					if (blackR1 == null) {
						blackR1 = new Rook(pos, this, false);
						pieces.add(blackR1);
					} else {
						blackR2 = new Rook(pos, this, false);
						pieces.add(blackR2);
					}
				}
				break;
			}
		}
		pieces.add(new King(new Position(wKingString.substring(2, 8)), this, true, whiteR1, whiteR2));
		pieces.add(new King(new Position(bKingString.substring(2, 8)), this, false, blackR1, blackR2));

		for (Piece p : pieces) {
			if (p instanceof King) {
				if (p.isWhite())
					wKingPos = p.getPos();
				else
					bKingPos = p.getPos();
			}
		}
		if (whiteTurn) {
			main.setText(blackTime + "<br>White's Turn<br>" + whiteTime);
			whiteTime.startTurn();
		} else {
			main.setText(blackTime + "<br>Black's Turn<br>" + whiteTime);
			blackTime.startTurn();
		}
		initBoard();
	}

	private void newGame() {
		int mins, secs;
		do {
			mins = Integer.parseInt(JOptionPane.showInputDialog("Enter number of minutes"));
			secs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seconds"));
			if (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0))
				JOptionPane.showMessageDialog(null, "Invalid time entered, enter time again.");
		} while (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0));

		whiteTime = new Time(mins, secs);
		blackTime = new Time(mins, secs);
		wKingPos = new Position(7, 4);
		bKingPos = new Position(0, 4);

		pieces = new ArrayList<Piece>();
		whiteR1 = new Rook(new Position(0, 0), this, false);
		whiteR2 = new Rook(new Position(0, 7), this, false);
		blackR1 = new Rook(new Position(7, 0), this, true);
		blackR2 = new Rook(new Position(7, 7), this, true);

		pieces.add(whiteR1);
		pieces.add(whiteR2);
		pieces.add(blackR1);
		pieces.add(blackR2);

		pieces.add(new Knight(new Position(0, 1), this, false));
		pieces.add(new Bishop(new Position(0, 2), this, false));
		pieces.add(new Queen(new Position(0, 3), this, false));
		pieces.add(new King(new Position(0, 4), this, false, blackR1, blackR2));
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
		pieces.add(new King(new Position(7, 4), this, true, whiteR1, whiteR2));
		pieces.add(new Bishop(new Position(7, 5), this, true));
		pieces.add(new Knight(new Position(7, 6), this, true));
		
		main.setText(getText());

		whiteTurn = true;
		System.out.println(whiteTurn);
		main.setText(blackTime + "<br>White's Turn<br>" + whiteTime);
	}
	
	private void repaintSquares() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0)
					squares[i][j].setBackground(Color.WHITE);
				else
					squares[i][j].setBackground(Color.LIGHT_GRAY);
			}
		}

	}

	public void playAgainMenu() {
		String[] options = { "No", "Yes" };
		if (JOptionPane.showOptionDialog(null, "Would you like to play again?", "Play Again", 0, 0, null, options,
				null) == 1) {
			newGame();
			repaintSquares();
		} else {
			System.exit(0);
		}
	}

	public void highlightMoves(Piece p) {
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN); // dark green
		for (Position pos : p.getMoveSet(true)) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160)); // light green
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
		updateGraphics();
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
		updateGraphics();
	}

	private void initBoard() {
		// setPreferredSize(new Dimension((int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110, (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 110));
		squares = new Square[8][8];
		selectedPiece = null;
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(this, new Position(i, j));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				if ((i + j) % 2 == 0 && whiteTurn)
					squares[i][j].setBackground(Color.WHITE);
				else if ((i + j) % 2 == 1 && !whiteTurn)
					squares[i][j].setBackground(Color.GRAY);
				else
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				int a = i;
				int b = j;
				squares[i][j].addActionListener((e) -> {
					squares[a][b].click();
				});
				add(squares[i][j]);
			}
		}
		updateGraphics();
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece p) {
		selectedPiece = p;
	}

	public void updateGraphics() {
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

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public ArrayList<Position> getFriendlyPieces(boolean isWhite) {
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

	public void removePiece(Piece p) {
		pieces.remove(p);
	}

	public Piece getPieceAtPos(Position pos) {
		for (Piece p : pieces) {
			if (p.getPos().equals(pos))
				return p;
		}
		return null;
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

	public ArrayList<Position> moveIntoCheck(Piece piece, ArrayList<Position> ret) {

		boolean pWhite = piece.isWhite;

		// if king is not already in check
		// if (!testCheck(pWhite)) {

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

	public boolean getWhiteTurn() {
		return whiteTurn;
	}

	public void nextTurn() {
		if (whiteTurn) {
			whiteTime.endTurn();
			if (whiteTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - Black wins!");
				playAgainMenu();
			} else {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if ((i + j) % 2 == 1)
							squares[i][j].setBackground(Color.GRAY);
						else
							squares[i][j].setBackground(Color.LIGHT_GRAY);
					}
				}
				blackTime.startTurn();
				whiteTurn = !whiteTurn;
			}
		} else {
			blackTime.endTurn();
			if (blackTime.isZero()) {
				JOptionPane.showMessageDialog(null, "Timeout - White wins!");

				playAgainMenu();
			} else {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if ((i + j) % 2 == 1)
							squares[i][j].setBackground(Color.LIGHT_GRAY);
						else
							squares[i][j].setBackground(Color.WHITE);
					}
				}
				whiteTime.startTurn();
				whiteTurn = !whiteTurn;
			}
		}
		updateGraphics();
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
	
	public Chess getMain() {
		return main;
	}

	public void addPiece(Piece p) {
		pieces.add(p);
	}

	public Time getWhiteTime() {
		return whiteTime;
	}

	public Time getBlackTime() {
		return blackTime;
	}

	public Position getWKingPos() {
		return wKingPos;
	}

	public Position getBKingPos() {
		return bKingPos;
	}

	public void setWhiteTime(Time t) {
		whiteTime = t;
	}

	public void setBlackTime(Time t) {
		blackTime = t;
	}

	public Chess getGame() {
		return main;
	}

	public String getText() {
		if (whiteTurn) {
			return "<>" + blackTime + " (Black)" + "<br>White's Turn<br>" + whiteTime + " (White)";
		} else {
			return "<>" + blackTime + " (Black)" + "<br>Black's Turn<br>" + whiteTime + " (White)";
		}
	}
}