import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = -1951662494698218973L;
	private ArrayList<Piece> pieces;
	private Time whiteTime, blackTime;
	private boolean whiteTurn;
	
	public SavedGame(ArrayList<Piece> pieces, Time whiteTime, Time blackTime, boolean whiteTurn) {
		this.pieces = pieces;
		this.whiteTime = whiteTime;
		this.blackTime = blackTime;
		this.whiteTurn = whiteTurn;
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
}
