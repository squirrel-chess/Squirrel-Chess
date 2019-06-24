import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 1; ((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
		for (int i = 1; ((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
		for (int i = 1; ((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
		for (int i = 1; ((pos.getRow() - i) >= 0) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {	
	}
	
	public String toString() {
		return "Bishop" + super.toString();
	}
}
