package eus.julenugalde.sandbox.designpatterns.visitor;

public class AnotherConcreteVisitor implements Visitor {

	@Override
	public void visit(Object objeto) {
		if (objeto instanceof ClaseA) {
			System.out.println("Volviendo a visitar a " + ((ClaseA)objeto).getClaseA());
		}
		else if (objeto instanceof ClaseB) {
			System.out.println("Volviendo a visitar a " + ((ClaseB)objeto).getClaseB());			
		}
		else if (objeto instanceof ClaseC) {
			System.out.println("Volviendo a visitar " + ((ClaseC)objeto).getClaseC());
		}		
		else {
			System.err.println("Clase desconocida");
		}		
	}
}
