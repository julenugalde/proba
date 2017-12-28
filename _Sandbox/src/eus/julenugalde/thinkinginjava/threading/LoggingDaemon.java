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
					int result = 0;
					for (int i=0; i<countValues.length; i++) {
						result += countValues[i];
					}
					System.out.println(new java.util.Date().toString() + " --> " + result);
				}
			} catch(InterruptedException iex) {
				System.err.println("Error with logging daemon: " + iex.getLocalizedMessage());
			}
		}
		
	}

}
