package eus.julenugalde.thinkinginjava.threading;

import java.awt.GridLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TestBlocking extends JFrame {
	public TestBlocking() {
		setTitle("Test blocking");
		setSize(500, 200);
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(5,1));
		//Blockable blockable1 = new Sleeper1(this);
		//Blockable blockable2 = new Sleeper2(this);
		//Blockable blockable1 = new SuspendResume1(this);
		//Blockable blockable2 = new SuspendResume2(this);
		Blockable blockable1 = new WaitNotify1(this);
		Blockable blockable2 = new WaitNotify2(this);
		
		blockable1.start();
		blockable2.start();
	}
	
	public static void main(String[] args) {
		TestBlocking window = new TestBlocking();
		window.setVisible(true);
		
	}
}
