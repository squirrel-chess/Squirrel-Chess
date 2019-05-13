
public abstract class Piece {
	private Position pos;
	
	public Piece(Position pos) {
		this.pos = pos;
	}
	
	public void move() {
		
	}
	
	abstract Position[] getMoveSet();
}
