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
		Bishop b = new Bishop(new Position(4, 4));
		b.getMoveSet().stream().forEach((s) -> {
			System.out.println(s);
		});
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
