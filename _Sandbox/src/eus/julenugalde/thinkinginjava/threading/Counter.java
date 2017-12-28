package eus.julenugalde.thinkinginjava.threading;

public class Counter implements Runnable {
	private int id;
	private int count = 0;
	private boolean flagCounting = true;
	private CounterUI userInterface;
		
	public Counter(int id, CounterUI userInterface) {
		this.id = id;
		this.userInterface = userInterface;
	}
	
	public void setCounting(boolean counting) {
		flagCounting = counting;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				if (flagCounting) {
					userInterface.setCount(id, ++count);
				}
			} catch (InterruptedException iex) {
				System.err.println("Error: interrupted exception: " + iex.getLocalizedMessage());
			}			
		}
	}
}
