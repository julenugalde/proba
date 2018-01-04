package eus.julenugalde.sandbox.designpatterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
	private static final Map<CharacterType, Organism> characterMap = 
			new HashMap<CharacterType, Organism>();
	
	static {
		characterMap.put(CharacterType.RICK, new Rick("C-131"));
		characterMap.put(CharacterType.MORTY, new Morty("C-131"));
		characterMap.put(CharacterType.BETH, new Beth("C-131"));
		characterMap.put(CharacterType.SUMMER, new Summer("C-131"));
		characterMap.put(CharacterType.JERRY, new Jerry("C-131"));
	}
	
	public static Organism getClone(CharacterType type) {
		try {
			return characterMap.get(type).clone();
		} catch (NullPointerException npex) {
			System.err.println("The character " + type + " doesn't exist");
			return null;
		}
	}
	
	public static int getNumCharacters() {
		return characterMap.size();
	}
}
