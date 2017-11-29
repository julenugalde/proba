package eus.julenugalde.thinkinginjava.exceptions;

import java.util.Random;

public class Exercise3 {
	public static void main(String[] args) {
		Exercise3 obj = new Exercise3();
		
		try {
			obj.method();
		} catch (Exercise2Exception e) {
			e.printMessage();
			e.printStackTrace();
		}
	}
	
	public void method() throws Exercise2Exception {
		Random random = new Random();
		if (random.nextBoolean()) throw new Exercise2Exception("bad luck");
	}
}
