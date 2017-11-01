package eus.julenugalde.sandbox.designpatterns.observer;

/** Implementaci�n del Observer que representa el valor en formato octal */
public class OctObserver extends Observer {

	public OctObserver(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
		System.out.println("observador oct registrado");

	}
	
	@Override
	public void update() {
		System.out.print("\t0" + Integer.toOctalString(subject.getCurrentValue()));
	}
}