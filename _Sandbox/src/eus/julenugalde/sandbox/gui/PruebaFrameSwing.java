package eus.julenugalde.sandbox.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PruebaFrameSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton b1;
	private JButton b2;
	private JButton b3;	
	private JButton b4;
	private JSlider s1;
	private JPanel panel;
	private JTextField tf1;
	private JLabel l1;
	
	private ActionListener listenerBotones;
	
	public PruebaFrameSwing (String titulo) {
		inicializarFrame(titulo);
		crearElementos();
		asignarListeners();	
		anadirElementosVentana();
	}
	
	private void anadirElementosVentana() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		b1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b1);
		b2.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b2);
		b3.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b3);
		b4.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b4);
		s1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(s1);
		tf1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(tf1);
		l1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(l1);
		this.add(panel);
		
	}

	private void asignarListeners() {
		listenerBotones = new ActionAdapter();
		b1.addActionListener(listenerBotones);
		b2.addActionListener(listenerBotones);
		b3.addActionListener(listenerBotones);
		b4.addActionListener(listenerBotones);
		s1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider origen = (JSlider)e.getSource();
				System.out.println("Nuevo valor: " + origen.getValue());
			}			
		});
		s1.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("Propiedad cambiada " + evt.getPropertyName() + ". " + 
					evt.getOldValue() + " -> " + evt.getNewValue());				
			}			
		});
		
		tf1.addKeyListener(new AdaptadorTeclas());

	}

	private void crearElementos() {
		//Botones que crean los dialogos
		b1 = new JButton("ConfirmDialog");
		b1.setName("confirmation");
		b2 = new JButton("InputDialog");
		b2.setName("input");
		b3 = new JButton("MessageDialog");
		b3.setName("message");
		b4 = new JButton("OptionsDialog");
		b4.setName("options");

		//slider
		s1 = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 50);
		s1.setMajorTickSpacing(10);
		s1.setMinorTickSpacing(1);
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		
		//campo de texto
		tf1 = new JTextField();		
		
		//label de estado
		l1 = new JLabel("xxxxxxxxxxxxx");
		l1.setName("status");
		
		//panel contenedor
		panel = new JPanel();
		//panel.setBackground(java.awt.Color.RED);
	}

	private void inicializarFrame(String titulo) {
		this.setTitle(titulo);
		this.setSize(500, 300);
		this.setLocation(100, 100);
		
		//Establecer el look&feel de la ventana
		UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
		int i = 3;	//Windows
		try {
			UIManager.setLookAndFeel(laf[i].getClassName());
			System.out.println("Look & Feel " + laf[i].getName());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}		
	}

	public static void main (String[] args) {
		PruebaFrameSwing ventana = new PruebaFrameSwing("prueba swing");
		ventana.setVisible(true);
		ventana.setEnabled(true);
		ventana.setResizable(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class AdaptadorTeclas extends KeyAdapter {
	@Override
	public void keyReleased(KeyEvent e) {
		//Convierte el texto a mayusculas
		JTextField origen = (JTextField)e.getSource();
		origen.setText(origen.getText().toUpperCase());
	}
}
