import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {
	Square[][] squares;
	ArrayList<Piece> pieces;

	public Board() {
		pieces = new ArrayList<Piece>();
		initBoard();
		initPieces();
	}

	public void highlightMoves(Piece p) {
		for (Position pos : p.getMoveSet()) {
			System.out.println(pos);
			squares[pos.getRow()][pos.getCol()].setBackground(new Color(160, 255, 160));
		}
	}

	public void unhighlightMoves(Piece p) {
		for (Position pos : p.getMoveSet())
			if ((pos.getRow() + pos.getCol()) % 2 == 1)
				squares[pos.getRow()][pos.getCol()].setBackground(Color.LIGHT_GRAY);
			else
				squares[pos.getRow()][pos.getCol()].setBackground(Color.WHITE);
	}

	public void removePiece(Piece p) {
		pieces.remove(p);
	}

	private void initBoard() {
		squares = new Square[8][8];
		setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(this, new Position(i, j));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				squares[i][j].setText(i + ", " + j);
				if ((i + j) % 2 == 1) {
					squares[i][j].setBackground(Color.LIGHT_GRAY);
				}
				int a = i;
				int b = j;
				squares[i][j].addActionListener((e) -> {
					squares[a][b].click();
				});
				add(squares[i][j]);
			}
		}
	}

	private void initPieces() {
		pieces = new ArrayList<Piece>();
		pieces.add(new Rook(new Position(0, 0), this, false));
		pieces.add(new Knight(new Position(0, 1), this, false));
		pieces.add(new Bishop(new Position(0, 2), this, false));
		pieces.add(new Queen(new Position(0, 3), this, false));
		pieces.add(new King(new Position(0, 4), this, false));
		pieces.add(new Bishop(new Position(0, 5), this, false));
		pieces.add(new Knight(new Position(0, 6), this, false));
		pieces.add(new Rook(new Position(0, 7), this, false));
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
		pieces.add(new Rook(new Position(7, 0), this, true));
		pieces.add(new Knight(new Position(7, 1), this, true));
		pieces.add(new Bishop(new Position(7, 2), this, true));
		pieces.add(new Queen(new Position(7, 3), this, true));
		pieces.add(new King(new Position(7, 4), this, true));
		pieces.add(new Bishop(new Position(7, 5), this, true));
		pieces.add(new Knight(new Position(7, 6), this, true));
		pieces.add(new Rook(new Position(7, 7), this, true));

		for (Piece p : pieces) {
			squares[p.getPos().getRow()][p.getPos().getCol()].setText(p.toString());
		}
	}

	public Piece getPieceAtPos(Position pos) {
		for (Piece p : pieces) {
			if (p.getPos().equals(pos))
				return p;
		}
		return null;
	}
}
