public class Time {
	private int mins;
	private int secs;
	private int millis;
	private long lastTime;

	public Time(int mins, int secs) {
		this.mins = mins;
		this.secs = secs;
		lastTime = System.currentTimeMillis();
	}

	public void startTurn() {
		lastTime = System.currentTimeMillis();
	}

	public void endTurn() {
		long timeChange = System.currentTimeMillis() - lastTime;
		long minChange = timeChange / 60000;
		long secChange = (timeChange - (minChange * 60000)) / 1000;
		long milliChange = (timeChange - (minChange * 60000) - (secChange * 1000));
		mins -= minChange;
		secs -= secChange;
		millis -= milliChange;

		if (millis < 0) {
			millis = millis + 1000;
			secs--;

		}

		if (secs < 0) {
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

	public boolean isZero() {
		return mins < 0;
	}

	public String toString() {
		if (mins < 0) {
			return "0:00";
		} else {
			if (secs >= 10) {
				if (millis > 0) {
					if (secs < 59) {
						return mins + ":" + (secs + 1);
					} else {
						return (mins + 1) + ":00";
					}
				} else {
					return mins + ":" + secs;
				}
			} else {
				if (millis > 0) {
					return mins + ":0" + (secs + 1);
				} else {
					return mins + ":0" + secs;
				}
			}
		}
	}
}