package eus.julenugalde.sandbox.designpatterns.bridge;

/** Clase de abstracción que define el interface de implementación */
public abstract class Vehicle {
	Gear gear;
	
	public Vehicle (Gear gear) {
		this.gear = gear;
	}

	abstract void addGear();
}
