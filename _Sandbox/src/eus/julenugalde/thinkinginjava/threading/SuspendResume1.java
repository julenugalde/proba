package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class SuspendResume1 extends SuspendResume {
	public SuspendResume1(Container c) {
		super(c);
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void run() {
		while (true) {
			i++;
			update();
			suspend();
		}
	}
}
