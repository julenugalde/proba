package eus.julenugalde.sandbox.designpatterns.bridge;

/** Implementaci�n concreta del implementor */
public class ManualGear implements Gear {

	@Override
	public void handleGear() {
		System.out.println("manual gear");
	}
}
