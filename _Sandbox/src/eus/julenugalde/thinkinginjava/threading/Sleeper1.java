package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class Sleeper1 extends Blockable {

	public Sleeper1(Container container) {
		super(container);
	}
	
	public synchronized void run() {
		i++;
		update();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException iex) {
			System.err.println("Sleeper1 error: " + iex.getLocalizedMessage());
		}
	}

}
