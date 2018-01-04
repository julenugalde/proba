package eus.julenugalde.sandbox.designpatterns.prototype;

public class Summer extends Organism {

	Summer(String dimension) {
		super(dimension);
	}

	@Override
	public Organism clone() {
		return new Summer(getRandomDimension());
	}

}
