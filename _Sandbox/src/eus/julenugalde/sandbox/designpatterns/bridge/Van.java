package eus.julenugalde.sandbox.designpatterns.bridge;

/** Refined abstraction - implementa la abstracci�n como furgoneta */
public class Van extends Vehicle {
		
	public Van(Gear gear) {
		super(gear);		
	}

	@Override
	void addGear() {
		System.out.print("Van handles ");
		gear.handleGear();
	}

}
