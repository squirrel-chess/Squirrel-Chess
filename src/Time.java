public class Time {
	private int mins;
	private int secs;
	private int decimal;
	
	public Time(int mins, int secs) {
		this.mins = mins;
		this.secs = secs;
		this.decimal = 0;
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

	
	public void increment() {
		decimal--;
		if (decimal < 0) {
			decimal = decimal + 100;
			secs--;

		}
		
		if (secs < 0) {]
			mins--;
			secs = secs + 60;
			secs = secs + 60;
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
		if (secs >= 10) {
			return mins + ":" + secs + "." + decimal;
		} else {
			return mins + ":0" + secs + "." + decimal;
		}
	}
}