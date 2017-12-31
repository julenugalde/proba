package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class WaitNotify2 extends Blockable {

	public WaitNotify2(Container container) {
		super(container);
		new Notifier(this);
	}

	public synchronized void run() {
		while (true) {
			i++;
			update();
			try {
				wait();
			} catch (InterruptedException iex) {
				System.err.println("InterruptedException@WaitNotify2: "+iex.getLocalizedMessage());
			}
		}
	}
}
