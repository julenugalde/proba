import java.util.Objects;

/** Clase para hacer pruebas de sobrecargas de métodos, implementación de interfaces... */
public class Complejo implements Comparable<Complejo> {
	/** Parte real */
	protected double real;
	/** Parte imaginaria */
	protected double imag;
	
	Complejo (double real, double imag) {
		this.real = real;
		this.imag = imag;		
	}
	
	Complejo () {
		this(0, 0);
	}
	
	Complejo (double real) {
		this (real, 0);
	}
	
	public double getReal () {return this.real;}
	public double getImag () {return this.imag;}
	public void setReal(double real) {this.real = real;}
	public void setImag (double imag) {this.imag = imag;}
	
	public double getModulo () {return Math.sqrt(real*real + imag*imag);}
	/**
	 * Devuelve el argumento en radianes
	 * @return Valor del argumento en radianes entre 0 (eje real positivo) y 2*PI
	 */
	public double getArgumento ()  {
		if (real == 0) {	//Si la parte real es 0, el número está sobre el eje imaginario
			if (imag == 0) return 0;	//En realidad habría que lanzar una ArithmeticException
			else if (imag > 0) return Math.PI/2;
			else return -Math.PI/2;
		}
		double salida = Math.atan(imag/real);
		if (real < 0) //estamos en la parte negativa del eje real (2º y 3er cuadrantes)
			salida += Math.PI;
		if (real >0 & imag < 0)
			salida += 2*Math.PI;
		
		return salida;
	}
	
	public static Complejo sumar(Complejo comp1, Complejo comp2) {
		return new Complejo (comp1.real+comp2.real, comp1.imag+comp2.imag);
	}
	
	public void sumar (Complejo comp) {
		this.real += comp.real;
		this.imag += comp.imag;
	}
	
	@Override
	public String toString() {
		return real + " + " + imag + "i";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Complejo))
			return false;
		if (obj == this)
			return true;
		
		Complejo comp = (Complejo) obj;
		return ((this.real == comp.real) & (this.imag == comp.imag));
	}
	
	@Override
	public int hashCode() {
		return (int) ((int)(553543 * this.real) + (321545 * this.imag));
	}
	
	/**
	 * Implementación del interface Comparable<Complejo>. Compara dos números complejos en base al módulo y, 
	 * en caso de igualdad, la parte real
	 * @param comp	Número a comparar
	 * @return Devuelve 0 si los números son iguales, 
	 * @throws NullPointerException 
	 */
	@Override
	public int compareTo(Complejo comp) {
		if (comp == null) throw new NullPointerException();
		//Comparamos partes reales e imaginarias
		if ((this.real == comp.real) & (this.imag == comp.imag))
			return 0;
		//Si son distintos, se compara primero el módulo
		if (this.getModulo() == comp.getModulo()) {
			return Double.compare(this.real, comp.real);
		}
		else //Los modulos son distintos
			return Double.compare(this.getModulo(), comp.getModulo());
			
	}
	
	public static Complejo[] getRandomArray(int size, int seed, int modulo) {
		int a, b;
		int temp = seed;
		Complejo[] salida = new Complejo[size];
		for (int i=0; i<size; i++) {
			a = siguiente (temp, modulo);
			b = siguiente (a, modulo);
			temp = b;
			salida[i] = new Complejo (a, b);
		}
		return salida;
	}
	
	private static int siguiente (int anterior, int modulo) {;
		final int a = 1664525;
		final int c = 1013904223;
		return (a*anterior + c) % modulo;
	}
}


