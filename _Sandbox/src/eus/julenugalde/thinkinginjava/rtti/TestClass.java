package eus.julenugalde.thinkinginjava.rtti;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TestClass {
	public static void main (String[] args) {
		System.out.println("main en marcha");
		Clase1 obj = new Clase1();
		System.out.println("Objeto Clase 1 creado - " + obj.toString());
		try {
			//Class<?> objClassClase2 = Class.forName("eus.julenugalde.thinkinginjava.rtti.Clase2");
			Class<?> objClassClase2 = Clase2.class;
			System.out.println("Clase2 cargada con Class.forName() - " + objClassClase2.getName());
			Method[] metodos = objClassClase2.getDeclaredMethods();
			Parameter[] parametros;
			for (Method m : metodos) {
				parametros = m.getParameters();
				System.out.print(m.getName()  + "(");
				for (Parameter p : parametros) {
					System.out.print(p.getType().getName() + " " + p.getName() + ", ");
				}
				System.out.println(") : " + m.getReturnType().getName());
			}
		} /*catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		}*/
		finally {}
	}
}

class Clase1 {
	static {
		System.out.println("Cargando Clase 1");
	}
}

class Clase2 {
	int i=0;
	static {
		System.out.println("Cargando Clase 2");
	}
	public void print() {
		System.out.println("Metodo publico Clase2");
	}
	public Clase2() {}
	public void doNothing(int argumento1, double argumento2, String argumento3) {}
	public String getString() {return "";}
}