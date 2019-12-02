import java.io.Serializable;

public class Position implements Serializable {
	private int row;
	private int col;
	
	public Position(int row, int col) {
		this.setRow(row);
		this.setCol(col);
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
