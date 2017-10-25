package eus.julenugalde;

/** Clase que define un arbol binario con elementos de tipo 
 * {@link eus.julenugalde.Nodo}. Incluye métodos para la
 * visualización sencilla de los elementos con preorder, inorder y postorder
 * 
 * @param <E> Tipo genérico de los elementos contenidos en el nodo
 */
public class ArbolBinario<E> {
	private Nodo<E> raiz;
	
	/** Orientación del nodo hijo respecto a su padre*/
	public enum Orientacion {
		/** Nodo hijo a la izquierda del nodo padre */
		IZQUIERDA, 
		/** Nodo hijo a la derecha del nodo padre */
		DERECHA		
	};
	
	/** Orden a la hora de recorrer el árbol */
	public enum Recorrido {
		/** Recorrido en el que primero se visita el nodo actual, luego el hijo izquierdo 
		 y por último el hijo derecho */
		PREORDEN, 	
		/** Recorrido en el que primero se visita el hijo izquierdo, luego el hijo derecho 
		y por último el nodo actual */
		POSTORDEN,  
		/** Recorrido en el que primero se visita el hijo izquierdo, luego el nodo actual y
		por último el hijo derecho */
		INORDEN		
	};
	
	/** Constructor que crea un nuevo árbol binario a partir de un nodo raiz 
	 * 
	 * @param nodoRaiz Nodo raíz a partir del que se construye el árbol
	 */
	public ArbolBinario(Nodo<E> nodoRaiz) {
		raiz = nodoRaiz;
	}
	
	/** Método que añade un nodo hijo respecto del nodo indicado como padre. No se comprueba si ya 
	 * había un nodo hijo en esa posición
	 * 
	 * @param nodo Nodo de tipo {@link Nodo} que se insertará
	 * @param padre Nodo padre
	 * @param orientacion Elemento de tipo {@link Orientacion} que indica si se insertará a la izquierda
	 * o derecha respecto del nodo padre
	 */
	public void addHijo (Nodo<E> nodo, Nodo<E> padre, Orientacion orientacion) {
		if (orientacion == Orientacion.IZQUIERDA) 
			padre.setIzquierdo(nodo);
		else //Orientacion.DERECHA
			padre.setDerecha(nodo);
	}
	
	/** Método que saca por pantalla el contenido del árbol
	 * 
	 * @param formaRecorrer Valor perteneciente a la enumeración {@link Recorrido} que indica
	 * la forma de recorrer el árbo: preorden, inorden o postorden
	 */
	public void mostrarArbol (Recorrido formaRecorrer) {
		int profundidad = 0;
		Nodo<E> temp;
		
		switch (formaRecorrer) {
		case PREORDEN:
			System.out.println(raiz.toString());
			if ((temp = raiz.getIzquierda()) != null)
				preOrden (temp, profundidad);
			if ((temp = raiz.getDerecha()) != null)
				preOrden (temp, profundidad);	
			break;
		
		case POSTORDEN:
			if ((temp = raiz.getIzquierda()) != null)
				postOrden (temp, profundidad);
			if ((temp = raiz.getDerecha()) != null)
				postOrden (temp, profundidad);
			System.out.println(raiz.toString());
			break;
			
		case INORDEN:
			if ((temp = raiz.getIzquierda()) != null)
				inOrden (temp, profundidad);
			System.out.println(raiz.toString());
			if ((temp = raiz.getDerecha()) != null)
				inOrden (temp, profundidad);
			break;			
		}		
	}
	
	/**
	 * Recorrido en el que primero se visita el nodo actual, luego el hijo izquierdo y
	 * por último el hijo derecho
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del árbol
	 */
	public void preOrden(Nodo<E>nodoActual, int profundidad) {
		profundidad++;
		for (int i=0; i<profundidad; i++)
			System.out.print('\t');
		System.out.println(nodoActual.toString());
		Nodo<E> temp;
		if ((temp = nodoActual.getIzquierda()) != null)	
			preOrden (temp, profundidad);
		if ((temp = nodoActual.getDerecha()) != null)
			preOrden (temp, profundidad);
	}	
	
	/**
	 * Recorrido en el que primero se visita el hijo izquierdo, luego el hijo derecho y
	 * por último el nodo actual
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del árbol
	 */
	public void postOrden(Nodo<E>nodoActual, int profundidad) {
		profundidad++;
		Nodo<E> temp;
		if ((temp = nodoActual.getIzquierda()) != null)	
			postOrden (temp, profundidad);
		if ((temp = nodoActual.getDerecha()) != null)
			postOrden (temp, profundidad);
		for (int i=0; i<profundidad; i++)
			System.out.print('\t');
		System.out.println(nodoActual.toString());		
	}	

	/**
	 * Recorrido en el que primero se visita el hijo izquierdo, luego el nodo actual y
	 * por último el hijo derecho
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del árbol
	 */
	public void inOrden(Nodo<E>nodoActual, int profundidad) {
		profundidad++;
		Nodo<E> temp;
		if ((temp = nodoActual.getIzquierda()) != null)	
			inOrden (temp, profundidad);
		for (int i=0; i<profundidad; i++)
			System.out.print('\t');
		System.out.println(nodoActual.toString());		
		if ((temp = nodoActual.getDerecha()) != null)
			inOrden (temp, profundidad);
		
	}		
}
