package eus.julenugalde;

public class Nodo<E> {
	private int clave;
	private Nodo<E> izquierda;
	private Nodo<E> derecha;
	private E contenido;
	
	public Nodo (E elemento, int key) {
		clave = key;
		izquierda = null;
		derecha = null;
		contenido = elemento;		
	}
	
	public void setKey (int key) {clave = key;}
	public int getKey () {return clave;}
	public void setIzquierdo (Nodo<E> nodo) {izquierda = nodo;}
	public Nodo<E> getIzquierda () {return izquierda;}
	public void setDerecha (Nodo<E> nodo) {derecha = nodo;}
	public Nodo<E> getDerecha() {return derecha;}
	
	
}
