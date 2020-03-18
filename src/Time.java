public class Time {
	private int mins;
	private int secs;
	private int millis;
	 private long lastTime;
	static private long lastTimeHold;
	private long currentMillis;
	private boolean paused = false;
	static private double accumulatedTime = 0;
	 private long start;
	private long lastEndW;
	private long lastEndB;
	 private long lastEnd;

	public Time(int mins, int secs) {
		this.mins = mins;
		this.secs = secs;
		lastTime = System.currentTimeMillis();
	}

	public long getLastW() {
		return lastEndW;
	}

	public long getBlackW() {
		return lastEndB;
	}

	public void setLastW(long l) {
		lastEndW = l;
	}

	public void setLastB(long l) {
		lastEndB = l;
	}

	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public void startTurn() {
		long l = 0;
		if(lastTimeHold>0) {
			l+=lastTime - lastTimeHold;
		}
		lastTime = System.currentTimeMillis() - l;
		start = System.currentTimeMillis();
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long s) {
		lastTime = s;
	}

	public long getCurrentMillis() {
		return currentMillis;
	}

	public int toMillis() {
		int mils = 0;
		mils += mins * 60000;
		mils += secs * 1000;
		mils += millis;
		return mils;
	}

	public void setMins(int min) {
		mins = min;
	}

	public void setSecs(int sec) {
		secs = sec;
	}

	public void setStartValue(long s) {
		start = s;
	}

	public long getStart() {
		return start;
	}

	public void setLastTimeHold(long l) {
		lastTimeHold = l;
	}

	public long getLastTimeHold() {
		return lastTimeHold;
	}

	static long passedTime = 0;

	public void endTurn() {
		lastEnd = System.currentTimeMillis();
		System.out.println(lastTimeHold+ "hold");
		System.out.println(lastTime +"lastTime");
	
		long timeChange = 0;
		if (paused == false) {
			accumulatedTime += ((double) lastEnd - (double) start);

			if (passedTime > 0) {
				System.out.println("asdf");

				timeChange = (long) Math.abs((accumulatedTime) - lastTime - passedTime);

			} else {
				// timeChange = (long) Math.abs((accumulatedTime) - lastTime);
				timeChange = (long) (accumulatedTime) - lastTime;
				//lastTIme is a much larger than accumulatedtime after paused and then play
				System.out.println(timeChange);
			}

			passedTime = 0;
		}
		// System.out.println(timeChange); //should be around 3000 millis --> 3
		// seconds
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
		// startTurn();
	

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