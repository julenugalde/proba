package eus.julenugalde.thinkinginjava.designpatterns;

public class Glass extends Trash {
	private static double value = 0.23f;
	
	protected Glass(Info info) {
		super(info);
	}
	
	protected Glass() {
		super(new Info(TrashType.GLASS, 0, 0));
	}

	@Override
	public double getValue() {
		return value;
	}
	
	public static void setValue(double newValue) {
		value = newValue;
	}

	@Override
	Trash accept(Info info) {
		return new Glass(info);
	}

}
