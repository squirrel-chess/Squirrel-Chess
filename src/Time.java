
public class Time {
	private int mins;
	private int secs;

	public Time(int mins, int secs) {
		this.mins = mins;
		this.secs = secs;
	}

	public void increment() {
		secs--;
		if (secs == -1) {
			secs = 59;
			mins--;
		}
	}

	public int getMins() {
		return mins;
	}

	public int getSecs() {
		return secs;
	}

	public String toString() {
		if (secs >= 10)
			return mins + ":" + secs;
		else
			return mins + ":0" + secs;
	}
}
