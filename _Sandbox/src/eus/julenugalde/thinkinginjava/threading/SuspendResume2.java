package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class SuspendResume2 extends SuspendResume{

	SuspendResume2(Container c) {
		super(c);
	}
	
	@SuppressWarnings("deprecation")
	public void run()  {
		while(true) {
			change();
			suspend();
		}
	}
	
	public synchronized void change() {
		i++;
		update();
	}

}
