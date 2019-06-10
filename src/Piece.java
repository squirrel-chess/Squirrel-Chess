import java.util.ArrayList;

public abstract class Piece {
	protected Position pos;
	protected Board b;
	protected boolean isWhite;

	public Piece(Position pos, Board b, boolean isWhite) {
		this.pos = pos;
		this.b = b;
		this.isWhite = isWhite;
	}

	public abstract void draw();

	abstract ArrayList<Position> getMoveSet();

	public void select() {
		System.out.println(b.getSelectedPiece());
		if (b.getSelectedPiece() == null)
			b.highlightMoves(getMoveSet(), this);
		else if (b.getSelectedPiece() == this) {
			b.unhighlightMoves(getMoveSet(), b.getSelectedPiece());
		}
		
	}

	public void move(Position pos) {
		this.pos = pos;
	}

	public void remove() {
		b.removePiece(this);
	}

	public Position getPos() {
		return pos;
	}

	public String toString() {
		if (isWhite)
			return ("W(" + pos.getRow() + "," + pos.getCol() + ")");
		else
			return ("B(" + pos.getRow() + "," + pos.getCol() + ")");
	}
}
