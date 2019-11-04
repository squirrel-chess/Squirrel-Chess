import java.util.ArrayList;

public class King extends Piece {

	private Rook rook1;
	private Rook rook2;
	boolean hasMoved = false;

	public King(Position pos, Board b, boolean isWhite, Rook rook1, Rook rook2) {
		super(pos, b, isWhite);
		this.rook1 = rook1;// white
		this.rook2 = rook2;// black

	}

	public boolean leftAbleToCastle() {
		System.out.println("King moved: " + hasMoved);
		if (isWhite) {
			return (b.getPieceAtPos(new Position(7, 1)) == null && b.getPieceAtPos(new Position(7, 2)) == null
					&& b.getPieceAtPos(new Position(7, 3)) == null && pos.getRow() == 7 && !hasMoved && !rook1.hasMoved());
		} else {
			return (b.getPieceAtPos(new Position(0, 1)) == null && b.getPieceAtPos(new Position(0, 2)) == null
					&& b.getPieceAtPos(new Position(0, 3)) == null && pos.getRow() == 0 && !hasMoved && !rook1.hasMoved());
		}
	}

	public boolean rightAbleToCastle() {
		System.out.println("King moved: " + hasMoved);
		if (isWhite) {
			return (b.getPieceAtPos(new Position(7, 5)) == null && b.getPieceAtPos(new Position(7, 6)) == null
					&& pos.getRow() == 7 && !hasMoved && !rook2.hasMoved());
		} else {
			return (b.getPieceAtPos(new Position(0, 5)) == null && b.getPieceAtPos(new Position(0, 6)) == null
					&& pos.getRow() == 0 && !hasMoved && !rook2.hasMoved());
		}
	}

	@Override
	public ArrayList<Position> getMoveSet() {
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
		if (isWhite && leftAbleToCastle()) {
			ret.add(new Position(7, 2));
		}
		if (isWhite && rightAbleToCastle()) {
			ret.add(new Position(7, 6));
		}
		if (!isWhite && leftAbleToCastle()) {
			ret.add(new Position(0, 2));
		}
		if (!isWhite && rightAbleToCastle()) {
			ret.add(new Position(0, 6));
		}
		return removeInvalidMoves(ret);
	}

	@Override
	public void draw() {

	}

	@Override
	public void move(Position pos) {
		if (b.getPieceAtPos(pos) != null)
			b.getPieceAtPos(pos).remove();
		if (leftAbleToCastle()) {
			if (isWhite) {
				if (pos.equals(new Position(7, 2))) {
					this.pos = pos;
					b.wKingPos = pos;
					b.getWhiteR1().move(new Position(7, 3));
				}
			} else {
				if (pos.equals(new Position(0, 2))) {
					this.pos = pos;
					b.bKingPos = pos;
					b.getBlackR1().move(new Position(0, 3));
				}
			}
		} else if (rightAbleToCastle()) {
			if (isWhite) {
				if (pos.equals(new Position(7, 6))) {
					this.pos = pos;
					b.wKingPos = pos;
					b.getWhiteR1().move(new Position(7, 5));
				}
			} else {
				if (pos.equals(new Position(0, 6))) {
					this.pos = pos;
					b.bKingPos = pos;
					b.getBlackR1().move(new Position(0, 5));
				}
			}
		} else {
			this.pos = pos;

			// for check
			if (isWhite) {
				b.wKingPos = pos;
			} else {
				b.bKingPos = pos;
			}

			b.updateText();
			b.unhighlightMoves();
			b.setSelectedPiece(null);
		}
		hasMoved = true;
		b.nextTurn();
		b.updateText();
	}

	public String toString() {
		return "King" + super.toString();
	}
}