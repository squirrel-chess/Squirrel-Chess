import java.util.ArrayList;
import java.util.stream.Stream;

public class Bishop extends Piece {

	public Bishop(Position pos) {
		super(pos);
	}

	@Override
	public Position[] getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = pos.getRow(); i < 8; i++)
			ret.add(new Position(i, pos.getCol() + i));
		for (int i = pos.getRow(); i < 8; i++)
			ret.add(new Position(i, pos.getCol() - i));
		for (int i = pos.getRow(); i >= 0; i--)
			ret.add(new Position(i, pos.getCol() - i));
		for (int i = pos.getRow(); i >= 0; i--)
			ret.add(new Position(i, pos.getCol() - i));
		Stream<Position> s = ret.stream().filter((p) -> {
			return (p.getCol() >= 8 || p.getRow() >= 8);
		});
		return s.toArray();
	}

}
