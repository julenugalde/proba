package eus.julenugalde.sandbox.designpatterns.bridge;

/** Implementación concreta del interface gear - cambio automático */
public class AutoGear implements Gear {

	@Override
	public void handleGear() {
		System.out.println("automatic gear");
	}

}
