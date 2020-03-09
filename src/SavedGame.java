import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = -1951662494698218973L;
	private ArrayList<String> pieceStrings;
	private Time whiteTime, blackTime;
	private boolean whiteTurn;
	
	public SavedGame(ArrayList<String> pieceStrings, Time whiteTime, Time blackTime, boolean whiteTurn) {
		this.pieceStrings = pieceStrings;
		this.whiteTime = whiteTime;
		this.blackTime = blackTime;
		this.whiteTurn = whiteTurn;
	}
	
	public ArrayList<String> getPieceStrings() {
		return pieceStrings;
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
