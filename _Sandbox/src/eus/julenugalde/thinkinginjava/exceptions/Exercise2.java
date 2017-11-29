package eus.julenugalde.thinkinginjava.exceptions;

public class Exercise2 {

	public static void main(String[] args) {
		try {
			throw new Exercise2Exception("problem found");
		}
		catch (Exercise2Exception e) {
			e.printMessage();
		}

	}

}
