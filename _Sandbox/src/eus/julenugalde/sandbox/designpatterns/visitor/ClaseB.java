package eus.julenugalde.sandbox.designpatterns.visitor;

public class ClaseB implements VisitableElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public String getClaseB() {
		return "objeto clase B";
	}

}
