package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** Implementaci�n de factory para arquitectura Enginola */
public class EnginolaToolkit extends AbstractFactory {

	@Override
	public CPU createCPU(int serialCode) {
		return new EnginolaCPU(serialCode);
	}

	@Override
	public MMU createMMU() {
		return new EnginolaMMU();
	}

}
