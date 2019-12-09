import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable {

	private static final long serialVersionUID = -8963802459683023645L;
	protected Position pos;
	protected Board b;
	protected boolean isWhite;

	public Piece(Position pos, Board b, boolean isWhite) {
		this.pos = pos;
		this.b = b;
		this.isWhite = isWhite;
		
	}

	public abstract void draw();

	public abstract ArrayList<Position> getMoveSet();

	public void select() {
		if (b.getWhiteTurn() == isWhite) {
			if (b.getSelectedPiece() == null)
				b.highlightMoves(this);
			else {
				b.unhighlightMoves();
			}
		}
	}

	public void move(Position pos) {
		if (b.getPieceAtPos(pos) != null) 
			b.getPieceAtPos(pos).remove();
		this.pos = pos;
		b.updatePic();
		b.unhighlightMoves();
		b.setSelectedPiece(null);
		b.nextTurn(); 
	}
	
	public Piece simMove(Position pos) {
		if (b.getPieceAtPos(pos) != null) {
			Piece removed = b.getPieceAtPos(pos);
			b.getPieceAtPos(pos).remove();
			
			this.pos = pos;
			
			return removed;
		}
		this.pos = pos;
		return null;
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

	protected ArrayList<Position> removeInvalidMoves(ArrayList<Position> moveSet) {
		ArrayList<Position> posList = b.getAllFriendlyPiecePos(isWhite);
		for (int i = 0; i < posList.size(); i++) {
			for (int j = 0; j < moveSet.size(); j++)
				if (posList.get(i).equals(moveSet.get(j))) {
					moveSet.remove(j);
				}
		}
		return moveSet;
	}
	
	public boolean isKing() {
		return false;
	}
	
	public boolean isWhite() {
		return isWhite;
	}
}