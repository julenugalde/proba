package eus.julenugalde.sandbox.designpatterns.prototype;

public class TestPrototype {
	public static void main(String[] args) {
		CharacterType[] characters = CharacterType.values();
		int maxIndex = CharacterFactory.getNumCharacters();
		for (int i=0; i<50; i++) {
			CharacterType randomCharacter = characters[(int)(Math.random() * maxIndex)];
			Organism character = CharacterFactory.getClone(randomCharacter);
			System.out.println((i+1) + ": " + character.toString() + " (" + randomCharacter + ")");
		}
	}
}
