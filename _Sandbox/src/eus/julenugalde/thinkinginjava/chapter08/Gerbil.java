package eus.julenugalde.thinkinginjava.chapter08;

public class Gerbil {
	private int gerbilNumber;
	
	public Gerbil(int number) {
		gerbilNumber = number;
	}
	
	public void hop() {
		System.out.println("Gerbil #" + gerbilNumber + " is hopping");
	}
	
	@Override
	public String toString() {
		return "Gerbil #" + gerbilNumber;
	}
}
