package eus.julenugalde.sandbox.designpatterns.observer;

/** Clase base a la que se acopla el subject */
public abstract class Observer {
	protected Subject subject;
	public abstract void update();	
}
