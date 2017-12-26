package eus.julenugalde.thinkinginjava.rtti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unused")
public class TestDowncast {
	static String[] nombresForma = {"Circle", "Triangle", "Square", "Elipse", "Rectangle"};
	
	public static void main(String[] args) {
		//testBasico();
		//testForName();
		testClassLiterals();
		
		Elipse elipse = new Elipse();
		Class<?> objClass = Circle.class;
		if (objClass.isInstance(elipse)) 
			System.out.println("si");
		else 
			System.out.println("no");
		Class<?> clase = elipse.getClass();
		if (clase.isInterface()) System.out.println("interface");
		else System.out.println("no interface");
	}

	private static void testClassLiterals() {
		try {
			Class<?>[] tiposForma = {
					Circle.class,
					Triangle.class,
					Square.class,
					Elipse.class,
					Rectangle.class
			};
			
			ArrayList<Shape> formas = new ArrayList<Shape>();
			int valorAleatorio;
			for (int i=0; i<100; i++) {
				valorAleatorio = (int)(Math.random()*tiposForma.length);
				formas.add((Shape) tiposForma[valorAleatorio].newInstance());
			}
			
			HashMap<String,Counter> mapa = new HashMap<String,Counter>();
			for (Class<?> c : tiposForma) {
				mapa.put(c.toString(), new Counter());
			}
			
			Iterator<Shape> iteradorFormas = formas.iterator();
			Shape actual;
			while (iteradorFormas.hasNext()) {
				actual = iteradorFormas.next();
				for (Class<?> c : tiposForma) {
					if (c.isInstance(actual)) {
						((Counter)mapa.get(c.toString())).increment();
					}
				}
			}
			
			Set<String> claves = mapa.keySet();
			for (String s : claves) {
				System.out.println(s + " - " + ((Counter)mapa.get(s)).getValue());
			}
		} catch (InstantiationException e) {
			System.err.println("Error de instanciacion");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Acceso ilegal");
			e.printStackTrace();
		}
	}

	private static void testForName() {
		try {
			Class<?>[] tiposForma = {
					Class.forName("eus.julenugalde.thinkinginjava.rtti.Circle"),
					Class.forName("eus.julenugalde.thinkinginjava.rtti.Triangle"),
					Class.forName("eus.julenugalde.thinkinginjava.rtti.Square"),
					Class.forName("eus.julenugalde.thinkinginjava.rtti.Elipse"),
					Class.forName("eus.julenugalde.thinkinginjava.rtti.Rectangle")
			};
			
			ArrayList<Shape> formas = new ArrayList<Shape>();
			int valorAleatorio;
			for (int i=0; i<10; i++) {
				valorAleatorio = (int)(Math.random()*tiposForma.length);
				formas.add((Shape) tiposForma[valorAleatorio].newInstance());
			}
			
			HashMap<Class<?>,Counter> mapa = new HashMap<Class<?>,Counter>();
			for (Class<?> c : tiposForma) {
				mapa.put(c, new Counter());
			}
			
			Iterator<Shape> iteradorFormas = formas.iterator();
			Shape actual;
			while (iteradorFormas.hasNext()) {
				actual = iteradorFormas.next();
				if (actual instanceof eus.julenugalde.thinkinginjava.rtti.Circle) {
					mapa.get(tiposForma[0]).increment();
				}
				if (actual instanceof eus.julenugalde.thinkinginjava.rtti.Triangle) {
					mapa.get(tiposForma[1]).increment();
				}
				if (actual instanceof eus.julenugalde.thinkinginjava.rtti.Square) {
					mapa.get(tiposForma[2]).increment();
				}
				if (actual instanceof eus.julenugalde.thinkinginjava.rtti.Elipse) {
					mapa.get(tiposForma[3]).increment();
				}
				if (actual instanceof eus.julenugalde.thinkinginjava.rtti.Rectangle) {
					mapa.get(tiposForma[4]).increment();
				}
			}
			
			Set<Class<?>> claves = mapa.keySet();
			for (Class<?> c : claves) {
				System.out.println(c.getName() + " - " + ((Counter)mapa.get(c)).getValue());
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		} catch (InstantiationException e) {
			System.err.println("Error de instanciacion");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("Acceso ilegal");
			e.printStackTrace();
		}
	}

	private static void testBasico() {
		Shape s1 = new Circle();
		Shape s2 = new Triangle();
		
		s1.draw();
		s2.draw();
		
		if (s1 instanceof Circle) {
			System.out.println("Circle radius=" + ((Circle)s1).getRadius());
		}
		else {
			System.out.println("Not a circle");
		}
		
		if (s2 instanceof Circle) {
			System.out.println("Circle radius=" + ((Circle)s2).getRadius());
		}
		else {
			System.out.println("Not a circle");
		}
	}

}

abstract class Shape {
	public abstract void draw();
}

class Circle extends Elipse {
	@Override
	public void draw() {
		System.out.println("Circle drawn");
	}
	public double getRadius() {
		return 1;
	}
}

class Triangle extends Shape {
	@Override
	public void draw() {
		System.out.println("Triangle drawn");
	}
}

class Rectangle extends Shape {
	@Override
	public void draw() {
		System.out.println("Rectangle drawn");
	}
}

class Square extends Rectangle {
	@Override
	public void draw() {
		System.out.println("Square drawn");
	}
}

class Elipse extends Shape {
	@Override
	public void draw() {
		System.out.println("Elipse drawn");
	}
}

class Counter {
	int i=0;
	public void increment() { i++; }
	public int getValue() { return i; }
}