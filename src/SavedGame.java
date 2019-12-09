import java.io.Serializable;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = 6564040416077628059L;
	private Board board;
	
	public SavedGame(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
}
