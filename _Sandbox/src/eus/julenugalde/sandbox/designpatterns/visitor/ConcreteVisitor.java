package eus.julenugalde.sandbox.designpatterns.visitor;

public class ConcreteVisitor implements Visitor {

	@Override
	public void visit(Object objeto) {
		if (objeto instanceof ClaseA) {
			System.out.println("Visitando a " + ((ClaseA)objeto).getClaseA());
		}
		else if (objeto instanceof ClaseB) {
			System.out.println("Visitando a " + ((ClaseB)objeto).getClaseB());			
		}
		else if (objeto instanceof ClaseC) {
			System.out.println("Visitando a " + ((ClaseC)objeto).getClaseC());
		}		
		else {
			System.err.println("Clase desconocida");
		}		
	}

}
