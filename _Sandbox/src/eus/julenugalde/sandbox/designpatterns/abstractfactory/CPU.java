package eus.julenugalde.sandbox.designpatterns.abstractfactory;

/** Clase abstracta para representar las CPUs de distintas familias */
public abstract class CPU {
	public String getComponentType() {return "CPU";}
	public abstract String getArchitectureName();
}
