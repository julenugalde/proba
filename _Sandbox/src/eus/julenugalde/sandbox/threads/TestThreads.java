package eus.julenugalde.sandbox.threads;

import java.util.Random;

public class TestThreads implements Runnable {
	public static final int MAX_DELAY = 5000;
	private Thread hilo;
	private Random randomGenerator;
	private boolean flagStop;
	
	public TestThreads(String nombre) {
		hilo = new Thread(this);
		if (nombre != null) {
			hilo.setName(nombre);
		}
		flagStop = false;
		randomGenerator = new Random();
		hilo.start();
	}

	@Override
	public void run() {
		while (!flagStop) {
			try {
				Thread.sleep(randomGenerator.nextInt(MAX_DELAY));
				System.out.println("Thread " + hilo.getName() + " despierto.");
				//Aleatoriamente se termina el hilo 
				if (randomGenerator.nextInt(10) == 0) {
					System.out.println("Finalizando " + hilo.getName() + "...");
					stop();
				}
			} catch (InterruptedException e) {
				System.err.println(
						"Error en thread " + hilo.getName() + ": " + e.getLocalizedMessage());
			}
		}		
		System.out.println(hilo.getName() + " finalizado.");
	}
	
	public void stop() {
		flagStop = true;
	}
	
	public static void main (String[] args) {
		for (int i=0; i<50; i++) {
			new TestThreads("test " + i);	
		}
	}
}
