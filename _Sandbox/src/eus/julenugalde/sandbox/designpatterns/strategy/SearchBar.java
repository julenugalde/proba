package eus.julenugalde.sandbox.designpatterns.strategy;

/** Implementacion concreta de la estrategia search */
public class SearchBar extends StrategySearch {
	private int state = 1;
	
	@Override
	public void preProcess() {
		System.out.print("SearchBar start... ");
	}

	@Override
	public boolean search() {
		int solution = 5;	//Por ejemplo
		if (state == solution) {
			System.out.println("Search-" + state + " found");
			return true;
		}
		else {
			System.out.print("Search-" + state++ + " not found... ");
			return false;
		}
	}

	@Override
	public void postProcess() {
		System.out.println("SearchBar stop");
	}
}
