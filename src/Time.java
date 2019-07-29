
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
		mins -= timeChange / 60000;
		timeChange -= timeChange / 60000;
		secs -= timeChange / 1000;
		timeChange -= timeChange / 1000;
		millis -= timeChange;
		if (millis < 0) {
			int secChange = -millis / 1000;
			secs -= secChange;
			millis -= 
			millis = millis + 1000;		
		}
		if (secs < 0) {
			
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
