package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** MMU de la arquitectura Ember */
public class EmberMMU extends MMU {
	public EmberMMU() {
		
	}
	
	@Override
	public String getArchitectureName() {
		return "Ember";
	}

	@Override
	public String toString() {
		return "Ember MMU";
	}
}
