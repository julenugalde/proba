package eus.julenugalde.sandbox.designpatterns.prototype;

public class Morty extends Organism {
	Morty(String dimension) {
		super(dimension);
	}

	@Override
	public Organism clone() {
		return new Morty(getRandomDimension());
	}
	
}