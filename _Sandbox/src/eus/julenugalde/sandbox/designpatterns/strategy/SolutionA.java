package eus.julenugalde.sandbox.designpatterns.strategy;

/** Implementacion del algoritmo solution */
public class SolutionA extends StrategySolution {
	private int state = 1;
	
	@Override
	void start() {
		System.out.print("StrategySolutionA start... ");
	}

	@Override
	boolean nextTry() {
		System.out.print("NextTry-" + state++ + "... ");
		return true;
	}

	@Override
	boolean isSolution() {
		int solution = 3;	//Por ejemplo
		if (state == solution) {
			System.out.print(state + " is solution... ");
			return true;
		}
		else {
			System.out.print(state  + " is not solution... ");
			return false;
		}		
	}

	@Override
	void stop() {
		System.out.println("StrategySolutionA stop");
	}
}
