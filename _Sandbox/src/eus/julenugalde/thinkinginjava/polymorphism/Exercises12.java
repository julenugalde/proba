package eus.julenugalde.thinkinginjava.polymorphism;

public class Exercises12 {
	public static void main (String[] args) {
		Rodent[] rodents = new Rodent[5];
		rodents[0] = new Mouse();
		rodents[1] = new Gerbil();
		rodents[2] = new Gerbil();
		rodents[3] = new Hamster();
		rodents[4] = new Mouse();
		
		for (Rodent r : rodents) {
			r.gnaw();
		}
	}
}
