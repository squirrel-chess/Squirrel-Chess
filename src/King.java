import java.util.ArrayList;

public class King extends Piece {

	public King(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (pos.getRow() + 1 < 8 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
		if (pos.getRow() + 1 < 8 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		if (pos.getRow() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol()));
		if (pos.getRow() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol()));
		if (pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow(), pos.getCol() + 1));
		if (pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow(), pos.getCol() - 1));
		return ret;
	}
	
	@Override
	public void draw() {
		
	}
	
	public String toString() {
		return "King" + super.toString();
	}
}
