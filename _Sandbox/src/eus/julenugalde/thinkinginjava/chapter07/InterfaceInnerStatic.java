package eus.julenugalde.thinkinginjava.chapter07;

public interface InterfaceInnerStatic {
	public int metodo(String cadena, int numero);
	
	static class Inner {
		int i, j, k;
		public Inner(int a, int b, int c) {
			i=a;
			j=b;
			k=c;
		}
		long funcion() { return i*j*k;}
	}
}
