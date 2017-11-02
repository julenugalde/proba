package eus.julenugalde.sandbox.empresa;

public enum PuestosEmpresa {
	DIRECTOR ("Director", 80000, 100000),
	JEFE_DEPARTAMENTO ("Jefe dpto.", 45000, 75000),
	TRABAJADOR ("Trabajador", 20000, 35000),
	BECARIO ("Becario    ", 10000, 12000);
	
	private String nombrePuesto;
	private int sueldoMinimo;
	private int sueldoMaximo;
	
	private PuestosEmpresa (String nombrePuesto, int sueldoMinimo, int sueldoMaximo) {
		this.nombrePuesto = nombrePuesto;
		this.sueldoMaximo = sueldoMaximo;
		this.sueldoMinimo = sueldoMinimo;
	}
	
	public void setSueldoMaximo (int nuevoSueldo) {
		this.sueldoMaximo = nuevoSueldo;
	}
	
	public double sueldoMedio() {
		return (this.sueldoMaximo+this.sueldoMinimo) / 2;
	}
	
	public String toString() {
		return nombrePuesto;
	}
}
