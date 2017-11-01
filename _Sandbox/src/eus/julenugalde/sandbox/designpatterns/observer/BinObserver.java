package eus.julenugalde.sandbox.designpatterns.observer;

/** Implementación del Observer que representa el valor en formato binario */
public class BinObserver extends Observer {

	public BinObserver(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);	
		System.out.println("observador bin registrado");

	}
	
	@Override
	public void update() {
		System.out.print("\t" + Integer.toBinaryString(subject.getCurrentValue()));
	}
}
