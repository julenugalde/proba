package eus.julenugalde.thinkinginjava.threading;

import java.util.Random;

public class Countdown implements Runnable {
	private int id;
	private int count;
	
	public Countdown(int id, int startValue) {
		this.id = id;
		count = startValue;
		System.out.println("Countdown #" + id + " started with initial value " + count);
	}
	
	@Override
	public void run() {
		while (count > 0) {
			System.out.println("Countdown #" + id + "..." + (--count));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Countdown #" + id + " finished.");
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		for (int i=0; i<10; i++) {
			new Thread(new Countdown(i, random.nextInt(10) + 10)).start();			
		}
		System.out.println("All threads started.");
	}
}
