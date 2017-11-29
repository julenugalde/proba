package eus.julenugalde.thinkinginjava.polymorphism;

public class Clase {
    
    private class ClaseInterna1 extends ClaseAbstracta {
		@Override
		public void metodo() {
			System.out.println("metodo de la clase interna 1");
			
		}
        
    }
    protected class ClaseInterna2 implements Interfaz {
        public String metodoInterfaz() {
        	return "metodo interfaz implementado por clase interna 2";
        }
    }

    public static void main(String[] args) {
        Clase objeto = new Clase();
        Clase.ClaseInterna1 obj1 = objeto.new ClaseInterna1();
        obj1.metodo();
        Clase.ClaseInterna2 obj2 = objeto.new ClaseInterna2();
        System.out.println(obj2.metodoInterfaz());
    }
}
