package eus.julenugalde.thinkinginjava.threading;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Interrupt extends JFrame {
	private JButton jbInterrupt;
	private Blocked blocked;
	
	public Interrupt() {
		setTitle("Test interrupt()");
		setSize(200, 100);
		setLocation(300, 100);
		
		jbInterrupt = new JButton("Interrupt");
		jbInterrupt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed");
				if (blocked == null) return;
				Thread remove = blocked;
				remove.interrupt();
				blocked = null;
			}
		});
		add(jbInterrupt);
		
		blocked = new Blocked();
		blocked.start();
		
	}
	
	public static void main(String[] args) {
		Interrupt window = new Interrupt();
		window.setVisible(true);
	}
}


class Blocked extends Thread {

	@Override
	public synchronized void run() {
		try {
			System.out.println("Thread will be blocked");
			wait();
		} catch (InterruptedException iex) {
			System.err.println("InterruptedException @ Blocked: " + iex.getLocalizedMessage());
		}
		System.out.println("Exiting Blocked.run()...");
	}
	
}