package eus.julenugalde.thinkinginjava.complexity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FieldBeastsUI extends JFrame {
	private FieldOfBeasts field;
	private JButton jbStart;
	private JTextField jtfNumberBeasts;
	private JTextField jtfMaxSpeed;
	
	public FieldBeastsUI() {
		field = new FieldOfBeasts();
		jbStart = new JButton("Start");
		jtfNumberBeasts = new JTextField(String.valueOf(FieldOfBeasts.numBeasts));
		jtfMaxSpeed = new JTextField(String.valueOf(FieldOfBeasts.maxSpeed));
		
		jbStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer i = Integer.parseInt(jtfNumberBeasts.getText());
					FieldOfBeasts.numBeasts = i;
				} catch (NumberFormatException nfex) {
					jtfNumberBeasts.setText(String.valueOf(FieldOfBeasts.numBeasts));
				}				
				try {
					Integer i = Integer.parseInt(jtfMaxSpeed.getText());
					FieldOfBeasts.maxSpeed = i;
				} catch (NumberFormatException nfex) {
					jtfMaxSpeed.setText(String.valueOf(FieldOfBeasts.maxSpeed));
				}
				field.start();
			}
		});
		
		JPanel jpControls = new JPanel(new GridLayout(15, 1, 5, 5));
		jpControls.setBorder(new EmptyBorder(5,5,5,5));
		jpControls.add(new JLabel("Number of beasts:"));
		jpControls.add(jtfNumberBeasts);
		jpControls.add(new JLabel("Maximum speed:"));
		jpControls.add(jtfMaxSpeed);
		jpControls.add(jbStart);
		
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(field, BorderLayout.CENTER);
		add(jpControls, BorderLayout.EAST);
		
		setSize(640+80, 480);
		setTitle("Field of beasts");
	}
	
	public static void main(String[] args) {
		FieldBeastsUI window = new FieldBeastsUI();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
