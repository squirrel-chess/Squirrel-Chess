import java.util.ArrayList;

public class Knight extends Piece {

	private static final long serialVersionUID = 3003340149398527709L;

	public Knight(Position pos, Game game, Board board, boolean isWhite) {
		super(pos, game, board, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (pos.getRow() + 2 < 8 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() + 2, pos.getCol() + 1));
		if (pos.getRow() - 2 >= 0 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() - 2, pos.getCol() + 1));
		if (pos.getRow() + 2 < 8 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() + 2, pos.getCol() - 1));
		if (pos.getRow() - 2 >= 0 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 2, pos.getCol() - 1));
		if (pos.getRow() + 1 < 8 && pos.getCol() + 2 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() + 2));
		if (pos.getRow() - 1 >= 0 && pos.getCol() + 2 < 8)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() + 2));
		if (pos.getRow() + 1 < 8 && pos.getCol() - 2 >= 0)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() - 2));
		if (pos.getRow() - 1 >= 0 && pos.getCol() - 2 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() - 2));
		
		if (check) {
			ret = game.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void draw() {
		
	}
	
	public String toString() {
		return super.toString() + "Knight";
	}
}