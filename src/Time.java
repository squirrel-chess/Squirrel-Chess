
public class Time {
	private int mins;
	private int secs;
	
	public Time() {
		mins = 0;
		secs = 0;
	}
	
	public void increment() {
		secs++;
		if (secs == 60) {
			secs = 0;
			mins++;
		}
	}
	
	public int getMins() {
		return mins;
	}
	
	public int getSecs() {
		return secs;
	}
}
