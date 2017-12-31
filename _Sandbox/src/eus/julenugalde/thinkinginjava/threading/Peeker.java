package eus.julenugalde.thinkinginjava.threading;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JTextField;

public class Peeker implements Runnable {
	private Blockable blockable;
	private int session;
	private JTextField status;
	private boolean stop = false;
	
	public Peeker(Blockable b, Container c) {
		status = new JTextField(40);
		status.setBackground(new Color(240, 70, 70));
		c.add(status);
		this.blockable = b;
		new Thread(this).start();
	}
	
	public void terminate() {
		stop = true;
	}

	@Override
	public void run() {
		while(!stop) {
			status.setText(blockable.getClass().getName() + " Peeker " + (++session) + 
					"; value=" + blockable.read());
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException iex) {
				System.err.println("Exception@Peeker.run(): " + iex.getLocalizedMessage());
			}
		}
	}

}
