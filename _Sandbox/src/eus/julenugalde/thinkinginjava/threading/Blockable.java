package eus.julenugalde.thinkinginjava.threading;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

public class Blockable extends Thread {
	private Peeker peeker;
	protected JTextField state;
	protected int i;
	
	public Blockable(Container container) {
		state = new JTextField(40);
		state.setBackground(Color.CYAN);
		container.add(state);
		peeker = new Peeker(this, container);		
	}
	
	public synchronized int read() {
		return i;
	}
	
	protected synchronized void update() {
		state.setText(getClass().getName() + " state: i = " + i);
	}
	
	public void stopPeeker() {
		peeker.terminate();
	}	
}
