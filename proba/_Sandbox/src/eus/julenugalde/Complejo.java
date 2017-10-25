

/** Clase para hacer pruebas de sobrecargas de métodos, implementación de interfaces... */
public class Complejo implements Comparable<Complejo>, Divisible {
	/** Parte real */
	protected double real;
	/** Parte imaginaria */
	protected double imag;
	
	/**
	 * Constructor con dos argumentos
	 * @param real Parte real
	 * @param imag Parte imaginaria
	 */
	Complejo (double real, double imag) {
		this.real = real;
		this.imag = imag;		
	}
	
	/**
	 * Constructor por defecto, en el que se inicializa a 0+0i
	 */
	Complejo () {
		this(0, 0);
	}
	
	/**
	 * Constructor en el que solo se especifica la parte real
	 * @param real Parte real
	 */
	Complejo (double real) {
		this (real, 0);
	}
	
	public double getReal () {return this.real;}
	public double getImag () {return this.imag;}
	public void setReal(double real) {this.real = real;}
	public void setImag (double imag) {this.imag = imag;}
	
	/**
	 * Obtiene el modulo
	 * @return Modulo del número complejo
	 */
	public double getModulo () {
		return Math.sqrt(real*real + imag*imag);
	}
	
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
	
	/**
	 * Método estático para sumar dos número complejos
	 * @param comp1 Primer número complejo
	 * @param comp2 Segundo número complejo
	 * @return Suma de los dos argumentos
	 */
	public static Complejo sumar(Complejo comp1, Complejo comp2) {
		return new Complejo (comp1.real+comp2.real, comp1.imag+comp2.imag);
	}
	
	/**
	 * Método que suma un valor al número complejo
	 * @param comp Número complejo que se sumará
	 */
	public void sumar (Complejo comp) {
		this.real += comp.real;
		this.imag += comp.imag;
	}
	
	/**
	 * Sobrecarga del método toString(). Devuelve el número en coordenadas rectangulares
	 */
	@Override
	public String toString() {
		return "(" + real + "+" + imag + "i)";
	}
	
	/**
	 * Representación del número complejo en coordenadas polares
	 * @return Representación del número en el formato modulo<argumento, con el argumento
	 * en coordenadas polares en el rango [0, 2*PI)
	 */
	public String toStringPolar() {
		return "(" + this.getModulo() + "<" + this.getArgumento() + ")";
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
	 * Implementación del interface Comparable<Complejo>. Compara dos números complejos en base 
	 * al módulo y, en caso de igualdad, la parte real
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
	
	/**
	 * Método de prueba que devuelve un array aleatorio de números complejos
	 * @param size Tamaño del array de complejos
	 * @param seed Valor que se tomará como semilla para el generador de números aleatorios
	 * @param modulo Valor máximo para cada uno de los valores generados
	 * @return Array de números complejos aleatorios
	 */
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

	@Override
	public Divisible dividir(Divisible denominador) throws DivisorNuloException {
		Complejo num = (Complejo) this;
		Complejo den = (Complejo) denominador;
		
		if (den.real==0 & den.imag==0)
			throw new DivisorNuloException ("Division by zero");
		return new Complejo (
				((num.real*den.real) + (num.imag*den.imag))/((den.real*den.real) + (den.imag*den.imag)),
				((num.imag*den.real) - (num.real*den.imag))/((den.real*den.real) + (den.imag*den.imag))
				);
	}
	
	@Override
	public Divisible inverso() throws DivisorNuloException {
		Complejo numero = (Complejo)this;
		if (numero.real==0 & numero.imag==0)
			throw new DivisorNuloException ("Division by zero");
		return new Complejo (
				(numero.real/(numero.real*numero.real + numero.imag*numero.imag)),
				-(numero.imag/(numero.real*numero.real + numero.imag*numero.imag)));
	}
}


