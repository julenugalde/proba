package eus.julenugalde;
/**
 * Excepción de ejemplo para detectar la división por cero en las operaciones con números complejos
 * @author Julen Ugalde
 */
public final class DivisorNuloException extends ArithmeticException {
	private static final long serialVersionUID = -1410044866807862474L;
	
	public DivisorNuloException() {super();}
	public DivisorNuloException(String s) {super("Error de division por cero: " + s);}
	
}
