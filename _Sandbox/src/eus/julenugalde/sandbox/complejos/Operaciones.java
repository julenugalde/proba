package eus.julenugalde.sandbox.complejos;

/** Interface que define operaciones aritm�ticas gen�ricas */
public interface Operaciones<E extends Aritmetico> {
	public void sumar (E elemento);
	public void restar (E elemento);
	public void multiplicar (E elemento);
}
