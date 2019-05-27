import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton {
	private boolean isClicked;
	private Board b;
	private Position pos;
	
	public Square(boolean isClicked, Board b, Position pos) {
		this.isClicked = isClicked;
		this.b = b;
		this.pos = pos;
	}
	
	public void click() {
		if (isClicked)
			b.unHighlightMoves(getPieceAtPos(new Position(a, b)));
	}

	public void setBackground(Color lightGray) {
		// TODO Auto-generated method stub
		
	}
}
