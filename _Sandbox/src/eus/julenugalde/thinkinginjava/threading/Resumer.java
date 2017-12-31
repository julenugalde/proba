package eus.julenugalde.thinkinginjava.threading;

public class Resumer extends Thread {
	private SuspendResume sr;

	public Resumer(SuspendResume sr) {
		this.sr = sr;
		start();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException iex) {
			}
			sr.resume();
		}
	}
}
