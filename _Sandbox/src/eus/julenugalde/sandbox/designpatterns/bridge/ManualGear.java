package eus.julenugalde.sandbox.designpatterns.bridge;

/** Implementación concreta del implementor */
public class ManualGear implements Gear {

	@Override
	public void handleGear() {
		System.out.println("manual gear");
	}
}
