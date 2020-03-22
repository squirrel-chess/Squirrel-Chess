import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Pawn extends Piece {

	private static final long serialVersionUID = -2651334521992229263L;
	private boolean hasMoved;
	String fileName;

	public Pawn(Position pos, Game game, boolean isWhite) {
		super(pos, game, isWhite);
		hasMoved = false;
	}

	boolean movedTwo = false;

	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (isWhite) {// white
			if (!hasMoved) { // hasn't moved
				if ((game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (game.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
					ret.add(new Position(pos.getRow() - 2, pos.getCol()));
				}
				if ((game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (game.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
			} else { // has moved
				if (game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
				}
			}

			if ((game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
			if ((game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		} else {// black
			if (!hasMoved) { // hasn't moved
				if ((game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (game.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
					ret.add(new Position(pos.getRow() + 2, pos.getCol()));
				}
				if ((game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (game.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
			} else { // has moved
				if (game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
				}
			}

			if ((game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
			if ((game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		}

		if (check) {
			ret = game.moveIntoCheck(this, ret);
		}

		return removeInvalidMoves(ret);
	}

	@Override
	public void move(Position pos) {
		if (isWhite && pos.getRow() - 2 == 0) {
			movedTwo = true;
		}
		super.move(pos);
		hasMoved = true;
		if (isWhite) {
			if (pos.getRow() == 0)
				promoMenu();
		} else {
			if (pos.getRow() == 7)
				promoMenu();
		}
	}

	@Override
	public void draw() {

	}

	public String toString() {
		return super.toString() + "Pawn";
	}

	private void promoMenu() {
		game.removePiece(this);
		String[] options = { "Bishop", "Knight", "Queen", "Rook" };
		int option = JOptionPane.showOptionDialog(null, "Choose the piece to promote to", "Pawn Promotion", 0, 0, null,
				options, null);
		Piece p;
		switch (option) {
		case 0:
			if (isWhite)
				p = new Bishop(pos, game, true);
			else
				p = new Bishop(pos, game, false);
			game.updateGraphics();
			break;
		case 1:
			if (isWhite)
				p = new Knight(pos, game, true);
			else
				p = new Knight(pos, game, false);
			game.updateGraphics();
			break;
		case 2:
			if (isWhite)
				p = new Queen(pos, game, true);
			else
				p = new Queen(pos, game, false);
			game.updateGraphics();
			break;
		default:
			if (isWhite)
				p = new Rook(pos, game, true);
			else
				p = new Rook(pos, game, false);
			game.updateGraphics();
			break;
		}
		game.addPiece(p);
		game.updateGraphics();
	}
}