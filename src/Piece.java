import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public abstract class Piece implements Serializable {

	private static final long serialVersionUID = -8963802459683023645L;
	boolean canEnPassantRight = false;
	boolean canEnPassantLeft = false;
	protected Position pos;
	protected Board game;
	protected boolean isWhite;

	public Piece(Position pos, Board game, boolean isWhite) {
		this.pos = pos;
		this.game = game;
		this.isWhite = isWhite;
	}

	public abstract void draw();

	public abstract ArrayList<Position> getMoveSet(boolean check);

	public void select() {
		System.out.println(game.getWhiteTurn());
		if (game.getWhiteTurn() == isWhite) {
			if (game.getSelectedPiece() == null) {
				game.highlightMoves(this);
			game.setSelectedPiece(this);
			} else {
				game.unhighlightMoves();
			}
		}
	}

	public void move(Position pos) {
		if (game.getPieceAtPos(pos) != null) {
			game.getPieceAtPos(pos).remove(true);
			playSound("chomp.wav");
		}
		else {
			playSound("whack.wav");
		}
		this.pos = pos;
		game.updateGraphics();
		game.unhighlightMoves();
		game.setSelectedPiece(null);
		game.nextTurn(); 
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
		if (game.getPieceAtPos(pos) != null) {
			Piece removed = game.getPieceAtPos(pos);
			game.getPieceAtPos(pos).remove(false);
			
			this.pos = pos;
			
			return removed;
		}
		this.pos = pos;
		return null;
	}

	// REMOVE
	public void remove(boolean taken) {
		game.removePiece(this);
		
		if (taken) {
			game.getMain().getGamePanel().addPiece(this);
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
		ArrayList<Position> posList = game.getAllFriendlyPiecePos(isWhite);
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
	
	// PIECE TYPE - use isKing for king
	public int pieceType() {
		return -1;
	}
}