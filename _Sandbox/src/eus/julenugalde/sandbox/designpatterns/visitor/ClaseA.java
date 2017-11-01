package eus.julenugalde.sandbox.designpatterns.visitor;

public class ClaseA implements VisitableElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public String getClaseA() {
		return "objeto clase A";
	}

}
