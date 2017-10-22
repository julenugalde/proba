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
}