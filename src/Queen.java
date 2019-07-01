import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8)) && b.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() + i)) == null; i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0)) && b.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() - i)) == null; i++)
			ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8)) && b.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() + i)) == null; i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() - i) >= 0)) && b.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() - i)) == null; i++)
			ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
		for (int i = 0; i < pos.getCol() && b.getPieceAtPos(new Position(pos.getRow(), i)) == null; i++)
			ret.add(new Position(pos.getRow(), i));
		for (int i = pos.getCol() + 1; i < 8 && b.getPieceAtPos(new Position(pos.getRow(), i)) == null; i++)
			ret.add(new Position(pos.getRow(), i));
		for (int i = 0; i < pos.getRow() && b.getPieceAtPos(new Position(i, pos.getCol())) == null; i++)
			ret.add(new Position(i, pos.getCol()));
		for (int i = pos.getRow() + 1; i < 8 && b.getPieceAtPos(new Position(i, pos.getCol())) == null; i++)
			ret.add(new Position(i, pos.getCol()));
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "Queen" + super.toString();
	}
}
