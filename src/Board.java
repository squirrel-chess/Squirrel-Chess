import java.awt.Color;

// ADD CHECK BRANCH

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Board extends JPanel {
	private Chess game;
	private Square[][] squares;
	private ArrayList<Piece> pieces;
	private Piece selectedPiece;
	private Timer timer;
	private boolean whiteCastle;
	private boolean blackCastle;
	private boolean whiteTurn;
	private Rook rook1;
	private Rook rook2;
	private King king;
	
	public Position wKingPos;
	public Position bKingPos;


	public Board(Chess game) {
		this.game = game;
		pieces = new ArrayList<Piece>();
		timer = new Timer(10, (e) -> {
		});
		setPreferredSize(new Dimension(1000, 1000));
		game.setText("White's turn.");
		initBoard();
		initPieces();
		timer.start();
	}

	public King getKing() {
		return king;
	}
	
	public void highlightMoves(Piece p) {
		squares[p.getPos().getRow()][p.getPos().getCol()].setBackground(Color.GREEN);
		for (Position pos : p.getMoveSet()) {
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));
			squares[pos.getRow()][pos.getCol()].setInMoveSet(true);
		}
		selectedPiece = p;
	}

	public void unhighlightMoves() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 1) {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				} else
					squares[i][j].setBackground(Color.WHITE);
				squares[i][j].setInMoveSet(false);
			}
		}
		selectedPiece = null;
	}

	public void removePiece(Piece p) {
		pieces.remove(p);
	}

	private void initBoard() {
		squares = new Square[8][8];
		selectedPiece = null;
		setSize(1000, 1000);
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(this, new Position(i, j));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				if ((i + j) % 2 == 1)
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				else
					squares[i][j].setBackground(Color.WHITE);
				int a = i;
				int b = j;
				squares[i][j].addActionListener((e) -> {
					squares[a][b].click();
				});
				add(squares[i][j]);
			}
		}
		whiteTurn = true;
		whiteCastle = true;
		blackCastle = true;
	}

	Rook whiteR1;
	Rook whiteR2;
	Rook blackR1;
	Rook blackR2;
	
	private void initPieces() {
		 blackR1 = new Rook(new Position(0, 0), this, false);
		 blackR2 = new Rook(new Position(0, 7), this, false);
		 whiteR1 = new Rook(new Position(7, 0), this, true);
		 whiteR2 = new Rook(new Position(7, 7), this, true);
		
		pieces = new ArrayList<Piece>();
		pieces.add(whiteR1);
		pieces.add(whiteR2);
		pieces.add(blackR1);
		pieces.add(blackR2);
		pieces.add(new Knight(new Position(0, 1), this, false));
		pieces.add(new Bishop(new Position(0, 2), this, false));
		pieces.add(new Queen(new Position(0, 3), this, false));
		pieces.add(new King(new Position(0, 4), this, false, whiteR1, whiteR2));
		pieces.add(new Bishop(new Position(0, 5), this, false));
		pieces.add(new Knight(new Position(0, 6), this, false));
		pieces.add(new Pawn(new Position(1, 0), this, false));
		pieces.add(new Pawn(new Position(1, 1), this, false));
		pieces.add(new Pawn(new Position(1, 2), this, false));
		pieces.add(new Pawn(new Position(1, 3), this, false));
		pieces.add(new Pawn(new Position(1, 4), this, false));
		pieces.add(new Pawn(new Position(1, 5), this, false));
		pieces.add(new Pawn(new Position(1, 6), this, false));
		pieces.add(new Pawn(new Position(1, 7), this, false));
		pieces.add(new Pawn(new Position(6, 0), this, true));
		pieces.add(new Pawn(new Position(6, 1), this, true));
		pieces.add(new Pawn(new Position(6, 2), this, true));
		pieces.add(new Pawn(new Position(6, 3), this, true));
		pieces.add(new Pawn(new Position(6, 4), this, true));
		pieces.add(new Pawn(new Position(6, 5), this, true));
		pieces.add(new Pawn(new Position(6, 6), this, true));
		pieces.add(new Pawn(new Position(6, 7), this, true));
		pieces.add(new Knight(new Position(7, 1), this, true));
		pieces.add(new Bishop(new Position(7, 2), this, true));
		pieces.add(new Queen(new Position(7, 3), this, true));
		pieces.add(new King(new Position(7, 4), this, true, blackR1, blackR2));
		pieces.add(new Bishop(new Position(7, 5), this, true));
		pieces.add(new Knight(new Position(7, 6), this, true));
		
		wKingPos = new Position(7, 4);
		bKingPos = new Position(0, 4);
		
		whiteCastle = true;
		blackCastle = true;
		
		updateText();
	}

	public Rook getWhiteR1() {
		return whiteR1;
	}
	public Rook getWhiteR2() {
		return whiteR2;
	}
	public Rook getBlackR1() {
		return blackR1;
	}
	public Rook getBlackR2() {
		return blackR2;
	}
	
	public Piece getPieceAtPos(Position pos) {
		for (Piece p : pieces) {
			if (p.getPos().equals(pos))
				return p;
		}
		return null;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece p) {
		selectedPiece = p;
	}

	public void updateText() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].setText("");
			}
		}
		for (Piece p : pieces) {
			squares[p.getPos().getRow()][p.getPos().getCol()].setText(p.toString());
		}
	}

	public void addPiece(Piece p) {
		pieces.add(p);
	}

	public ArrayList<Position> getAllFriendlyPiecePos(boolean isWhite) {
		ArrayList<Position> ret = new ArrayList<Position>();
		for (Piece p : pieces) {
			if (p.isWhite && isWhite)
				ret.add(p.getPos());
			else if (!p.isWhite && !isWhite)
				ret.add(p.getPos());
		}
		return ret;
	}
	
	public boolean testCheck(boolean isWhite) { // checking if the king of isWhite color is in check
		for (Piece p : pieces) {
			if (p.isWhite != isWhite) {
				for (Position pos : p.getMoveSet()) {
					if (isWhite) {
						if (pos.equals(wKingPos)) {
							return true;
						}
					} else {
						if (pos.equals(bKingPos)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean testCheckmate(boolean isWhite) { // checking if the king of isWhite color is in check
		for (int i = 0; i < pieces.size(); i++) {
			Piece p = pieces.get(i);			
			if (p.isWhite == isWhite) {
				
				System.out.println(p.toString());
				
				Position original = p.getPos();
				for (int j = 0; j < p.getMoveSet().size(); j++) {
					Position pos = p.getMoveSet().get(j);
					Piece taken = p.simMove(pos);
					if (isWhite) {
						if (testCheck(isWhite) == false) {
							if (taken != null) {
								pieces.add(taken);
							}
							p.simMove(original);
							return false;
						}
					} else {
						if (testCheck(!isWhite) == false) {
							if (taken != null) {
								pieces.add(taken);
							}
							p.simMove(original);	
							return false;
						}
					}
					if (taken != null) {
						pieces.add(taken);
					}
					p.simMove(original);
				}
			}
		}
		return true;
	}
	
	public boolean whiteCanCastle() {
		return whiteCastle;
	}
	
	public boolean blackCanCastle() {
		return blackCastle;
	}
	
	public void setWhiteCastle(boolean whiteCastle) {
		this.whiteCastle = whiteCastle;
	}
	
	public void setBlackCastle(boolean blackCastle) {
		this.blackCastle = blackCastle;
	}
	
	public void nextTurn() { 
		whiteTurn = !whiteTurn;
	}
	
	public boolean getWhiteTurn() {
		return whiteTurn;
	}
}