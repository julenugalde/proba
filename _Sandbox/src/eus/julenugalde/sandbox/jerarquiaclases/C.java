package eus.julenugalde.sandbox.jerarquiaclases;
public class C extends B {
	public String x;
	private int variablePrivada;
	
	public C() {
		x = "Variable x en clase C";
	}
	
	public void metodoPrueba () {
		System.out.println("x de C: " + x);
		System.out.println("x de B: " + super.getX());
		System.out.println("x de A: " + ((A)this).x);
		
	}
	
	public void metodoC() {
		System.out.println("Metodo C ejecutado");
	}
	
	public void metodoRedef() {
		System.out.println("Metodo redefinido por la clase C");
	}

	/**
	 * @return the variablePrivada
	 */
	public int getVariablePrivada() {
		return variablePrivada;
	}

	/**
	 * @param variablePrivada the variablePrivada to set
	 */
	public void setVariablePrivada(int variablePrivada) {
		this.variablePrivada = variablePrivada;
	}
}