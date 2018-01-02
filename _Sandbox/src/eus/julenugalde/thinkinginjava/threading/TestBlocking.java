package eus.julenugalde.thinkinginjava.threading;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TestBlocking extends JFrame {
	private JButton jbStart;
	private JButton jbStopPeekers;
	private Blockable[] blockables;
	private boolean flagStarted;
	private PipedReader in;
	private PipedWriter out;
	
	public TestBlocking() {
		flagStarted = false;
		out = new PipedWriter();
		try {
			in = new PipedReader(out);
		} catch (IOException ioex) {
			System.err.println("IOException@TestBlocking: " + ioex.getLocalizedMessage());
		}
		
		initializeWindow();
		createElements();
		defineLayout();
		addListeners();		
	}
	
	private void addListeners() {
		jbStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!flagStarted) {
					flagStarted = true;
					for (int i=0; i<blockables.length; i++) {
						blockables[i].start();
					}
				}
			}
		});
		
		jbStopPeekers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<blockables.length; i++) {
					blockables[i].stopPeeker();
				}				
			}
		});
	}

	private void defineLayout() {
		JPanel buttonsPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonsPane.add(jbStart);
		buttonsPane.add(jbStopPeekers);
				
		this.setLayout(new GridLayout(17,1));
		this.add(buttonsPane);
		blockables = new Blockable[] {
			new Sleeper1(this),
			new Sleeper2(this),
			new SuspendResume1(this),
			new SuspendResume2(this),
			new WaitNotify1(this),
			new WaitNotify2(this),
			new Sender(this, out),
			new Receiver(this, in)
		};
	}

	private void createElements() {
		jbStart = new JButton("Start");
		jbStopPeekers = new JButton("Stop peekers");
	}

	private void initializeWindow() {
		setTitle("Test blocking");
		setSize(700, 600);
		setLocation(300, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

	public static void main(String[] args) {
		TestBlocking window = new TestBlocking();
		window.setVisible(true);		
	}
}
