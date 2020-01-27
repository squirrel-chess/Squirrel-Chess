import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable {

	private static final long serialVersionUID = -8963802459683023645L;
	protected Position pos;
	protected Game game;
	protected Board board;
	protected boolean isWhite;

	public Piece(Position pos, Game game, Board board, boolean isWhite) {
		this.pos = pos;
		this.game = game;
		this.isWhite = isWhite;
		this.board = board;
	}

	public abstract void draw();

	public abstract ArrayList<Position> getMoveSet(boolean check);

	public void select() {
		System.out.println("got here");
		if (game.getWhiteTurn() == isWhite) {
			if (board.getSelectedPiece() == null)
				board.highlightMoves(this);
			else {
				board.unhighlightMoves();
			}
		}
	}

	public void move(Position pos) {
		if (game.getPieceAtPos(pos) != null) 
			game.getPieceAtPos(pos).remove();
		this.pos = pos;
		board.updatePic();
		board.unhighlightMoves();
		board.setSelectedPiece(null);
		game.nextTurn(); 
	}
	
	public Piece simMove(Position pos) {
		if (game.getPieceAtPos(pos) != null) {
			Piece removed = game.getPieceAtPos(pos);
			game.getPieceAtPos(pos).remove();
			
			this.pos = pos;
			
			return removed;
		}
		this.pos = pos;
		return null;
	}

	public void remove() {
		game.removePiece(this);
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
}