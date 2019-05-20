import java.util.ArrayList;
import java.util.stream.Stream;

public class Bishop extends Piece {

	public Bishop(Position pos, Board b) {
		super(pos, b);
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 0; ((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
		for (int i = 0; ((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
		for (int i = 0; ((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
		for (int i = 0; ((pos.getRow() + i) >= 0) && ((pos.getCol() - i) >= 0); i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
		return ret;
	}

	@Override
	public void draw() {	
	}

}
