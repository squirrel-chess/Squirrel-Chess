import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Pawn extends Piece {

	private static final long serialVersionUID = -2651334521992229263L;
	private boolean hasMoved;
	String fileName;
	Position initialPos;
	// boolean movedTwo = false;
//	static boolean canEnPassantRight = false;
	// static boolean canEnPassantLeft = false;

	public Pawn(Position pos, Game game, boolean isWhite) {
		super(pos, game, isWhite);
		hasMoved = false;
	}

	boolean movedTwo = false;
	static boolean blankSpot = false;

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

			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)) instanceof Pawn
					&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)).canEnPassantRight == true) {
				ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
			}
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1)) instanceof Pawn
					&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1)).canEnPassantLeft == true) {
				ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
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

			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)) instanceof Pawn
					&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)).canEnPassantRight == true) {
				ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
			}
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1)) instanceof Pawn
					&& game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1)).canEnPassantLeft == true) {
				ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
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
		initialPos = game.getSelectedPiece().getPos();
		if (isWhite && pos.getRow() - 2 == 0) {
			movedTwo = true;
		}
		blankSpot = false;
		if (game.getPieceAtPos(pos) == null) {
			blankSpot = true;
		}
		super.move(pos);
		for (Piece p : game.getPieces()) {
			if (p instanceof Pawn) {
				Position position = p.getPos();
				game.getPieceAtPos(position).canEnPassantRight = false;
				game.getPieceAtPos(position).canEnPassantLeft = false;
			}
		}
		game.getPieceAtPos(pos).canEnPassantRight = false;
		game.getPieceAtPos(pos).canEnPassantLeft = false;
		hasMoved = true;
		if (isWhite) {
			if (pos.getRow() == 0)
				promoMenu();
		} else {
			if (pos.getRow() == 7)
				promoMenu();
		}
		if (Math.abs(pos.getRow() - initialPos.getRow()) == 2) {
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1)) instanceof Pawn) {
				game.getPieceAtPos(pos).canEnPassantRight = true;
			}
			if (game.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1)) instanceof Pawn) {
				game.getPieceAtPos(pos).canEnPassantLeft = true;
			}
			movedTwo = true;

		}
		if (blankSpot == true && game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) != null
				&& game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) instanceof Pawn
				&& Math.abs(initialPos.getRow() - pos.getRow()) == 1
				&& Math.abs(initialPos.getCol() - pos.getCol()) == 1) {
			Piece pieceRemoved = game.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol()));
			pieceRemoved.remove(true);
			game.updatePic();
			game.unhighlightMoves();
			game.setSelectedPiece(null);
		} else if (blankSpot == true && game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) != null
				&& game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) instanceof Pawn
				&& Math.abs(initialPos.getRow() - pos.getRow()) == 1
				&& Math.abs(initialPos.getCol() - pos.getCol()) == 1) {
			Piece pieceRemoved = game.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol()));
			pieceRemoved.remove(true);
			game.updatePic();
			game.unhighlightMoves();
			game.setSelectedPiece(null);
		}

	}

//	boolean canDoEnPassantRight() {
//		if(canEnPassantRight == true) {
//			return true;
//		} 
//		}

	@Override
	public void draw() {

	}

	public String toString() {
		return super.toString() + "Pawn";
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
		playSound("AHHHHHHHHHH.wav");
	}
	
	@Override
	public int pieceType() {
		return 5;
	}
}