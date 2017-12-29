package eus.julenugalde.thinkinginjava.threading;

//Counter value will always be between
public class TestWaitNotify {
	private Thread upCounter;
	private Thread downCounter;
	
	public static void main(String[] args) {
		TestWaitNotify test = new TestWaitNotify();
		test.testCounters();
	}
	
	public void testCounters() {
		Count count = new Count();
		upCounter = new Thread(new Counting(count, Counting.INCREMENT));
		downCounter = new Thread(new Counting(count, Counting.DECREMENT));
		upCounter.start();
		downCounter.start();
	}
	
	public void notifyIncrementers() {
		upCounter.notify();
	}
	
	public void notifyDecrementers() {
		downCounter.notify();
	}
}

class Counting implements Runnable {
	private Count count;
	private int direction;
	public static final int INCREMENT = 0;
	public static final int DECREMENT = 1;
	
	public Counting(Count count, int direction) {
		this.count = count;
		switch (direction) {
		case DECREMENT:
			this.direction = DECREMENT;
		case INCREMENT:
		default:
			this.direction = INCREMENT;
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				synchronized (count) {
					if (direction == INCREMENT) {
						count.increment();
						System.out.println("Incrementing --> " + count.getValue());
						if (count.isMaxValue()) {
							count.notify();
							count.wait();
						}
					}
					else {	//direction == DECREMENT
						count.decrement();
						System.out.println("Decrementing --> " + count.getValue());
						if (count.isMinValue()) {
							count.notify();
							count.wait();
						}
					}	
				}
			} catch (InterruptedException e) {
				System.err.println("Interrupted exception: " + e.getLocalizedMessage());
			}			
		}
		
	}
	
}

class Count {
	private int value;
	private final int MIN_VALUE = 0;
	private final int MAX_VALUE = 10;
	
	public Count() {
		value = MIN_VALUE;
	}
	
	public void increment() {value++;}
	
	public void decrement() {value--;}
	
	public boolean isMaxValue() { return value>=MAX_VALUE; }
	
	public boolean isMinValue() { return value<=MIN_VALUE; }
	
	public int getValue() { return value; }
}