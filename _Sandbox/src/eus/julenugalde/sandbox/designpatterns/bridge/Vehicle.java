package eus.julenugalde.sandbox.designpatterns.bridge;

/** Clase de abstracci�n que define el interface de implementaci�n */
public abstract class Vehicle {
	Gear gear;
	
	public Vehicle (Gear gear) {
		this.gear = gear;
	}

	abstract void addGear();
}
