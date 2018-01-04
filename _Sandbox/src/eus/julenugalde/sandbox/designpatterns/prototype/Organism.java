package eus.julenugalde.sandbox.designpatterns.prototype;

public abstract class Organism {
	private String dimension;
	
	public abstract Organism clone();
	
	public String toString() {
		return this.getClass().getSimpleName() + " from dimension " + dimension;
	}
	
	protected Organism(String dimension) {
		this.dimension = dimension;
	}
	
	protected String getRandomDimension() {
		String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return letter.charAt((int)(Math.random() * letter.length())) + 
			"-" + (int)(Math.random() * 1000);
	}
}
