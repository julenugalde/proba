package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** CPU de la arquitectura Ember. Se identifica por el número de serie */
public class EmberCPU extends CPU {
	private int serialCode;
	
	public EmberCPU (int serialCode) {
		this.serialCode = serialCode;
	}
	
	@Override
	public String getArchitectureName() {
		return "Ember";
	}
	
	@Override
	public String toString() {
		return "Ember CPU with serial code " + serialCode;
	}
}
