package eus.julenugalde.thinkinginjava.chapter07;

public class ImplementadorInnerStatic implements InterfaceInnerStatic {

	@Override
	public int metodo(String cadena, int numero) {
		System.out.println("cadena: " + cadena);
		return numero + 10;
	}
	
	public static void main(String[] args) {
		InterfaceInnerStatic iis = new ImplementadorInnerStatic();
		
		iis.metodo("prueba", 5);
		InterfaceInnerStatic.Inner objInner = new InterfaceInnerStatic.Inner(1, 2, 3);
		System.out.println("objeto interno: " + objInner.funcion());
	}

}
