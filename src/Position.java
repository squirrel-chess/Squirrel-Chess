import java.io.Serializable;

public class Position implements Serializable {

	private static final long serialVersionUID = -3599838962682075666L;
	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Position(String pos) {
		try {
			row = Integer.parseInt(pos.substring(1, 2));
			col = Integer.parseInt(pos.substring(4, 5));
		} catch (NumberFormatException e) {
			System.err.println("Something is very wrong.");
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String toString() {
		return "(" + row + ", " + col + ")";
	}

	public boolean equals(Position pos) {
		return (pos.getCol() == col && pos.getRow() == row);
	}
}
