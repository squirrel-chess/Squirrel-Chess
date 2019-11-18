import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = 6564040416077628059L;
	private ArrayList<Piece> pieces;
	
	public SavedGame(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
}
