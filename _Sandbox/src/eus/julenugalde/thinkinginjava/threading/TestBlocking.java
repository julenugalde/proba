package eus.julenugalde.thinkinginjava.threading;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class TestBlocking extends JFrame {
	public TestBlocking() {
		this.setLayout(new GridLayout(5,1));
		Blockable sleeper1 = new Sleeper1(this);
		Blockable sleeper2 = new Sleeper2(this);
		sleeper1.start();
		sleeper2.start();
	}
	
	public static void main(String[] args) {
		TestBlocking window = new TestBlocking();
		window.setTitle("Test blocking");
		window.setSize(500, 200);
		window.setLocation(300, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
