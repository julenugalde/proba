package eus.julenugalde.thinkinginjava.designpatterns;

public class Aluminium extends Trash {
	private static double value = 1.67f;
	
	protected Aluminium(Info info) {
		super(info);
	}
	
	protected Aluminium() {
		super(new Info(TrashType.ALUMINIUM, 0, 0));
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
		return new Aluminium(info);
	}

}
