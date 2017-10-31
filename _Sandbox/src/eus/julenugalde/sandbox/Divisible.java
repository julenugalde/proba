package eus.julenugalde.sandbox;

public interface Divisible {
	public Divisible dividir (Divisible denominador) throws ArithmeticException;
	public Divisible inverso() throws ArithmeticException;
}
