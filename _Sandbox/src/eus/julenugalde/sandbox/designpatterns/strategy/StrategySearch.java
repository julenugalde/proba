package eus.julenugalde.sandbox.designpatterns.strategy;

public abstract class StrategySearch implements Strategy {
	@Override
	public void solve() {
		while (true) {
			preProcess();
			if (search()) {
				break;
			}
			postProcess();
		}
	}
	
	public abstract void preProcess();
	public abstract boolean search();
	public abstract void postProcess();
}
