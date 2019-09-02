import java.util.ArrayList;

public class SimBoard {

	private Square[][] squares;
	public ArrayList<Piece> pieces;
	private Board b;
	
	private Position wKingPos;
	private Position bKingPos;
	
	public SimBoard(Board b, ArrayList<Piece> p) {
		
		this.b = b;
		pieces = p;
		
		squares = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(b, new Position(i, j));
			}	
		}
	}
	
	// removePiece? getPieceAtPos? getallFriendlyPiecePos?
	
	public boolean testCheck(boolean isWhite) {
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
	
}
