package eus.julenugalde.thinkinginjava.gui;

import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestTraversalPolicy extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String[] labels = {
			"Treecko", "Grovyle", "Sceptile", "Torchic", "Combusken", "Blaziken", 
			"Mudkip", "Mashtomp", "Swampert", "Poochyena", "Mightyena", "Zigzagoon", 
			"Linoone", "Wurmple", "Cascoon", "Dustox", "Silcoon", "Beautifly", "Seedot", 
			"Nuzleaf", "Shiftry", "Lotad", "Lombre", "Ludicolo", "Taillow", "Swellow", 
			"Wingull", "Pelipper", "Ralts", "Kirlia", "Gardevoir", "Surskit", "Masquerain", 
			"Shroomish", "Breloom", "Slakoth", "Vigoroth", "Slaking"};
	
	private JButton[] buttons;
	
	public static void main(String[] args) {
		TestTraversalPolicy window = new TestTraversalPolicy();
		window.setTitle("Test of trasversal focus policy");
		window.setFocusTraversalPolicy(new AlphabeticPolicy());
		window.getContentPane().setBackground(SystemColor.window);
		window.setSize(400, 500);
		window.setVisible(true);
	}

	public TestTraversalPolicy() {
		createComponents();
		placeComponents();
		SystemColor.desktop.getRGB();
	}
	
	private void placeComponents() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		for (JButton button : buttons) {
			this.add(button);
		}
		
	}

	private void createComponents() {
		buttons = new JButton[labels.length];
		for (int i=0; i<buttons.length; i++) {
			buttons[i] = new JButton(labels[i]);
			buttons[i].setBackground(SystemColor.control);
			buttons[i].setForeground(SystemColor.textText);
		}
		
	}
}
