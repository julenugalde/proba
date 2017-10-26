package eus.julenugalde;
/**
 * Excepci�n de ejemplo para detectar la divisi�n por cero en las operaciones con n�meros complejos
 * @author Julen Ugalde
 */
public final class DivisorNuloException extends ArithmeticException {
	private static final long serialVersionUID = -1410044866807862474L;
	
	public DivisorNuloException() {super();}
	public DivisorNuloException(String s) {super("Error de division por cero: " + s);}
	
}
