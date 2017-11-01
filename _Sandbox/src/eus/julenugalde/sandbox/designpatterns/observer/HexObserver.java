package eus.julenugalde.sandbox.designpatterns.observer;

/** Implementaci�n del Observer que representa el valor en formato hexadecimal */
public class HexObserver extends Observer {

	public HexObserver(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);	
		System.out.println("observador hex registrado");
	}
	
	@Override
	public void update() {
		System.out.print("\t0x" + Integer.toHexString(subject.getCurrentValue()));
	}
}
