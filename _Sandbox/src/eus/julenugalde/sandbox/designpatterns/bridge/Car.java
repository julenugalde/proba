package eus.julenugalde.sandbox.designpatterns.bridge;

/** Refined Abstraction - implementa la abstracción */
public class Car extends Vehicle {

	public Car(Gear gear) {
		super(gear);
	}

	@Override
	void addGear() {
		System.out.print ("Car handles ");
		gear.handleGear();
	}

}
