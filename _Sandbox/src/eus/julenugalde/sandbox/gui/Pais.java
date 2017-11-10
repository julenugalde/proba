package eus.julenugalde.sandbox.gui;

public class Pais {	
	private String code;
	private String name;
	
	public Pais(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Pais() {
		this ("", "");
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
