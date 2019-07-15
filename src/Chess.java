import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Chess {
	
	private JFrame frame;
	private JFrame gameInfo;
	private JPanel textPanel;
	private JTextArea text;
	
	public Chess() {
		JFrame frame = new JFrame("Squirrel Chess");
		JFrame gameInfo = new JFrame("Game Info");
		Board board = new Board(this);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.add(board);
		frame.setVisible(true);
		gameInfo.setSize(200, 200);
		JPanel textPanel = new JPanel();
		JTextArea text = new JTextArea();
	}
	
	public void setGameText(String s) {
		
	}
	
	public static void main(String[] args) {
		new Chess();
	}
}
