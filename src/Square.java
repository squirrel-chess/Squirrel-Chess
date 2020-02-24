import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Square extends JButton {

	private static final long serialVersionUID = -3661941489557446453L;
	private boolean inMoveSet;
	private Game game;
	private Board board;
	private Position pos;
	
	public Square(Game game, Board board, Position pos) {
		this.inMoveSet = false;
		this.game = game;
		this.board = board;
		this.pos = pos;
		setOpaque(true);
	}

	public void click() {
		System.out.println("Click " + pos);
		if (board.getSelectedPiece() == null && game.getPieceAtPos(pos) != null) {
			game.getPieceAtPos(pos).select();
		} else if (inMoveSet && board.getSelectedPiece() != null) {
			board.getSelectedPiece().move(pos);
			if (game.testCheck(true)) {
				JOptionPane.showMessageDialog(null, "Black wins!");
				board.playAgainMenu();
			} else if (game.testCheck(false)) {
				JOptionPane.showMessageDialog(null, "White wins!");
				board.playAgainMenu();
			} else if (game.testCheck(true)) {
				JOptionPane.showMessageDialog(null, "White King is in check!");
			} else if (game.testCheck(false)) {
				JOptionPane.showMessageDialog(null, "Black King is in check!");
			}
		} else {
			board.setSelectedPiece(null);
			board.unhighlightMoves();
		}
	}

	public void setInMoveSet(boolean inMoveSet) {
		this.inMoveSet = inMoveSet;
	}
}