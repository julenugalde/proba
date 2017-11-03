package eus.julenugalde.sandbox.complejos;

/** Interface que define operaciones aritméticas genéricas */
public interface Operaciones<E extends Aritmetico> {
	public void sumar (E elemento);
	public void restar (E elemento);
	public void multiplicar (E elemento);
}
