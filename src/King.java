import java.util.ArrayList;

public class King extends Piece {

	private static final long serialVersionUID = -633202941094591935L;
	private Rook rook1;
	private Rook rook2;
	boolean hasMoved = false;
	public King(Position pos, Game game, Board board, boolean isWhite, Rook rook1, Rook rook2) {
		super(pos, game, board, isWhite);
		this.rook1 = rook1;// white
		this.rook2 = rook2;// black
	}


	public boolean whiteLeftAbleToCastle() {
		if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)) == null
				&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 2)) == null
				&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 3)) == null && pos.getRow() == 7 && hasMoved) {
			return true;
		} else {
			return false;
		}
	}

	public boolean leftAbleToCastle() {
		if (isWhite) {
			return (game.getPieceAtPos(new Position(7, 1)) == null && game.getPieceAtPos(new Position(7, 2)) == null
					&& game.getPieceAtPos(new Position(7, 3)) == null && pos.getRow() == 7 && !hasMoved && !rook1.hasMoved());
		} else {
			return (game.getPieceAtPos(new Position(0, 1)) == null && game.getPieceAtPos(new Position(0, 2)) == null
					&& game.getPieceAtPos(new Position(0, 3)) == null && pos.getRow() == 0 && !hasMoved && !rook1.hasMoved());
		}
	}

	public boolean rightAbleToCastle() {
		if (isWhite) {
			return (game.getPieceAtPos(new Position(7, 5)) == null && game.getPieceAtPos(new Position(7, 6)) == null
					&& pos.getRow() == 7 && !hasMoved && !rook2.hasMoved());
		} else {
			return (game.getPieceAtPos(new Position(0, 5)) == null && game.getPieceAtPos(new Position(0, 6)) == null
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
		if (game.getPieceAtPos(pos) != null)
			game.getPieceAtPos(pos).remove();
		if (pos.equals(new Position(7, 2))) {
			this.pos = pos;
			game.wKingPos = pos;
			game.getWhiteR1().castleMove(new Position(7, 3));
		} else if (pos.equals(new Position(0, 2))) {
			this.pos = pos;
			game.bKingPos = pos;
			game.getBlackR1().castleMove(new Position(0, 3));
		} else if (pos.equals(new Position(7, 6))) {
			this.pos = pos;
			game.wKingPos = pos;
			game.getWhiteR2().castleMove(new Position(7, 5));
		} else if (pos.equals(new Position(0, 6))) {
			this.pos = pos;
			game.bKingPos = pos;
			game.getBlackR2().castleMove(new Position(0, 5));
		} else {
			this.pos = pos;

			// for check
			if (isWhite) {
				game.wKingPos = pos;
			} else {
				game.bKingPos = pos;
			}
		}
		board.unhighlightMoves();
		board.setSelectedPiece(null);
		hasMoved = true;
		board.updateGraphics();
		game.nextTurn();
	}
	
	@Override
	public boolean isKing() {
		return true;
	}

	public String toString() {
		return "King" + super.toString();
	}
}