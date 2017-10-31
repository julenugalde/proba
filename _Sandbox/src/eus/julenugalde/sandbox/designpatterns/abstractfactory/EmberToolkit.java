package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** Implementaci�n de factory para arquitectura Ember */
public class EmberToolkit extends AbstractFactory {

	@Override
	public CPU createCPU(int serialCode) {
		return new EmberCPU(serialCode);
	}

	@Override
	public MMU createMMU() {
		return new EmberMMU();
	}

}
