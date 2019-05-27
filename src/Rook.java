import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 0; i < 8; i++)
			ret.add(new Position(pos.getRow(), i));
		for (int i = 0; i < 8; i++)
			ret.add(new Position(i, pos.getCol()));
		return ret;
	}

	@Override
	public void draw() {
		
	}
	
	public String toString() {
		return "Rook (" + pos.getRow() + ", " + pos.getCol() + ")";
	}
}
