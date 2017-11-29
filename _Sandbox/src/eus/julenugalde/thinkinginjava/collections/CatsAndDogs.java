package eus.julenugalde.thinkinginjava.collections;

import java.util.ArrayList;
import java.util.Vector;

class Cat {
	private int catNumber;
	Cat(int i) {
		catNumber = i;
	}
	void print() {
		System.out.println("Cat #" + catNumber);
	}
}

class Dog {
	private int dogNumber;
	Dog(int i) {
		dogNumber = i;
	}
	void print() {
		System.out.println("Dog #" + dogNumber);
	}
}

public class CatsAndDogs {
	public static void main(String[] args) {
		Vector<Cat> cats = new Vector<Cat>();
		//ArrayList<Cat> cats = new ArrayList<Cat>();
		for (int i=0; i<7; i++) {
			cats.add(new Cat(i));
		}
		
		print(cats);
		cats.remove(3);
		cats.remove(4);
		cats.add(new Cat(7));
		cats.add(new Cat(8));
		cats.remove(0);
		cats.add(new Cat(9));
		print(cats);
	}
	
	public static void print (Vector<Cat> cats) {
		System.out.println("Cat list as a vector. " + cats.size() + " cats in total");
		for (int i=0; i<cats.size(); i++) {
			if (cats.get(i) != null) {
				cats.get(i).print();
			}
			else {
				System.out.println("There's no cat at #" + i);
			}
		}
	}
	
	public static void print (ArrayList<Cat> cats) {
		System.out.println("Cat list as an array list. " + cats.size() + " cats in total");
		for (Cat cat : cats) {
			cat.print();
		}
	}
}
