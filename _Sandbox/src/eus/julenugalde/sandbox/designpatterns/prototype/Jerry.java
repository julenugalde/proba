package eus.julenugalde.sandbox.designpatterns.prototype;

public class Jerry extends Organism {

	Jerry(String dimension) {
		super(dimension);
	}

	@Override
	public Organism clone() {
		return new Jerry(getRandomDimension());
	}

}
