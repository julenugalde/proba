package eus.julenugalde.thinkinginjava.rtti;

import java.lang.reflect.*;

public class TestReflection {
	public static final String CLASE = "eus.julenugalde.sandbox.complejos.Complejo";
	
	public static void main(String[] args) {
		try {
			Class<?> className = Class.forName(CLASE);
			Method[] methods = className.getDeclaredMethods();
			Constructor<?>[] constructors = className.getDeclaredConstructors();
			Parameter[] parameters;
			
			System.out.println("Methods of '" + className.getName() + "': ");
			for (Method m : methods) {
				System.out.println(" + " + m.getName() + " : " + m.getReturnType().getSimpleName());
				parameters = m.getParameters();
				for (Parameter p : parameters) {
					System.out.println(" |- " + p.getName() + " : " + p.getType().getSimpleName());
				}
			}
			
			System.out.println("Constructors of '" + className.getName() + "': ");
			for (Constructor<?> c : constructors) {
				System.out.println(" + " + c.getName());
				parameters = c.getParameters();
				for (Parameter p : parameters) {
					System.out.println(" |- " + p.getName() + " : " + p.getType().getSimpleName());
				}
			}
			
		} catch (ClassNotFoundException cnfex) {
			System.err.println("Class not found: " + cnfex.getLocalizedMessage());
		}

	}

}
