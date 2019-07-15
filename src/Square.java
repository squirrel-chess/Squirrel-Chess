import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton {
	private boolean inMoveSet;
	private Board b;
	private Position pos;

	public Square(Board b, Position pos) {
		this.inMoveSet = false;
		this.b = b;
		this.pos = pos;
	}

	public void click() {
		if (b.getSelectedPiece() == null && b.getPieceAtPos(pos) != null)
			b.getPieceAtPos(pos).select();
		else if (inMoveSet && b.getSelectedPiece() != null)
			b.getSelectedPiece().move(pos);
		else {
			b.setSelectedPiece(null);
			b.unhighlightMoves();
		}
	}

	public void setInMoveSet(boolean inMoveSet) {
		this.inMoveSet = inMoveSet;
	}
}
