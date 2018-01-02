package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;
import java.io.IOException;
import java.io.Reader;

public class Receiver extends Blockable {
	private Reader in;
	
	public Receiver(Container c, Reader in) {
		super(c);
		this.in = in;
	}
	
	public void run() {
		try {
			while(true) {
				i++;	//Show peeker it's alive
				state.setText("Receiver read '" + (char)in.read() + "'");
			}
		} catch (IOException ioex) {
			System.err.println("IOException@Receiver: " + ioex.getLocalizedMessage());
		}
	}
}
