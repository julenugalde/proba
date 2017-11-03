package eus.julenugalde.sandbox.threads;

import java.util.Random;

public class Mensaje implements Runnable {
	private String cadena;
	private long tiempoEspera;
	private int cuenta;
	
	public Mensaje(String cadena, long tiempoEspera) {
		this.cadena = cadena;
		this.tiempoEspera = tiempoEspera;
		cuenta = 0;
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.println("Se ha despertado el hilo #" + cadena + 
					" (" + (++cuenta) + " iteraciones) ...");
			try {
				Thread.sleep(tiempoEspera);
			} catch (InterruptedException e) {
				System.err.println("Interrupted Exception: " + e.getLocalizedMessage());
				break;
			}
		}

	}

	public static void main(String[] args) {
		Random r = new Random(123456789L);
		
		Thread[] hilos = new Thread[5];
		long tiempo;
		for (int i=0; i<hilos.length; i++) {
			tiempo = (r.nextLong() % 2000) + 2000;
			hilos[i] = new Thread (new Mensaje(String.valueOf(i), tiempo));
			System.out.println("Creado el hilo #" + i + " con tiempo de sleep " + tiempo + " ms");
			hilos[i].start();
		}
		
	}

}
