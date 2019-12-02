import java.util.ArrayList;

import javax.swing.JOptionPane;

public abstract class Piece {
	
	protected Position pos;
	protected Board b;
	protected boolean isWhite;
	boolean immediatelyAfterwards = true;
	
	protected static Piece recentPieceW;
	protected static Piece recentPieceB;

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
		b.updateText();
		b.unhighlightMoves();
		b.setSelectedPiece(null);
		b.nextTurn();
	//	if (isWhite)
			//JOptionPane.showMessageDialog(null, "Black's turn.");
	//	else
			//JOptionPane.showMessageDialog(null, "White's turn.");
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
//	public Piece getLastPiece() {
//		return piece;
//	}
}