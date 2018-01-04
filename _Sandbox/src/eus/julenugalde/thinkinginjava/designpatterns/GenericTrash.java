package eus.julenugalde.thinkinginjava.designpatterns;

public class GenericTrash extends Trash {

	protected GenericTrash(Info info) {
		super(info);
	}

	protected GenericTrash() {
		super(new Info(TrashType.GENERIC, 0, 0));
	}

	@Override
	double getValue() {
		return 0;
	}

	@Override
	Trash accept(Info info) {
		return new GenericTrash(info);
	}

}
