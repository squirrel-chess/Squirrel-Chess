import java.util.ArrayList;

public class BoardLayout {
	
	ArrayList<Piece> pieces;
	
	boolean whiteTurn;
	
	Position wKingPos;
	Position bKingPos;

	public BoardLayout(Board b) {
		
		ArrayList<Piece> boardPieces = new ArrayList<Piece>(b.getPieces());
		pieces = new ArrayList<Piece>(boardPieces);

		whiteTurn = b.getWhiteTurn();
		
		wKingPos = b.wKingPos;
		bKingPos = b.bKingPos;
		
	}
	
}
