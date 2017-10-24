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
	public double getArgumento () {return Math.atan(imag/real);}
	
	public static Complejo sumar(Complejo comp1, Complejo comp2) {
		return new Complejo (comp1.real+comp2.real, comp1.imag+comp2.imag);
	}
	
	public void sumar (Complejo comp) {
		this.real += comp.real;
		this.imag += comp.imag;
	}
	
	@Override
	public String toString() {return real + " + " + imag + "i";}

	/**
	 * Implementación del interface Comparable<Complejo>. Compara dos números complejos en base al módulo y, en caso de igualdad, la parte real
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
}
