package eus.julenugalde.thinkinginjava.chapter08;

import java.util.Vector;
import eus.julenugalde.thinkinginjava.chapter07.*;

public class Exercise5 {
	public static void main (String[] args) {
		Vector<Rodent> rodents = new Vector<Rodent>();
		rodents.add(new Mouse());
		rodents.add(new eus.julenugalde.thinkinginjava.chapter07.Gerbil());
		rodents.add(new eus.julenugalde.thinkinginjava.chapter07.Gerbil());
		rodents.add(new Hamster());
		rodents.add(new Mouse());
		
		for (Rodent r : rodents) {
			r.gnaw();
		}
	}
}
