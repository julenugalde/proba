package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** MMU de la arquitectura Ember */
public class EnginolaMMU extends MMU {
	public EnginolaMMU() {
		
	}
	
	@Override
	public String getArchitectureName() {
		return "Enginola";
	}

	@Override
	public String toString() {
		return "Enginola MMU";
	}
}