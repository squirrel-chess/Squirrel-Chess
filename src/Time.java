public class Time {
	private int mins;
	private int secs;
	private int millis;
	private long lastTime;
	private long currentMillis;
	private boolean paused = false;
	static private double accumulatedTime = 0;
	static private long start;
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
		lastTime = System.currentTimeMillis();
		start = System.currentTimeMillis();
	}
	public long getLastTime() {
		return lastTime;
	}
	public long getCurrentMillis() {
		return currentMillis;
	}
	public int toMillis() {
		int mils = 0;
		mils += mins*60000;
		mils += secs*1000;
		mils+= millis;
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
	static long passedTime = 0;
	public void endTurn() {
		lastEnd = System.currentTimeMillis();
		long timeChange = 0;
		if(paused==false) {
//			System.out.println(lastEnd);
//			System.out.println(start);
			
			accumulatedTime+= ((double)lastEnd-(double)start);
			//accumulatedTime = (double)(System.currentTimeMillis()- accumulatedTime);
	
			if(passedTime>0) {
				System.out.println("asdf");
				
			 timeChange = (long) Math.abs((accumulatedTime)-lastTime-passedTime);
			} else {
				timeChange = (long) Math.abs((accumulatedTime)-lastTime);
				//start = lastTime;
			}
			System.out.println(accumulatedTime - lastTime);
		//	 System.out.println(accumulatedTime); 
			 // accumulated is about double its previous time after play is pressed --> which is why the time its 0 immeidately after play button

			 passedTime = 0;

		} else {
			passedTime+= System.currentTimeMillis()-start;
		}
	//
		//System.out.println(timeChange);
	//	System.out.println(timeChange); //should be around 3000 millis --> 3 seconds
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
		startTurn();
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