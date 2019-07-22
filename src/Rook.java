import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		int foundCol1 = 0;
		int foundCol2 = 7;
		int foundRow1 = 0;
		int foundRow2 = 7;
		
		for (int i = 1; i < pos.getRow(); i++) {
			if (b.getPieceAtPos(new Position(i, pos.getCol())) != null) {
				foundCol1 = i;
			}
		}
		for (int i = 6; i > pos.getRow(); i--) {
			if (b.getPieceAtPos(new Position(i, pos.getCol())) != null) {
				foundCol2 = i;
			}
		}
		for (int i = 1; i < pos.getCol(); i++) {
			if (b.getPieceAtPos(new Position(pos.getRow(), i)) != null) {
				foundRow1 = i;
			}
		}
		for (int i = 6; i > pos.getCol(); i--) {
			if (b.getPieceAtPos(new Position(pos.getRow(), i)) != null) {
				foundRow2 = i;
			}
		}
		
		for (int i = foundCol1; i <= foundCol2; i++) {
			if (i != pos.getRow())
				ret.add(new Position(i, pos.getCol()));
		}
		for (int i = foundRow1; i <= foundRow2; i++) {
			if (i != pos.getCol())
				ret.add(new Position(pos.getRow(), i));
		}
			
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {
		
	}
	
	public String toString() {
		return super.toString() + "Rook";
	}
}
