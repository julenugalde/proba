package eus.julenugalde.sandbox.designpatterns.strategy;

public class StrategyTest {

	private static void execute(Strategy strategy) {
		strategy.solve();
	}
	
	public static void main(String[] args) {
		Strategy[] algorithms = {
				new SearchFoo(),
				new SearchBar(),
				new SolutionA(),
				new SolutionB()
		};
		
		for (Strategy algorithm : algorithms) {
			execute(algorithm);
		}
		
	}

}
