import java.util.ArrayList;

public class King extends Piece {

	private Rook rook1;
	private Rook rook2;
	boolean ifCastledWhite = false;
	boolean ifCastledBlack = false;
	
	
	public King(Position pos, Board b, boolean isWhite, Rook rook1, Rook rook2) {
		super(pos, b, isWhite);
		this.rook1 = rook1;//white
		this.rook2 = rook2;//black

	}

	public boolean whiteLeftAbleToCastle() {
		if(b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1))==null && 
			b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 2))==null && 
			b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 3)) == null && ifCastledWhite == false) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean whiteRightAbleToCastle() {
		if(b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1))==null && 
			b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 2))==null && ifCastledWhite == false) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean blackLeftAbleToCastle() {
		if(b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 1))==null && 
				b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 2))==null && 
				b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() - 3)) == null && ifCastledBlack == false) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean blackRightAbleToCastle() {
		if(b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 1))==null && 
			b.getPieceAtPos(new Position(pos.getRow(), pos.getCol() + 2))==null && ifCastledBlack == false) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (pos.getRow() + 1 < 8 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
		if (pos.getRow() + 1 < 8 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		if (pos.getRow() - 1 >= 0 && pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		if (pos.getRow() + 1 < 8)
			ret.add(new Position(pos.getRow() + 1, pos.getCol()));
		if (pos.getRow() - 1 >= 0)
			ret.add(new Position(pos.getRow() - 1, pos.getCol()));
		if (pos.getCol() + 1 < 8)
			ret.add(new Position(pos.getRow(), pos.getCol() + 1));
		if (pos.getCol() - 1 >= 0)
			ret.add(new Position(pos.getRow(), pos.getCol() - 1));
		if (isWhite && whiteLeftAbleToCastle()) { 
			ret.add(new Position(pos.getRow(), pos.getCol() - 2));
		}
		if(isWhite && whiteRightAbleToCastle()) {
			ret.add(new Position(pos.getRow(), pos.getCol() + 2));
		}
		if(isWhite == false && blackLeftAbleToCastle()) {
			ret.add(new Position(pos.getRow(), pos.getCol() - 2));
		}
		if(isWhite == false && blackRightAbleToCastle()) {
			ret.add(new Position(pos.getRow(), pos.getCol() + 2));
		}
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void draw() {
		
	}
	
	@Override
	public void move(Position pos) {
		System.out.println("ran");
		if(whiteLeftAbleToCastle() == true && ifCastledWhite == false) {
			if(pos.equals(new Position(7,2))) {
				this.pos=pos;
				ifCastledWhite = true;
				b.getWhiteR1().move(new Position(7,3));
			} 
		} else if(whiteRightAbleToCastle() == true && ifCastledWhite == false) {
			if(pos.equals(new Position(7,6))) {
				this.pos=pos;
				ifCastledWhite = true;
				b.getWhiteR2().move(new Position(7,5));
			}
		} else {
			this.pos=pos;
			b.updateText();
			b.unhighlightMoves();
			b.setSelectedPiece(null);
			b.nextTurn();
			//after castles white king cant move 
		}
		if(blackLeftAbleToCastle() == true && ifCastledBlack == false) {
			if(pos.equals(new Position(0,2))) {
				this.pos=pos;
				ifCastledBlack = true;
				b.getBlackR1().move(new Position(0,3));
			}
		} else if(blackRightAbleToCastle() == true && ifCastledBlack == false) {
			if(pos.equals(new Position(0,6))) {
				this.pos=pos;
				ifCastledBlack = true;
				b.getBlackR2().move(new Position(0,5));
			}
		}  else {
			this.pos=pos;
			b.updateText();
			b.unhighlightMoves();
			b.setSelectedPiece(null);
			b.nextTurn();
		}
	}
	
	public String toString() {
		return "King" + super.toString();
	}
}
