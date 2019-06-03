import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton {
	private boolean isClicked;
	private boolean inMoveSet;
	private Board b;
	private Position pos;

	public Square(Board b, Position pos) {
		this.isClicked = false;
		this.inMoveSet = false;
		this.b = b;
		this.pos = pos;
	}

	public void click() {
		if (inMoveSet)
			return;
		else if (isClicked)
			b.unhighlightMoves(b.getPieceAtPos(pos));
		else
			b.highlightMoves(b.getPieceAtPos(pos));
		isClicked = !isClicked;
	}
	
	public void setInMoveSet(boolean inMoveSet) {
		this.inMoveSet = inMoveSet;
	}
}
