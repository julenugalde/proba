package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class Sleeper2 extends Blockable {

	public Sleeper2(Container container) {
		super(container);
	}
	
	public void run() {
		while(true) {
			change();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException iex) {
				System.err.println("Sleeper2 error: " + iex.getLocalizedMessage());
			}
		}
	}
	
	public synchronized void change() {
		i++;
		update();
	}
}
