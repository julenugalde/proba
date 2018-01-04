package eus.julenugalde.thinkinginjava.designpatterns;

public class Info {
	public TrashType trashType;
	public double weight;
	public double data;
	
	public Info(TrashType trashType, double weight, double data) {
		this.trashType = trashType;
		this.data = data;
		this.weight = weight;
	}
}
