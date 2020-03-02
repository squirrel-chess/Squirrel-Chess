import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public abstract class Piece {
	boolean canEnPassantRight = false;
	boolean canEnPassantLeft = false;
	protected Position pos;
	protected Board b;
	protected boolean isWhite;
	protected BufferedImage image;

	public Piece(Position pos, Board b, boolean isWhite, String fileName) {
		this.pos = pos;
		this.b = b;
		this.isWhite = isWhite;
		try {
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
		if (b.getPieceAtPos(pos) != null) {
			b.getPieceAtPos(pos).remove(true);
			playSound("chomp.wav");
		}
		else {
			playSound("whack.wav");
		}
		this.pos = pos;
		b.updatePic();
		b.unhighlightMoves();
		b.setSelectedPiece(null);
		b.nextTurn(); 
	}
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          this.getClass().getResourceAsStream(url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	public Piece simMove(Position pos) {
		if (b.getPieceAtPos(pos) != null) {
			Piece removed = b.getPieceAtPos(pos);
			b.getPieceAtPos(pos).remove(false);
			
			this.pos = pos;
			
			return removed;
		}
		this.pos = pos;
		return null;
	}

	// REMOVE
	public void remove(boolean taken) {
		b.removePiece(this);
		
		if (taken) {
			b.getGame().getGamePanel().addPiece(this);
		}
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
	public void setImage(Image image) {
		this.image = (BufferedImage) image;
	}
	
	public boolean isKing() {
		return false;
	}
	
	// PIECE TYPE - use isKing for king
	public int pieceType() {
		return -1;
	}
	
}