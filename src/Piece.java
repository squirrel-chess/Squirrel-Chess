import java.util.ArrayList;

public abstract class Piece {
	Position pos;
	
	public Piece(Position pos) {
		this.pos = pos;
	}
	
	public void move() {
		
	}
	
	abstract Position[] getMoveSet();
}
