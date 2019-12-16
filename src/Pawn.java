import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Pawn extends Piece {

	private boolean hasMoved;
	String fileName;
	Position initialPos;
	//boolean movedTwo = false;
//	static boolean canEnPassantRight = false;
	 //static boolean canEnPassantLeft = false;
	
	public Pawn(Position pos, Board b, boolean isWhite, String fileName) {
		super(pos, b, isWhite, fileName);
		this.fileName = fileName;
		hasMoved = false;
	}
	boolean movedTwo = false;
	static boolean blankSpot = false;
	@Override
	public ArrayList<Position> getMoveSet(boolean check) {
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
			
			if(b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1)) instanceof Pawn && b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1)).canEnPassantRight == true) {
				ret.add(new Position(pos.getRow()-1,pos.getCol()-1));
			}
			if(b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1)) instanceof Pawn && b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1)).canEnPassantLeft == true) {
				ret.add(new Position(pos.getRow()-1,pos.getCol()+1));
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
			}
			
			if(b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1)) instanceof Pawn && b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1)).canEnPassantRight == true) {
				ret.add(new Position(pos.getRow()+1,pos.getCol()-1));
			}
			if(b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1)) instanceof Pawn && b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1)).canEnPassantLeft== true) {
				ret.add(new Position(pos.getRow()+1,pos.getCol()+1));
			}
			
			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() + 1)) != null) && pos.getCol() != 7)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() + 1));
			if ((b.getPieceAtPos(new Position(pos.getRow() + 1, pos.getCol() - 1)) != null) && pos.getCol() != 0)
				ret.add(new Position(pos.getRow() + 1, pos.getCol() - 1));
		}
		
		if (check) {
			ret = b.moveIntoCheck(this, ret);
		}
		
		return removeInvalidMoves(ret);
	}
	
	@Override
	public void move(Position pos) {
		
		initialPos = b.getSelectedPiece().getPos();
		if(isWhite && pos.getRow()-2==0) {
			movedTwo = true;
		}
		blankSpot = false;
		if(b.getPieceAtPos(pos) == null) {
			blankSpot = true;
		}
		super.move(pos);
		for (Piece p: b.getPieces()) {
			if(p instanceof Pawn) {
				Position position = p.getPos();
				b.getPieceAtPos(position).canEnPassantRight = false;
				b.getPieceAtPos(position).canEnPassantLeft = false;
			}
		}
		b.getPieceAtPos(pos).canEnPassantRight = false;
		b.getPieceAtPos(pos).canEnPassantLeft = false;
		hasMoved = true;
		if (isWhite) {
			if (pos.getRow() == 0)
				promoMenu();
		} else {
			if (pos.getRow() == 7)
				promoMenu();
		}
		if(Math.abs(pos.getRow()-initialPos.getRow()) == 2) { 
			if (b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()+1)) instanceof Pawn) {
				b.getPieceAtPos(pos).canEnPassantRight = true;
			}
			if (b.getPieceAtPos(new Position(pos.getRow(),pos.getCol()-1)) instanceof Pawn) {
				b.getPieceAtPos(pos).canEnPassantLeft = true;
			}
			System.out.println("2");
			movedTwo = true;
			
		}
		System.out.println(blankSpot);
		if(blankSpot = true && b.getPieceAtPos(new Position(pos.getRow() -1 ,pos.getCol())) != null && b.getPieceAtPos(new Position(pos.getRow() -1 ,pos.getCol())) instanceof Pawn) {
			Piece pieceRemoved = b.getPieceAtPos(new Position(pos.getRow()-1,pos.getCol()));
			b.removePiece(pieceRemoved);		
			b.unhighlightMoves();
			b.setSelectedPiece(null);
		}
		if(blankSpot = true && b.getPieceAtPos(new Position(pos.getRow() +1 ,pos.getCol())) != null && b.getPieceAtPos(new Position(pos.getRow() +1 ,pos.getCol())) instanceof Pawn) {
			Piece pieceRemoved = b.getPieceAtPos(new Position(pos.getRow()+1,pos.getCol()));
			System.out.println(pieceRemoved);
			b.removePiece(pieceRemoved);
			b.unhighlightMoves();
			b.setSelectedPiece(null);
		}
	
	}

//	boolean canDoEnPassantRight() {
//		if(canEnPassantRight == true) {
//			return true;
//		} 
//		}
	
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
			if (isWhite) p = new Bishop(pos, b, true, "bishopW.png");
			else p = new Bishop(pos, b, false, "bishopB.png");
			b.updatePic();
			break;
		case 1:
			if (isWhite) p = new Knight(pos, b, true, "knightW.png");
			else p = new Knight(pos, b, false, "knightB.png");
			b.updatePic();
			break;
		case 2:
			if (isWhite) p = new Queen(pos, b, true, "queenW.png");
			else p = new Queen(pos, b, false, "queenB.png");
			b.updatePic();
			break;
		default:
			if (isWhite) p = new Rook(pos, b, true, "rookW.png");
			else p = new Rook(pos, b, false, "rookB.png");
			b.updatePic();
			break;
		}
		b.addPiece(p);
		b.updatePic();
	}
}