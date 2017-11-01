package eus.julenugalde.sandbox.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import eus.julenugalde.sandbox.Complejo;

/** Clase que implementa el patron de diseño Iterator */
public class ComplejoBox {
	private List<Complejo> list = new ArrayList<>();
	
	/** Clase interna, no confundir con java.util.Iterator */
	public class Iterator {
		private ComplejoBox box;
		private java.util.Iterator<Complejo> iterator;
		private Complejo value;	//Valor actual
		private boolean done;	//Flag que indica si se ha llegado al final
		
		public Iterator(ComplejoBox complejoBox) {
			box = complejoBox;
			done = false;
		}
		
		/** Lleva el iterador al primer elemento de la lista. Este método no existe en el 
		 * interface Iterator de Java
		 */
		public void first() {
			iterator = box.list.iterator();
			done = false;
			next();
		}
		
		/** Mueve la iteración a la siguiente posición. Si se ha llegado al final de la lista
		 * se recoge la {@link java.util.NoSuchElementException} y se indica que se ha llegado
		 * al final de la lista */
		public void next() {
			try {
				value = (Complejo)iterator.next();
			} catch (NoSuchElementException e) {
				done = true;
			}
		}
		
		/** Indica si se ha llegado al final 
		 * 
		 * @return true si no hay más elementos en el iterador
		 */
		public boolean isDone() {
			return done;
		}
		
		/** Devuelve el valor actual
		 * 
		 * @return Valor del elemento actual en el iterador
		 */
		public Complejo currentValue() {
			return value;
		}
	}
	
	/** Añade un nuevo elemento en la lista
	 * 
	 * @param comp Número complejo a añadir
	 */
	public void add(Complejo comp) {
		list.add(comp);
	}
	
	/** Devuelve el iterador para recorrer la lista
	 * 
	 * @return Objeto ComplejoBox.Iterator que permite recorrer la lista de complejos
	 */
	public Iterator getIterator() {
		return new Iterator(this);
	}
}
