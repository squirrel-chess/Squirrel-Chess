import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 0; ((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
		for (int i = 0; ((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
		for (int i = 0; ((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
		for (int i = 0; ((pos.getRow() + i) >= 0) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
		for (int i = 0; i < 8; i++)
			ret.add(new Position(pos.getRow(), i));
		for (int i = 0; i < 8; i++)
			ret.add(new Position(i, pos.getCol()));
		return ret;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Queen (" + pos.getRow() + ", " + pos.getCol() + ")";
	}
}
