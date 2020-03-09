import java.util.ArrayList;

public class BoardLayout {
	
	public ArrayList<Piece> pieces;
	public boolean whiteTurn;
	
	public Position wKingPos;
	public Position bKingPos;
	
	public BoardLayout(Board b) {
		
		this.pieces = new ArrayList<Piece>(b.getPieces());
		this.whiteTurn = b.getWhiteTurn();
		this.wKingPos = b.wKingPos;
		this.bKingPos = b.bKingPos;
		
	}
	
}
