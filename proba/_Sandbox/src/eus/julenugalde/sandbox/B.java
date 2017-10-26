package eus.julenugalde.sandbox;
public class B extends A {
	public String x;
	ClaseInterna interna;
		
	B() {
		x = "Variable x en clase B";
	}
	public void metodoB() {
		System.out.println("Metodo B ejecutado");
	}
	
	public void metodoRedef() {
		System.out.println("Metodo redefinido por la clase B");
		ClaseInterna objetoInterno1 = new ClaseInterna ("Alice", 25);
		//ClaseInterna objetoInterno2 = new ClaseInterna ("Bob", 21);
		objetoInterno1.metodo();
	}
	
	class ClaseInterna {
		private String nombre;
		private int edad;
	
		ClaseInterna (String nombre, int edad) {
			this.nombre = nombre;
			this.edad = edad;
		}
		
		void metodo () {
			System.out.println(nombre + ", " + edad);			
		}
		
	}
}

