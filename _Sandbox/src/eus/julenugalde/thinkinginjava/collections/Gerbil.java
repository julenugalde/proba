package eus.julenugalde.thinkinginjava.collections;

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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Gerbil)) return false;
		Gerbil g = (Gerbil)obj;
		return (g.gerbilNumber == this.gerbilNumber);
	}
	
	@Override
	public int hashCode() {
		return gerbilNumber;
	}
}
