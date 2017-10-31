package eus.julenugalde.sandbox.designpatterns.bridge;

/** Refined abstraction - implementa la abstracci�n como cami�n */
public class Truck extends Vehicle {
	public Truck (Gear gear) {
		super(gear);
	}
	
	@Override
	void addGear() {
		System.out.print("Truck handles ");
		gear.handleGear();
	}

}
