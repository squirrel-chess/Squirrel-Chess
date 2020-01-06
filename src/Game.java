import java.util.ArrayList;

public class Game {
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

	public Game(ArrayList<Piece> pieces, Time whiteTime, Time blackTime) {
		this.pieces = pieces;
		this.whiteTime = whiteTime;
		this.blackTime = blackTime;

		if (pieces == null) {
			pieces.add(whiteR1);
			pieces.add(whiteR2);
			pieces.add(blackR1);
			pieces.add(blackR2);
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

		System.out.println("testing: " + piece.toString());

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

			System.out.println("\tmoving to: " + piece.toString());

			// test if it would move into check
			if (testCheck(pWhite)) {
				ret.remove(i);
				System.out.println("\t\tREMOVED!");
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
}
