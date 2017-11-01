package eus.julenugalde.sandbox.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/** Clase que modela la funcionalidad "independiente" dentro del patrón Observer */
public class Subject {
	private List<Observer> observers;
	private int currentValue;
	
	public Subject() {
		observers = new ArrayList<Observer>();
		currentValue = 0;
	}
	
	/** Añade un observador que será notificado cuando el valor cambie 
	 * 
	 * @param observer Nuevo observador a añadir
	 */
	public void addObserver(Observer observer) {
		observers.add(observer);
		//System.out.println("La lista de observadores tiene " + observers.size() + " elementos");
	}
	
	/** Devuelve el valor actual de la variable observada
	 * 
	 * @return Valor de la variable observada
	 */
	public int getCurrentValue() {
		return currentValue;
	}
	
	/** Establece el valor de la variable observada y notifica a los observadores
	 * 
	 * @param value Valor a guardar
	 */
	public void setCurrentValue(int value) {
		currentValue = value;
		execute();
	}
	
	/** Notifica a los observadores que ha cambiado el valor de la variable 
	 */
	public void execute() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}

