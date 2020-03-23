import java.util.ArrayList;

public class Bishop extends Piece {

	private static final long serialVersionUID = 7642991190474293351L;

	public Bishop(Position pos, Board game, boolean isWhite) {
		super(pos, game, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();

		boolean A = true;
		boolean B = true;
		boolean C = true;
		boolean D = true;

		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8) && A); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() + i)) == null) {
				ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
			} else {
				A = false;
				if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() + i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
				}
			}
		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0) && B); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() - i)) == null) {
				ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
			} else {
				B = false;
				if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() - i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
				}
			}
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8) && C); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() + i)) == null) {
				ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
			} else {
				C = false;
				if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() + i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
				}
			}
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() - i) >= 0) && D); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() - i)) == null) {
				ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
			} else {
				D = false;
				if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() - i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
				}
			}

		if (check) {
			ret = game.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {
	}

	public String toString() {
		return "Bishop" + super.toString();
	}
	
	@Override
	public int pieceType() {
		return 3;
	}
}