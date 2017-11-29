package eus.julenugalde.thinkinginjava.collections.randomness;

import java.util.Hashtable;
import java.util.Random;

public class TestRandomness {
	
	public static void main(String[] args) {
		int SAMPLE_NUMBER = 20000000;
		double MAX_VALUE = 10;
		int NUM_INTERVALS = 8;
		
		Interval[] intervals = new Interval [NUM_INTERVALS];
		for (int i=0; i<NUM_INTERVALS; i++) {
			intervals[i] = new Interval ((double)MAX_VALUE*((double)i/NUM_INTERVALS), 
					(double)(MAX_VALUE*((double)i+1)/NUM_INTERVALS));
			//System.out.println(i + ": " + intervals[i].toString());
		}
		Hashtable<Interval,Counter> distribution = new Hashtable<Interval,Counter>();
		Random random = new Random();
		Interval currentKey;
		double randomValue = 0;
		
		System.out.print("Calculating");
		for (int i=0; i<SAMPLE_NUMBER; i++) {
			currentKey = null;
			randomValue = MAX_VALUE * random.nextDouble();
			//System.out.print(i + " = " + randomValue);
			for (int j=0; j<NUM_INTERVALS; j++) {
				if (intervals[j].isIncluded(randomValue)) {
					currentKey = intervals[j];
					//System.out.print(". Found " + intervals[j].toString());
					break;
				}
			}
			if (currentKey != null) {
				if (distribution.get(currentKey) == null) {	//New key in the map
					distribution.put(currentKey, new Counter());
					//System.out.println(" not in map");
				}
				else {	//key already existed
					distribution.get(currentKey).increment();
					//System.out.println(" already in map " + distribution.get(currentKey).getValue() + " times so far");
				}
			}
			
			if ((i%1000000) == 0) System.out.print(".");
		}
		System.out.println();
		
		int[] values = new int[NUM_INTERVALS];
		for (int i=0; i<values.length; i++) {
			if (distribution.containsKey(intervals[i])) {
				values[i] = (distribution.get(intervals[i]).getValue());
			}
			else {
				values[i] = 0;
			}
		}
		
		double meanValue = (double)SAMPLE_NUMBER/NUM_INTERVALS;
		System.out.println("mean = " + (int)meanValue);
		for (int i=0; i<NUM_INTERVALS; i++) {
			System.out.println(intervals[i] + " --> " + values[i] + "\t(" + 
					((int)(values[i]- Math.floor(meanValue))) + ")");
		}
	}		
}
