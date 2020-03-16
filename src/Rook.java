import java.util.ArrayList;

public class Rook extends Piece {
	
	private boolean hasMoved;

	private static final long serialVersionUID = 5038678748630012418L;

	public Rook(Position pos, Game game, boolean isWhite) {
		super(pos, game, isWhite);
	}

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();
		int foundCol1 = 0;
		int foundCol2 = 7;
		int foundRow1 = 0;
		int foundRow2 = 7;
		
		for (int i = 1; i < pos.getRow(); i++) {
			if (game.getPieceAtPos(new Position(i, pos.getCol())) != null) {
				foundCol1 = i;
			}
		}
		for (int i = 6; i > pos.getRow(); i--) {
			if (game.getPieceAtPos(new Position(i, pos.getCol())) != null) {
				foundCol2 = i;
			}
		}
		for (int i = 1; i < pos.getCol(); i++) {
			if (game.getPieceAtPos(new Position(pos.getRow(), i)) != null) {
				foundRow1 = i;
			}
		}
		for (int i = 6; i > pos.getCol(); i--) {
			if (game.getPieceAtPos(new Position(pos.getRow(), i)) != null) {
				foundRow2 = i;
			}
		}
		
		for (int i = foundCol1; i <= foundCol2; i++) {
			if (i != pos.getRow())
				ret.add(new Position(i, pos.getCol()));
		}
		for (int i = foundRow1; i <= foundRow2; i++) {
			if (i != pos.getCol())
				ret.add(new Position(pos.getRow(), i));
		}
			
		if (check) {
			ret = game.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {
		
	}
	
	@Override
	public void move(Position pos) {
		super.move(pos);
		hasMoved = true;
	}
	
	public void castleMove(Position pos) {
		this.pos = pos;
		hasMoved = true;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public String toString() {
		return super.toString() + "Rook";
	}
}
