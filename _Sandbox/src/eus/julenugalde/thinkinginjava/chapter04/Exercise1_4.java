package eus.julenugalde.thinkinginjava.chapter04;

import java.util.Random;

public class Exercise1_4{
	public Exercise1_4 () {
		System.out.println("Object created");
	}
	
	public Exercise1_4(String s) {
		System.out.println("Object created: " + s);
	}
	
	@SuppressWarnings("unused")
	public static void main (String[] args) {
		Exercise1_4 obj = new Exercise1_4();
		obj = new Exercise1_4("other constructor");
		
		Random rand = new Random();
		Exercise1_4[] objs = new Exercise1_4[10];
		for (Exercise1_4 i : objs) {
			i = new Exercise1_4("number " + rand.nextInt(100));
		}
	}	
}
