import java.util.ArrayList;

public class Pawn extends Piece {

	private boolean hasMoved;

	public Pawn(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
		hasMoved = false;
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (isWhite) {
			if (!hasMoved) {
				ret.add(new Position(pos.getRow() - 1, pos.getCol()));
				ret.add(new Position(pos.getRow() - 2, pos.getCol()));
			} else
				ret.add(new Position(pos.getRow() - 1, pos.getCol()));
		} else {
			if (!hasMoved) {
				ret.add(new Position(pos.getRow() + 1, pos.getCol()));
				ret.add(new Position(pos.getRow() + 2, pos.getCol()));
			} else
				ret.add(new Position(pos.getRow() + 1, pos.getCol()));
		}
		return ret;
	}

	@Override
	public void draw() {

	}

	public String toString() {
		return "Pawn" + super.toString();
	}
}
