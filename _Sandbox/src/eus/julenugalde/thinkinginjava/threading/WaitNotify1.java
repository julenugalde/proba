package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class WaitNotify1 extends Blockable {

	public WaitNotify1(Container container) {
		super(container);
	}
	
	public synchronized void run() {
		i++;
		update();
		try {
			wait(1000);
		} catch (InterruptedException iex) {
			System.err.println("Interrupted exception @ WaitNotify1: "+iex.getLocalizedMessage());
		}
	}
}
