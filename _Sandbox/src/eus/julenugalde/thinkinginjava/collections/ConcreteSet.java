package eus.julenugalde.thinkinginjava.collections;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class ConcreteSet extends AbstractSet<Gerbil> {
	private HashSet<Gerbil> setGerbils;
	
	public ConcreteSet() {
		setGerbils = new HashSet<Gerbil>();
	}
	
	@Override
	public boolean add(Gerbil gerbil) {
		return setGerbils.add(gerbil);
	}
	
	@Override
	public Iterator<Gerbil> iterator() {
		return setGerbils.iterator();
	}

	@Override
	public int size() {
		return setGerbils.size();
	}
	
	public static void main(String[] args) {
		System.out.println("Testing a concrete set of gerbils");
		ConcreteSet setGerbils = new ConcreteSet();
		int gerbilNumber;
		Random rand = new Random();
		for (int i=0; i<50; i++) {
			gerbilNumber = rand.nextInt(100);
			if (setGerbils.add(new Gerbil(gerbilNumber)))
				System.out.println("Gerbil with ID #" + gerbilNumber + " added");
			else
				System.err.println("Gerbil with ID #" + gerbilNumber + " already existed");
		}
		Iterator<Gerbil> iterator = setGerbils.iterator();
		System.out.println("Set size: " + setGerbils.size());
		while (iterator.hasNext())
			System.out.print(iterator.next().toString() + ", ");
		
	}

}
