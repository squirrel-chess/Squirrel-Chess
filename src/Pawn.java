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
		if (!hasMoved) {
			ret.add(new Position(pos.getRow(), pos.getCol() - 1));
			ret.add(new Position(pos.getRow(), pos.getCol() - 2));
		} else
			ret.add(new Position(pos.getRow(), pos.getCol() - 1));
		return ret;
	}

	@Override
	public void draw() {

	}
}
