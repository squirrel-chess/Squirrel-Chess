import java.util.ArrayList;

public class BoardLayout {
	
	ArrayList<Piece> pieces;
	
	boolean whiteTurn;
	
	Position wKingPos;
	Position bKingPos;

	public BoardLayout(Board b) {
		
		pieces = new ArrayList<Piece>(b.getPieces());
		
		whiteTurn = b.getWhiteTurn();
		
		wKingPos = b.wKingPos;
		bKingPos = b.bKingPos;
		
	}
	
}
