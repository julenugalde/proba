package eus.julenugalde.sandbox.designpatterns.visitor;

public class ClaseC implements VisitableElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getClaseC() {
		return "objeto clase C";
	}
}
