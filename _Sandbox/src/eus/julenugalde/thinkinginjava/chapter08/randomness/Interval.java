package eus.julenugalde.thinkinginjava.chapter08.randomness;

public class Interval {
	double minValue;
	double maxValue;
	public Interval(double min, double max) {
		minValue = min;
		maxValue = max;
	}
	
	public boolean isIncluded(double number) {
		return ((number >= minValue) & (number < maxValue));
	}
	
	public String toString() {
		return "[" + String.format("%.4f", minValue) + "," + String.format("%.4f", maxValue) +
				")";
	}
	
}
