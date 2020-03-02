import java.util.ArrayList;

import javax.imageio.ImageIO;

public class King extends Piece {

	private Rook rook1;
	private Rook rook2;

	boolean hasMoved = false;

	public King(Position pos, Board b, boolean isWhite, Rook rook1, Rook rook2, String fileName) {
		super(pos, b, isWhite, fileName);
		this.rook1 = rook1;
		this.rook2 = rook2;
		if (isWhite) {
			fileName  = "kingW.png";
			}
		else {
			fileName = "kingB.png";
		}
	}

	public boolean leftAbleToCastle() {
		if (isWhite) {
				return (b.getPieceAtPos(new Position(7, 1)) == null && b.getPieceAtPos(new Position(7, 2)) == null
						&& b.getPieceAtPos(new Position(7, 3)) == null && pos.getRow() == 7 && !hasMoved && !rook1.hasMoved());
		} else {
				return (b.getPieceAtPos(new Position(0, 1)) == null && b.getPieceAtPos(new Position(0, 2)) == null
						&& b.getPieceAtPos(new Position(0, 3)) == null && pos.getRow() == 0 && !hasMoved && !rook1.hasMoved());
		}
	}

	public boolean rightAbleToCastle() {
		if (isWhite) {
			return (b.getPieceAtPos(new Position(7, 5)) == null && b.getPieceAtPos(new Position(7, 6)) == null
					&& pos.getRow() == 7 && !hasMoved && !rook2.hasMoved());
		} else {
			return (b.getPieceAtPos(new Position(0, 5)) == null && b.getPieceAtPos(new Position(0, 6)) == null
					&& pos.getRow() == 0 && !hasMoved && !rook2.hasMoved());
		}
	}

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (pos.getRow() + 1 < 8 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
		if (pos.getRow() + 1 < 8 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		if (pos.getRow() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol()));
		if (pos.getRow() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol()));
		if (pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow(), pos.getCol() + 1));
		if (pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow(), pos.getCol() - 1));
		
		if (isWhite && leftAbleToCastle() && check) {		// CHECK???
			if (b.testCheck(isWhite) == false) {		// can't castle out of check
				
				simMove(new Position(7, 3));			// can't castle through check
				b.wKingPos = new Position(7, 3);
				
				if (b.testCheck(isWhite) == false) {
					ret.add(new Position(7, 2));
				}
				
				simMove(new Position(7, 4));
				b.wKingPos = new Position(7, 4);
				
			}
		}
		if (isWhite && rightAbleToCastle() && check) {		// CHECK??
			if (b.testCheck(isWhite) == false) {		// can't castle out of check
				
				simMove(new Position(7, 5));			// can't castle through check
				b.wKingPos = new Position(7, 5);
				
				if (b.testCheck(isWhite) == false) {
					ret.add(new Position(7, 6));
				}
				
				simMove(new Position(7, 4));
				b.wKingPos = new Position(7, 4);
			}
		}
		if (!isWhite && leftAbleToCastle() && check) {		// CHECK?
			if (b.testCheck(isWhite) == false) {		// can't castle out of check
				
				simMove(new Position(0, 3));			// can't castle through check
				b.bKingPos = new Position(0, 3);
				
				if (b.testCheck(isWhite) == false) {
					ret.add(new Position(0, 2));
				}
				
				simMove(new Position(0, 4));
				b.bKingPos = new Position(0, 4);
				
			}
		}
		if (!isWhite && rightAbleToCastle() && check) {		// CHECK?
			if (b.testCheck(isWhite) == false) {		// can't castle out of check
				
				simMove(new Position(0, 5));			// can't castle through check
				b.bKingPos = new Position(0, 5);
				
				if (b.testCheck(isWhite) == false) {
					ret.add(new Position(0, 6));
				}
				
				simMove(new Position(0, 4));
				b.bKingPos = new Position(0, 4);
				
			}
		}
		
		if (check) {
			ret = b.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void draw() {

	}

	@Override
	public void move(Position pos) {
		if (b.getPieceAtPos(pos) != null)
			b.getPieceAtPos(pos).remove(true);
		if (pos.equals(new Position(7, 2))) {
			this.pos = pos;
			b.wKingPos = pos;
			b.getWhiteR1().castleMove(new Position(7, 3));
		} else if (pos.equals(new Position(0, 2))) {
			this.pos = pos;
			b.bKingPos = pos;
			b.getBlackR1().castleMove(new Position(0, 3));
		} else if (pos.equals(new Position(7, 6))) {
			this.pos = pos;
			b.wKingPos = pos;
			b.getWhiteR2().castleMove(new Position(7, 5));
		} else if (pos.equals(new Position(0, 6))) {
			this.pos = pos;
			b.bKingPos = pos;
			b.getBlackR2().castleMove(new Position(0, 5));
		} else {
			this.pos = pos;

			// for check
			if (isWhite) {
				b.wKingPos = pos;
			} else {
				b.bKingPos = pos;
			}
		} 
		b.unhighlightMoves();
		b.setSelectedPiece(null);
		hasMoved = true;
		b.updatePic();
		b.nextTurn();
	}
	
	@Override
	public boolean isKing() {
		return true;
	}

	public String toString() {
		return "King" + super.toString();
	}
}