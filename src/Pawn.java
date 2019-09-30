import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Pawn extends Piece {

	private boolean hasMoved;

	public Pawn(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
		hasMoved = false;
	}
	boolean movedTwo = false;
	Piece lastPiece;
	int lastBCol;
	int lastWCol;
	@Override
	
	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (isWhite) {//white
			if (!hasMoved) { //hasn't moved
				if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
					ret.add(new Position(pos.getRow() - 2, pos.getCol()));
				}
				if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
			} else { //has moved
				if (b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
				}
			}
			
			if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
			if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		} else {//black
			if (!hasMoved) { //hasn't moved
				if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
					ret.add(new Position(pos.getRow() + 2, pos.getCol()));
				}
				if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
			} else { //has moved
				if (b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
				}
//				if(ifWhiteMovedTwoInFrontRight()) {
//					ret.add(new Position(pos.getRow()+1,pos.getCol()-1));
//				} 
//				
//				if(ifWhiteMovedTwoInFrontLeft()) {
//					ret.add(new Position(pos.getRow()+1,pos.getCol()+1));
//				} 
			}
			
			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		}
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void move(Position pos) {
		if(isWhite && pos.getRow()-2==0) {
			movedTwo = true;
		}
		super.move(pos);
		hasMoved = true;
		if (isWhite) {
			if (pos.getRow() == 0)
				promoMenu();
		} else {
			if (pos.getRow() == 7)
				promoMenu();
		}
		lastPiece = b.getPieceAtPos(pos);
		if (isWhite) {
			System.out.println("di");
			lastWCol = lastPiece.getPos().getCol();
			System.out.println(lastWCol);
		} else {
			 lastBCol = lastPiece.getPos().getCol();
			 System.out.println(lastBCol);
		}
		if(ifWhiteMovedTwoInFrontLeft() == true) {
			System.out.println("movedleft");
			boolean moved2WLeft = true;
		}
		
//		 System.out.println(lastCol);
	}
	boolean ifWhiteMovedTwoInFrontRight() {
		if(pos.getRow() == 4 && (lastWCol-lastBCol) == 1) {
			return true;
		} else {
		return false;
		}
	}
	boolean ifWhiteMovedTwoInFrontLeft() {
		System.out.println("WCol" + lastWCol);
		System.out.println("lastBCol: " + lastBCol);
		System.out.println("pos get col:" + pos.getCol());
		if(pos.getRow() == 4 && (lastWCol-lastBCol) == -1) {
			System.out.println("d");
			
			return true;
			
		} else {
		//	System.out.println("d");
		
		//	System.out.println(lastWCol - lastBCol);
		return false;
		}
	}

	@Override
	public void draw() {

	}

	public String toString() {
		return super.toString() + "Pawn";
	}

	private void promoMenu() {
		b.removePiece(this);
		String[] options = { "Bishop", "Knight", "Queen", "Rook" };
		int option = JOptionPane.showOptionDialog(null, "Choose the piece to promote to", "Pawn Promotion", 0, 0, null,
				options, null);
		Piece p;
		switch (option) {
		case 0:
			p = new Bishop(pos, b, isWhite);
			break;
		case 1:
			p = new Knight(pos, b, isWhite);
			break;
		case 2:
			p = new Queen(pos, b, isWhite);
			break;
		default:
			p = new Rook(pos, b, isWhite);
			break;
		}
		b.addPiece(p);
		b.updateText();
	}
}