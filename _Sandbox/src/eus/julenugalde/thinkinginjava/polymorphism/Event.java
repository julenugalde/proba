package eus.julenugalde.thinkinginjava.polymorphism;

public abstract class Event implements Comparable <Event>{
	private long evtTime;
	public Event(long eventTime) {
		evtTime = eventTime;
	}
	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}
	
	abstract public void action();
	abstract public String description();
	
	public int compareTo(Event e) {
		if (e == null) return -1;
		if (evtTime == e.evtTime) return 0;
		return -1;
	}
}
