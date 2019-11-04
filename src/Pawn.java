import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Pawn extends Piece {

	private boolean hasMoved;

	public Pawn(Position pos, Board b, boolean isWhite) {
		super(pos, b, isWhite);
		hasMoved = false;
	}

	boolean movedOne = false;
	boolean movedTwo = false;
	boolean wPMoved = false;
	boolean bPMoved = false;
	boolean movedInFront = false;
	boolean ifWMovedAcross = false;
	boolean ifBMovedAcross = false;
	boolean immediatelyAfterwards = true;
	Piece lastPiece;
	int lastBCol;
	int lastWCol;
	int lastWRow;
	int lastBRow;

	@Override

	public ArrayList<Position> getMoveSet() {
		ArrayList<Position> ret = new ArrayList<Position>();
		if (isWhite) {// white
			if (!hasMoved) { // hasn't moved
				if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
					ret.add(new Position(pos.getRow() - 2, pos.getCol()));
				}
				if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() - 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
			} else { // has moved
				if (b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() - 1, pos.getCol()));
				}
				if(ifBlackMovedTwoInFrontRight() && ((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1))).movedOne==false) {
					ifWMovedAcross = true;
					ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
					
				}
				if(ifBlackMovedTwoInFrontLeft() && ((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1))).movedOne==false) {
					ifWMovedAcross = true;
					ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
				}
			}

			if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() + 1));
			if ((b.getPieceAtPos(new Position(pos.getRow() - 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() - 1, pos.getCol() - 1));
		} else {// black
			if (!hasMoved) { // hasn't moved
				if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) == null)) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
					ret.add(new Position(pos.getRow() + 2, pos.getCol()));
				}
				if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null)
						&& (b.getPieceAtPos(new Position(pos.getRow() + 2, pos.getCol())) != null))
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
			} else { // has moved
				if (b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol())) == null) {
					ret.add(new Position(pos.getRow() + 1, pos.getCol()));
				}
				System.out.println(((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()))).immediatelyAfterwards);
				if(ifWhiteMovedTwoInFrontRight() && ((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1))).movedOne==false && ((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()))).immediatelyAfterwards==true) {
					ifBMovedAcross = true;
					ret.add(new Position(pos.getRow()+1,pos.getCol()+1));
				} 
//				
				if(ifWhiteMovedTwoInFrontLeft() && ((Pawn)b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1))).movedOne==false) {
					ifBMovedAcross = true;
					ret.add(new Position(pos.getRow()+1,pos.getCol()-1));
				} 
			}

			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		}
		return removeInvalidMoves(ret);
	}
	
	
//	boolean movedOne() {
//		if(isWhite) {	
//			if(pos.getRow()==5) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			if(pos.getRow()==2) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//	}
	@Override
	public void move(Position pos) {
		

		if(isWhite) {	
			if(pos.getRow()==5) {
				movedOne = true;
			}
		} else {
			if(pos.getRow()==2) {
				movedOne = true;
			}
		}
		Position p = new Position(pos.getRow(),pos.getCol());
		
		super.move(pos);
		
		if(ifWhiteMovedTwoInFrontRight()) {
			if(b.getPieceAtPos(new Position(pos.getRow()-1,pos.getCol())) != null) {
				System.out.println("");
			} else {
				((Pawn)b.getPieceAtPos(p)).immediatelyAfterwards=false;
			}
		}
		
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
			lastWCol = pos.getCol();
			lastWRow = pos.getRow();

		} else {
			lastBCol = pos.getCol();
			lastBRow = pos.getRow();
		}

		//System.out.println(movedInFront + this.toString());
		if(ifBMovedAcross) {
//			Position p = new Position(pos.getRow(),pos.getCol());
			if(b.getPieceAtPos(new Position(pos.getRow()-1,pos.getCol())) != null) {
				System.out.println("moved");
				Piece pieceRemoved = b.getPieceAtPos(new Position(pos.getRow()-1,pos.getCol()));
				System.out.println(pieceRemoved);
				b.removePiece(pieceRemoved);
				b.updateText();
				b.unhighlightMoves();
				b.setSelectedPiece(null);
			//	b.nextTurn();
			} 
//			else {
//				((Pawn)b.getPieceAtPos(p)).immediatelyAfterwards=false;
//			}
//				System.out.println(immediatelyAfterwards);
	}
		if(ifWMovedAcross) {
			if(b.getPieceAtPos(new Position(pos.getRow()+1,pos.getCol())) != null) {
				System.out.println("moved");
				Piece pieceRemoved = b.getPieceAtPos(new Position(pos.getRow()+1,pos.getCol()));
				System.out.println(pieceRemoved);
				b.removePiece(pieceRemoved);
				b.updateText();
				b.unhighlightMoves();
				b.setSelectedPiece(null);
				//b.nextTurn();
			}
		}
		
		System.out.println(this);
		
	}

	boolean ifWhiteMovedTwoInFrontRight() {
		if (pos.getRow() == 4 && b.getPieceAtPos(new Position(4, pos.getCol() + 1)) instanceof Pawn) {
			return true;
		} else {

			return false;
		}
	}

	boolean ifWhiteMovedTwoInFrontLeft() {
				
		if (pos.getRow() == 4 && b.getPieceAtPos(new Position(4, pos.getCol() - 1)) instanceof Pawn) {
//			movedInFront = true;
			return true;
		} else {

			return false;
		}
	}
	boolean ifBlackMovedTwoInFrontRight() {
		if(pos.getRow() ==3 && b.getPieceAtPos(new Position(3, pos.getCol() + 1)) instanceof Pawn) {
			return true;
		} else {
			return false;
		}
	}
	boolean ifBlackMovedTwoInFrontLeft() {
		if(pos.getRow() == 3 && b.getPieceAtPos(new Position(3, pos.getCol() - 1)) instanceof Pawn) {
			return true;
		} else {
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