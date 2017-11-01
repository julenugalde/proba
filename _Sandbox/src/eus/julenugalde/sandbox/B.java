package eus.julenugalde.sandbox;
public class B extends A {
	private String x;
	ClaseInterna interna;
		
	B() {
		setX("Variable x en clase B");
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
	
	/**
	 * @return the x
	 */
	public String getX() {
		return "X de B: " + x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
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

