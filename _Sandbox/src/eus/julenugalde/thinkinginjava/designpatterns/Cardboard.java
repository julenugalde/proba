package eus.julenugalde.thinkinginjava.designpatterns;

public class Cardboard extends Trash {
	private static double value = 0.35f;
	
	protected Cardboard(Info info) {
		super(info);
	}

	protected Cardboard() {
		super(new Info(TrashType.CARDBOARD, 0, 0));
	}

	@Override
	double getValue() {
		return value;
	}

	public static void setValue(double newValue) {
		value = newValue;
	}

	@Override
	Trash accept(Info info) {
		return new Cardboard(info);
	}
}
