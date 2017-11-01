package eus.julenugalde.sandbox.designpatterns.observer;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Clase de test para probar el patrón de diseño observer.
 * Extraido de {@link https://sourcemaking.com/design_patterns/observer/java/1}
 */
public class ObserverTest {

	public static void main(String[] args) {
		Subject subject = new Subject();
		new BinObserver(subject);
		new OctObserver(subject);
		new HexObserver(subject);
		
		Scanner scanner = new Scanner(System.in);
		try {
			int valor;
			while (true) {
				System.out.print("\nIntroducir número entero --> ");
				valor = scanner.nextInt();
				subject.setCurrentValue(valor);
			}
		} catch (InputMismatchException e) {
			System.out.println("Fin");
			scanner.close();
		} 
	}

}
