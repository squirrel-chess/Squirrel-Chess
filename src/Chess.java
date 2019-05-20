import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chess {
	public Chess() {
		JFrame frame = new JFrame("Squirrel Chess");
		Board board = new Board();
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.add(board);
		frame.setVisible(true);
		Queen b = new Queen(new Position(4, 4), board, true);
		for (Position p : b.getMoveSet())
			System.out.println(p);
		b.move(b.getMoveSet());
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
