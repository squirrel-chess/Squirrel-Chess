import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = 6564040416077628059L;
	private ArrayList<Piece> pieces;
	private Time whiteTime, blackTime;
	private boolean whiteTurn;
	private Position wKingPos, bKingPos;
	
	public SavedGame(ArrayList<Piece> pieces, Time whiteTime, Time blackTime, boolean whiteTurn, Position wKingPos, Position bKingPos) {
		this.pieces = pieces;
		this.whiteTime = whiteTime;
		this.blackTime = blackTime;
		this.whiteTurn = whiteTurn;
		this.wKingPos = wKingPos;
		this.bKingPos = bKingPos;
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public Time getWhiteTime() {
		return whiteTime;
	}
	
	public Time getBlackTime() {
		return blackTime;
	}
	
	public boolean getWhiteTurn() {
		return whiteTurn;
	}
	
	public Position getWKingPos() {
		return wKingPos;
	}
	
	public Position getBKingPos() {
		return bKingPos;
	}
}
