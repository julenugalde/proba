package eus.julenugalde.sandbox;
public class A {
	protected String x;
	protected static int numStatic;
	
	static {
		numStatic = 18;
		System.out.println("Metodo static inicializado");
	}
	
	A() {
		x = "Variable x en clase A";
	}
	
	public String getX () {
		return x;
	}
	
	public int getNumStatic() { return numStatic;}
	
	public void metodoA() {
		System.out.println("Metodo A ejecutado");
	}
	
	public void metodoRedef() {
		System.out.println("Metodo redefinido por la clase A");
	}
}