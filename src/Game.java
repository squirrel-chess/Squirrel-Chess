import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game implements Serializable {

	private static final long serialVersionUID = 8353142734621333312L;
	private ArrayList<Piece> pieces;
	private Board board;
	private Time whiteTime;
	private Time blackTime;
	private boolean whiteTurn;

	public Position wKingPos;
	public Position bKingPos;

	Rook whiteR1;
	Rook whiteR2;
	Rook blackR1;
	Rook blackR2;

	public Game(Board board) {
		int mins, secs;
		do {
			System.out.println("this works");
			mins = Integer.parseInt(JOptionPane.showInputDialog("Enter number of minutes"));
			secs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seconds"));
			if (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0))
				JOptionPane.showMessageDialog(null, "Invalid time entered, enter time again.");
		} while (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0));

		whiteTime = new Time(mins, secs);
		blackTime = new Time(mins, secs);
		whiteTurn = true;
		wKingPos = new Position(7, 4);
		bKingPos = new Position(0, 4);
		this.board = board;

		pieces = new ArrayList<Piece>();
		whiteR1 = new Rook(new Position(0, 0), this, board, false);
		whiteR2 = new Rook(new Position(0, 7), this, board, false);
		blackR1 = new Rook(new Position(7, 0), this, board, true);
		blackR2 = new Rook(new Position(7, 7), this, board, true);

		pieces.add(whiteR1);
		pieces.add(whiteR2);
		pieces.add(blackR1);
		pieces.add(blackR2);

		pieces.add(new Knight(new Position(0, 1), this, board, false));
		pieces.add(new Bishop(new Position(0, 2), this, board, false));
		pieces.add(new Queen(new Position(0, 3), this, board, false));
		pieces.add(new King(new Position(0, 4), this, board, false, blackR1, blackR2));
		pieces.add(new Bishop(new Position(0, 5), this, board, false));
		pieces.add(new Knight(new Position(0, 6), this, board, false));
		pieces.add(new Pawn(new Position(1, 0), this, board, false));
		pieces.add(new Pawn(new Position(1, 1), this, board, false));
		pieces.add(new Pawn(new Position(1, 2), this, board, false));
		pieces.add(new Pawn(new Position(1, 3), this, board, false));
		pieces.add(new Pawn(new Position(1, 4), this, board, false));
		pieces.add(new Pawn(new Position(1, 5), this, board, false));
		pieces.add(new Pawn(new Position(1, 6), this, board, false));
		pieces.add(new Pawn(new Position(1, 7), this, board, false));
		// white pieces
		pieces.add(new Pawn(new Position(6, 0), this, board, true));
		pieces.add(new Pawn(new Position(6, 1), this, board, true));
		pieces.add(new Pawn(new Position(6, 2), this, board, true));
		pieces.add(new Pawn(new Position(6, 3), this, board, true));
		pieces.add(new Pawn(new Position(6, 4), this, board, true));
		pieces.add(new Pawn(new Position(6, 5), this, board, true));
		pieces.add(new Pawn(new Position(6, 6), this, board, true));
		pieces.add(new Pawn(new Position(6, 7), this, board, true));
		pieces.add(new Knight(new Position(7, 1), this, board, true));
		pieces.add(new Bishop(new Position(7, 2), this, board, true));
		pieces.add(new Queen(new Position(7, 3), this, board, true));
		pieces.add(new King(new Position(7, 4), this, board, true, whiteR1, whiteR2));
		pieces.add(new Bishop(new Position(7, 5), this, board, true));
		pieces.add(new Knight(new Position(7, 6), this, board, true));
	}

	public Game(Board board, ArrayList<Piece> pieces, boolean whiteTurn) {
		int mins, secs;
		do {
			mins = Integer.parseInt(JOptionPane.showInputDialog("Enter number of minutes"));
			secs = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seconds"));
			if (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0))
				JOptionPane.showMessageDialog(null, "Invalid time entered, enter time again.");
		} while (!(mins >= 0 && secs >= 0 && secs < 60) || (mins == 0 && secs == 0));

		whiteTime = new Time(mins, secs);
		blackTime = new Time(mins, secs);
		this.board = board;
		this.pieces = pieces;
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
		// }
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
		System.out.println(whiteTime + " " + blackTime);
		whiteTurn = !whiteTurn;
		if (whiteTurn) {
			whiteTime.endTurn();
			board.nextTurn(whiteTime, blackTime);
			blackTime.startTurn();
		} else {
			blackTime.endTurn();
			board.nextTurn(whiteTime, blackTime);
			whiteTime.startTurn();
		}
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

	public void addPiece(Piece p) {
		pieces.add(p);
	}

	public Time getWhiteTime() {
		return whiteTime;
	}

	public Time getBlackTime() {
		return blackTime;
	}
	
}
