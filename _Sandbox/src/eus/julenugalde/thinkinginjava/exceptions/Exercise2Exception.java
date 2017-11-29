package eus.julenugalde.thinkinginjava.exceptions;

@SuppressWarnings("serial")
public class Exercise2Exception extends Exception {
	private String message;
	
	public Exercise2Exception() {
		super();
		message = "";
	}
	
	public Exercise2Exception (String message) {
		super();
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println(message);
	}
}
