package eus.julenugalde.sandbox.jerarquiaclases;
public class A {
	protected String x;
	protected static int numStatic;
	
	static {
		numStatic = 18;
		System.out.println("Metodo static inicializado");
	}
	
	public A() {
		x = "Variable x en clase A";
	}
	
	public String getX () {
		return "X de A: " + x;
	}
	
	public int getNumStatic() { return numStatic;}
	
	public void metodoA() {
		System.out.println("Metodo A ejecutado");
	}
	
	public void metodoRedef() {
		System.out.println("Metodo redefinido por la clase A");
	}
}