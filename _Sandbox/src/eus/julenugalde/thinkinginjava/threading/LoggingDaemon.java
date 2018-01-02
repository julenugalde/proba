package eus.julenugalde.thinkinginjava.threading;

public class LoggingDaemon implements Runnable {
	private CounterUI ui;
	
	public LoggingDaemon(CounterUI userInterface) {
		ui = userInterface;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				//We display the sum of the count values in the console every 5 seconds
				Thread.sleep(5000);
				int[] countValues = ui.getCountValues();
				if (countValues == null) {
					System.err.println("Logging daemon error: Count values could not be accessed");
				}
				else {
					double reference = (double)countValues[0];
					System.out.print(new java.util.Date().toString() + " --> ");
					for (int i=0; i<countValues.length; i++) {
						System.out.print(i + ":" + 100*((double)countValues[i])/reference + "%  ");
					}
					System.out.println();
				}
			} catch(InterruptedException iex) {
				System.err.println("Error with logging daemon: " + iex.getLocalizedMessage());
			}
		}
		
	}

}
