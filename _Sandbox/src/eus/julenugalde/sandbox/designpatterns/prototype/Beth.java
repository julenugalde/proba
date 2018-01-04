package eus.julenugalde.sandbox.designpatterns.prototype;

public class Beth extends Organism {

	Beth(String dimension) {
		super(dimension);
	}

	@Override
	public Organism clone() {
		return new Beth(getRandomDimension());
	}

}
