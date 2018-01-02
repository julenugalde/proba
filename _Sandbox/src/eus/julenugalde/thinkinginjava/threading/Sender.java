package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;
import java.io.IOException;
import java.io.Writer;

public class Sender extends Blockable {
	private Writer out;
	
	public Sender(Container container, Writer out) {
		super(container);
		this.out = out;
	}
	
	public void run() {
		while (true) {
			for (char c='A'; c<='z'; c++) {
				try {
					i++;
					out.write(c);
					state.setText("Sender sent '" + (char)c + "'");
					sleep((int)(3000 * Math.random()));
				} catch (InterruptedException iex) {
					System.err.println("InterruptedException@Sender: " + iex.getLocalizedMessage());
				} catch (IOException ioex) {
					System.err.println("IOException@Sender: " + ioex.getLocalizedMessage());
				}
			}
		}
	}

}
