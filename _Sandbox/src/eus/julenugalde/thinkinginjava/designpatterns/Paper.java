package eus.julenugalde.thinkinginjava.designpatterns;

public class Paper extends Trash {
	private static double value = 0.10f;
	
	protected Paper(Info info) {
		super(info);
	}
	
	protected Paper() {
		super(new Info(TrashType.PAPER, 0, 0));
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
		return new Paper (info);
	}

}
