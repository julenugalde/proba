package eus.julenugalde.thinkinginjava.polymorphism;

import java.util.TreeSet;

public class Controller {
	protected TreeSet<Event> events = new TreeSet<Event>();
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void run() {
		for (Event e : events) {
			if (e.ready()) {
				e.action();
				System.out.println("Event: " + e.description());
				events.remove(e);		
			}
		}
	}
}
