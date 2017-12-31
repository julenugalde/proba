package eus.julenugalde.thinkinginjava.threading;

public class Notifier implements Runnable {
	private WaitNotify2 wn2;
	
	public Notifier(WaitNotify2 wn2) {
		this.wn2 = wn2;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch(InterruptedException iex) {
				System.err.println("InterruptedException@Notifier: " + iex.getLocalizedMessage());
			}
			synchronized(wn2) {
				wn2.notify();
			}
		}		
	}

}
