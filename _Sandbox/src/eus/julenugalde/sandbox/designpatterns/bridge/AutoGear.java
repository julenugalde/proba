package eus.julenugalde.sandbox.designpatterns.bridge;

/** Implementaci�n concreta del interface gear - cambio autom�tico */
public class AutoGear implements Gear {

	@Override
	public void handleGear() {
		System.out.println("automatic gear");
	}

}
