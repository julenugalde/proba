package eus.julenugalde.sandbox.designpatterns.prototype;

public class Rick extends Organism {
	Rick(String dimension) {
		super(dimension);
	}
	
	@Override
	public Organism clone() {
		return new Rick(getRandomDimension());
	}
}
