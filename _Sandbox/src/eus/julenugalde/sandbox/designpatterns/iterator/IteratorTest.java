package eus.julenugalde.sandbox.designpatterns.iterator;

import eus.julenugalde.sandbox.Complejo;

/** Clase para probar el patron de diseño Iterator.
 * Extraido de {@link https://sourcemaking.com/design_patterns/iterator/java/1}
 */
public class IteratorTest {
	public static void main (String[] args) {
		//Creamos un objeto ComplejoBox y lo rellenamos con algunos datos
		ComplejoBox compBox = new ComplejoBox();
		compBox.add(new Complejo(0,2));
		compBox.add(new Complejo(4,-5));
		compBox.add(new Complejo(3.2,-8));
		compBox.add(new Complejo(0.2,0));
		compBox.add(new Complejo(-7,1.3));
		compBox.add(new Complejo(66,5.7));
		compBox.add(new Complejo(-5,-22.3));
		compBox.add(new Complejo(10,1));
		
		//Creamos dos iteradores independientes con el método definido en ComplejoBox
		ComplejoBox.Iterator firstIterator = compBox.getIterator();
		ComplejoBox.Iterator secondIterator = compBox.getIterator();
		
		//Recorremos el primer iterador
		firstIterator.first();
		while (!firstIterator.isDone()) {
			System.out.print(firstIterator.currentValue() + " ");
			firstIterator.next();
		}
		System.out.println();
		
		//Lo mismo pero con los dos iteradores simultaneamente
		firstIterator.first();
		secondIterator.first();
		//Para la prueba hacemos que el segundo iterador esté 2 posiciones por delante del primero
		secondIterator.next();
		secondIterator.next();
		while (!firstIterator.isDone()) {	//Se sale cuando el primer iterador ha terminado
			if (secondIterator.isDone()) {	//Solo mostramos lo del primer iterador
				System.out.println("Valor primer iterador: " + firstIterator.currentValue() + 
						"\tEl segundo iterador ha terminado");
			} 
			else {	//Mostramos ambos iteradores
				System.out.println("Valor primer iterador: " + firstIterator.currentValue() + 
						"\tValor segundo iterador: " + secondIterator.currentValue());
			}
			firstIterator.next();
			secondIterator.next();
		}
		
	}
}
