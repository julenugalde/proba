package eus.julenugalde;

/** Clase que define un nodo generico para un arbol binario */
public class Nodo<E> {
	private Nodo<E> izquierda;
	private Nodo<E> derecha;
	private E contenido;
	
	/** Constructor que crea un nodo terminal, al que más adelante se pueden añadir nodos hijos 
	 * 
	 * @param elemento Elemento que contendrá el nodo
	 */
	public Nodo (E elemento) {
		izquierda = null;
		derecha = null;
		contenido = elemento;		
	}
		
	public void setIzquierdo (Nodo<E> nodo) {izquierda = nodo;}
	public Nodo<E> getIzquierda () {return izquierda;}
	public void setDerecha (Nodo<E> nodo) {derecha = nodo;}
	public Nodo<E> getDerecha() {return derecha;}
	
	@Override
	public String toString() {
		return contenido.toString();
	}
}
