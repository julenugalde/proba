package eus.julenugalde;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Clase de ejemplo para probar colecciones */
public class Empleado implements Comparable<Empleado> {
	public enum Puesto {DIRECTOR, JEFE_DEPARTAMENTO, TRABAJADOR, BECARIO};
	
	private String nombre;
	private Date fechaNacimiento;
	private int salario;
	private Puesto puesto;
	
	public Empleado (String nombre, Date fechaNacimiento, int salario, Puesto puesto) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.salario = salario;
		this.puesto = puesto;
	}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public Date getFechaNacimiento() {return fechaNacimiento;}
	public void setFechaNacimiento(Date fecha) {this.fechaNacimiento = fecha;}
	public int getSalario() {return salario;}
	public void setSalario(int salario) {this.salario = salario;}
	public Puesto getPuesto( ) {return puesto;}
	public void setPuesto(Puesto puesto) {this.puesto = puesto;}

	@Override
	public int compareTo(Empleado arg0) {
		Empleado aux = (Empleado) arg0;
		if (this.nombre.compareTo(aux.getNombre()) != 0)	//Primero se comparan nombres
			return this.nombre.compareTo(aux.getNombre());
		else {	//Después se compara fecha de nacimiento
			return this.fechaNacimiento.compareTo(aux.getFechaNacimiento());
		}
	}
	
	public String toString() {
		return "ID: " + this.hashCode() + " --> Nombre: " + nombre + ", fecha de nacimiento: " + 
				new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento) + ", salario: " + 
				salario  + "€, puesto: " + puesto.toString();
	}
	
}
