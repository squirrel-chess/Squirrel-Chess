import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Piece {
	protected Position pos;
	protected Board b;
	protected boolean isWhite;
	protected BufferedImage image;

	public Piece(Position pos, Board b, boolean isWhite, String fileName) {
		this.pos = pos;
		this.b = b;
		this.isWhite = isWhite;
		try {
//			BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream(fileName));
//			image = new BufferedImage((int) b.getGame().getFrameDimension().getHeight() / 8, (int) b.getGame().getFrameDimension().getHeight() / 8, BufferedImage.TYPE_INT_RGB);
			image = ImageIO.read(this.getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			
		}
		
	}

	public abstract void draw();

	public abstract ArrayList<Position> getMoveSet(boolean check);

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
	public Image getImage() {
		return image;
	}
	
	public boolean isKing() {
		return false;
	}
}