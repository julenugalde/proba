package eus.julenugalde.sandbox.designpatterns.strategy;

/** Clase abstracta que esconde la implementación */
public abstract class StrategySolution implements Strategy {

	@Override
	public void solve() {
		start();
		while (nextTry() && !isSolution()) {}
		stop();
	}
	
	abstract void start();
	abstract boolean nextTry();
	abstract boolean isSolution();
	abstract void stop();
}
