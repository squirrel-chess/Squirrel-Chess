
public class Time {
	private int mins;
	private int secs;
	private int millis;
	private long lastTime;

	public Time(int mins, int secs) {
		this.mins = mins;
		this.secs = secs;
		this.millis = 0;
		lastTime = System.currentTimeMillis();
	}

	public void update() {
		long timeChange = System.currentTimeMillis() - lastTime;
		long minChange = timeChange / 60000;
		long secChange = (timeChange - (minChange * 60000)) / 1000;
		long milliChange = (timeChange - (minChange * 60000) - (secChange * 1000));
		mins -= minChange;
		secs -= secChange;
		millis -= milliChange;
		
		if (millis < 0) {
			secs--;
			millis = millis + 1000;
		}
		
		if (secs < 0) {
			mins--;
			secs = secs + 60;
		}
		lastTime = System.currentTimeMillis();
	}

	public int getMins() {
		return mins;
	}

	public int getSecs() {
		return secs;
	}

	public String toString() {
		if (secs >= 10)
			return mins + ":" + secs + "." + millis;
		else
			return mins + ":0" + secs + "." + millis;
	}
}
