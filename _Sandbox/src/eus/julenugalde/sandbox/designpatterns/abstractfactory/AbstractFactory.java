package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** Clase AbstractFactory con método static que devuelve un toolkit concreto 
 * en función del tipo de arquitectura
 */
public abstract class AbstractFactory {
	private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
	private static final EnginolaToolkit ENGINOLA_TOOLKIT = new EnginolaToolkit();
	
    // Returns a concrete factory object that is an instance of the
    // concrete factory class appropriate for the given architecture
	public static AbstractFactory getFactory(Architecture architecture) {
		AbstractFactory factory = null;
		switch (architecture) {
		case EMBER:
			factory = EMBER_TOOLKIT;
			break;
		case ENGINOLA:
			factory = ENGINOLA_TOOLKIT;
			break;				
		}
		return factory;
	}
	
	public abstract CPU createCPU(int serialCode);
	public abstract MMU createMMU();
}
