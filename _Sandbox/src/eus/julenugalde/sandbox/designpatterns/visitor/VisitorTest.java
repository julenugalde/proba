package eus.julenugalde.sandbox.designpatterns.visitor;

public class VisitorTest {

	public static void main(String[] args) {
		VisitableElement[] elementos = {
			new ClaseA(),
			new ClaseB(),
			new ClaseC(),
			new ClaseB()
		};
		
		ConcreteVisitor cv = new ConcreteVisitor();
		AnotherConcreteVisitor acv = new AnotherConcreteVisitor();
		
		for (VisitableElement elemento : elementos) {
			elemento.accept(cv);
			elemento.accept(acv);			
		}
	}
}
