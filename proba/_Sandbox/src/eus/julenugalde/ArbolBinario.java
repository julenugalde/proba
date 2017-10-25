package eus.julenugalde;

/** Clase que define un arbol binario con elementos de tipo 
 * {@link eus.julenugalde.Nodo}. Incluye m�todos para la
 * visualizaci�n sencilla de los elementos con preorder, inorder y postorder
 * 
 * @param <E> Tipo gen�rico de los elementos contenidos en el nodo
 */
public class ArbolBinario<E> {
	private Nodo<E> raiz;
	
	/** Orientaci�n del nodo hijo respecto a su padre*/
	public enum Orientacion {
		/** Nodo hijo a la izquierda del nodo padre */
		IZQUIERDA, 
		/** Nodo hijo a la derecha del nodo padre */
		DERECHA		
	};
	
	/** Orden a la hora de recorrer el �rbol */
	public enum Recorrido {
		/** Recorrido en el que primero se visita el nodo actual, luego el hijo izquierdo 
		 y por �ltimo el hijo derecho */
		PREORDEN, 	
		/** Recorrido en el que primero se visita el hijo izquierdo, luego el hijo derecho 
		y por �ltimo el nodo actual */
		POSTORDEN,  
		/** Recorrido en el que primero se visita el hijo izquierdo, luego el nodo actual y
		por �ltimo el hijo derecho */
		INORDEN		
	};
	
	/** Constructor que crea un nuevo �rbol binario a partir de un nodo raiz 
	 * 
	 * @param nodoRaiz Nodo ra�z a partir del que se construye el �rbol
	 */
	public ArbolBinario(Nodo<E> nodoRaiz) {
		raiz = nodoRaiz;
	}
	
	/** M�todo que a�ade un nodo hijo respecto del nodo indicado como padre. No se comprueba si ya 
	 * hab�a un nodo hijo en esa posici�n
	 * 
	 * @param nodo Nodo de tipo {@link Nodo} que se insertar�
	 * @param padre Nodo padre
	 * @param orientacion Elemento de tipo {@link Orientacion} que indica si se insertar� a la izquierda
	 * o derecha respecto del nodo padre
	 */
	public void addHijo (Nodo<E> nodo, Nodo<E> padre, Orientacion orientacion) {
		if (orientacion == Orientacion.IZQUIERDA) 
			padre.setIzquierdo(nodo);
		else //Orientacion.DERECHA
			padre.setDerecha(nodo);
	}
	
	/** M�todo que saca por pantalla el contenido del �rbol
	 * 
	 * @param formaRecorrer Valor perteneciente a la enumeraci�n {@link Recorrido} que indica
	 * la forma de recorrer el �rbo: preorden, inorden o postorden
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
	 * por �ltimo el hijo derecho
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del �rbol
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
	 * por �ltimo el nodo actual
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del �rbol
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
	 * por �ltimo el hijo derecho
	 * @param nodoActual Nodo actual
	 * @param profundidad Nivel de profundidad dentro del �rbol
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
