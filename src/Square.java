import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Square extends JButton {

	private static final long serialVersionUID = -3661941489557446453L;
	private boolean inMoveSet;
	private Game game;
	private Position pos;
	
	public Square(Game game, Position pos) {
		this.inMoveSet = false;
		this.game = game;
		this.pos = pos;
		setOpaque(true);
	}

	public void click() {
		if (game.getSelectedPiece() == null && game.getPieceAtPos(pos) != null) {
			game.getPieceAtPos(pos).select();
		} else if (inMoveSet && game.getSelectedPiece() != null) {
			game.getSelectedPiece().move(pos);
			if (game.testCheck(true)) {
				JOptionPane.showMessageDialog(null, "Black wins!");
				
				//b.playAgainMenu();
				
			} else if (game.testCheckmate(false)) {
				JOptionPane.showMessageDialog(null, "White wins!");
				
				//b.playAgainMenu();
				
			} else if (game.testCheck(true)) {
				JOptionPane.showMessageDialog(null, "White King is in check!");
			} else if (game.testCheck(false)) {
				JOptionPane.showMessageDialog(null, "Black King is in check!");
			}
		} else {
			game.setSelectedPiece(null);
			game.unhighlightMoves();
		}
	}

	public void setInMoveSet(boolean inMoveSet) {
		this.inMoveSet = inMoveSet;
	}
}