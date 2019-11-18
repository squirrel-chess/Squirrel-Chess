import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
	
	private ArrayList<Piece> pieces;
	
	public SavedGame(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
}
