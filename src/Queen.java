import java.util.ArrayList;

public class Queen extends Piece {

	private static final long serialVersionUID = -1782948667490553479L;

	public Queen(Position pos, Game game, boolean isWhite) {
		super(pos, game, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();
				
		boolean A = true;
		boolean B = true;
		boolean C = true;
		boolean D = true;
		boolean E = true;
		boolean F = true;
		boolean G = true;
		boolean H = true;
		
		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() + i) < 8) && A); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() + i)) == null) {
				ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
			} else {
				A = false;
				if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() + i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() + i, pos.getCol() + i));
				}
			}
		for (int i = 1; (((pos.getRow() + i) < 8) && ((pos.getCol() - i) >= 0)) && B; i++)	
			if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() - i)) == null) {
				ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
			} else {
				B = false;
				if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol() - i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() + i, pos.getCol() - i));
				}
			}
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() + i) < 8)) && C; i++)
			if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() + i)) == null) {
				ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
			} else {
				C = false;
				if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() + i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() - i, pos.getCol() + i));
				}
			}		
		for (int i = 1; (((pos.getRow() - i) >= 0) && ((pos.getCol() - i) >= 0)) && D; i++)
			if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() - i)) == null) {
				ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
			} else {
				D = false;
				if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol() - i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() - i, pos.getCol() - i));
				}
			}
		for (int i = 1; ((pos.getRow() + i < 8) && E); i++)
			if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol())) == null) {
				ret.add(new Position(pos.getRow() + i, pos.getCol()));
			} else {
				E = false;
				if (game.getPieceAtPos(new Position(pos.getRow() + i, pos.getCol())).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() + i, pos.getCol()));
				}
			}
		for (int i = 1; ((pos.getRow() - i >= 0) && F); i++) 
			if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol())) == null) {
				ret.add(new Position(pos.getRow() - i, pos.getCol()));
			} else {
				F = false;
				if (game.getPieceAtPos(new Position(pos.getRow() - i, pos.getCol())).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow() - i, pos.getCol()));
				}
			}
		for (int i = 1; ((pos.getCol() + i < 8) && G); i++)
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + i)) == null) {
				ret.add(new Position(pos.getRow(), pos.getCol() + i));
			} else {
				G = false;
				if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow(), pos.getCol() + i));
				}
			}
		for (int i = 1; ((pos.getCol() - i >= 0) && H); i++)
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - i)) == null) {
				ret.add(new Position(pos.getRow(), pos.getCol() - i));
			} else {
				H = false;
				if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - i)).isWhite != super.isWhite) {
					ret.add(new Position(pos.getRow(), pos.getCol() - i));
				}
			}
		
		if (check) {
			ret = game.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return super.toString() + "Queen";
	}
	
	@Override
	public int pieceType() {
		return 1;
	}
}
