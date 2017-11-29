package eus.julenugalde.thinkinginjava.collections;

import java.util.BitSet;

public class TestFlags {
	
	public static void main(String[] args) {
		int NUM_MAX = 5000;
		BitSet flags = new BitSet(NUM_MAX);
		for (int i=0; i<NUM_MAX; i++) {
			if (isPrime(i)) flags.set(i);
		}
		
		print(flags);
	}
	
	public static boolean isPrime(int number) {
		boolean isPrime = true;
		int temp;
		if ((number == 0) || (number == 1)) return false;
		for(int i=2; i <= number/2; i++)
		{
	       temp=number%i;
		   if(temp==0)
		   {
		      isPrime=false;
		      break;
		   }
		}
		return isPrime;
	}
	
	public static void print(BitSet flags) {
		System.out.println("Flags\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
		int decena = 0;
		int unidad = 0;
		System.out.print("0\t");
		for (int i=0; i < flags.length(); i++) {
			if (flags.get(i)) System.out.print("[X]\t");
			else System.out.print("[ ]\t");
			if (++unidad == 10) {
				unidad = 0;
				System.out.print("\n" + (++decena) + "\t");
			}			
		}
	}
}
