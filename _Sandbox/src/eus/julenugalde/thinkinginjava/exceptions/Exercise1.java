package eus.julenugalde.thinkinginjava.exceptions;

public class Exercise1 {
	public static void main(String[] args) {
		try {
			throw new Exception("Exception thrown from main()");
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getLocalizedMessage());
		}
		finally {
			System.out.println("Finally statement executed");
		}
	}
}
