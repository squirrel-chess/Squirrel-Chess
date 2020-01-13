import java.io.Serializable;

public class SavedGame implements Serializable {
	
	private static final long serialVersionUID = 6564040416077628059L;
	private Game game;
	
	public SavedGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
}