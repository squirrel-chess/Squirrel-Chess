import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 0; i < pos.getCol() && b.getPieceAtPos(new Position(pos.getRow(), i)) == null; i++)
			ret.add(new Position(pos.getRow(), i));
		for (int i = 7; i > pos.getCol() && b.getPieceAtPos(new Position(pos.getRow(), i)) == null; i--)
			ret.add(new Position(pos.getRow(), i));
		for (int i = 0; i < pos.getRow() && b.getPieceAtPos(new Position(i, pos.getCol())) == null; i++)
			ret.add(new Position(i, pos.getCol()));
		for (int i = 7; i > pos.getRow() && b.getPieceAtPos(new Position(i, pos.getCol())) == null; i--)
			ret.add(new Position(i, pos.getCol()));
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {
		
	}
	
	public String toString() {
		return "Rook" + super.toString();
	}
}
