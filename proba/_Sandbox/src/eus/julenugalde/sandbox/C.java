package eus.julenugalde.sandbox;
public class C extends B {
	public String x;
	
	C() {
		x = "Variable x en clase C";
	}
	
	public void metodoPrueba () {
		System.out.println("x de C: " + x);
		System.out.println("x de B: " + super.x);
		System.out.println("x de A: " + ((A)this).x);
		
	}
	
	public void metodoC() {
		System.out.println("Metodo C ejecutado");
	}
	
	public void metodoRedef() {
		System.out.println("Metodo redefinido por la clase C");
	}
}