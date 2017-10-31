package eus.julenugalde.sandbox;

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
	
	private ActionListener listenerBotones;
	
	public PruebaFrameSwing (String titulo) {
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
			
		//Crear los elementos
		b1 = new JButton("ConfirmDialog");
		b2 = new JButton("InputDialog");
		b3 = new JButton("MessageDialog");
		b4 = new JButton("OptionsDialog");
		s1 = new JSlider(SwingConstants.HORIZONTAL, 1, 100, 50);
		s1.setMajorTickSpacing(10);
		s1.setMinorTickSpacing(1);
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		panel = new JPanel();
		//panel.setBackground(java.awt.Color.RED);
		tf1 = new JTextField();
		
		//Añadir listeners
		listenerBotones = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton origen = (JButton)e.getSource();
				if (origen == b1) {	//dialogo de confirmacion
					int respuesta = JOptionPane.showConfirmDialog(
							panel, 						//parent component
							"Mensaje de confirmación",	//message 
							"Confirme, por favor",		//title
							JOptionPane.YES_NO_CANCEL_OPTION,	//option type
							JOptionPane.QUESTION_MESSAGE);
					switch (respuesta) {
					case JOptionPane.YES_OPTION:
						System.out.println("Ha pulsado si");
						break;
					case JOptionPane.NO_OPTION:
						System.out.println("Ha pulsado no");
						break;
					case JOptionPane.CANCEL_OPTION:
						System.out.println("Ha pulsado cancelar");
						break;
					default:
						System.err.println("Opcion desconocida");
					}
				}
				
				else if (origen == b2) {	//input dialog
					String[] opciones = {"A Game of Thrones", "A Clash of Kings", "A Storm of Swords",
							"A Feast for Crows", "A Dance with Dragong", "The Winds of Winter"};
					Object respuesta = JOptionPane.showInputDialog(
							panel,							//Parent component
							"Elija un libro:", 				//Mensaje
							"A Song of ice and fire", 		//Titulo de ventana de dialogo
							JOptionPane.QUESTION_MESSAGE, 	//Tipo de ventana
							null, 							//Icono
							opciones,						//Elementos entre los que elegir
							0);								//Seleccion inicial
					System.out.println("Se ha elegido el libro " + respuesta.toString());
				}
				
				else if (origen == b3) {	//message dialog
					String [] cadena = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +  
							"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 
							"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " + 
							"nisi ut aliquip ex ea commodo consequat. ", 
							"Duis aute irure dolor in reprehenderit in voluptate velit esse " + 
							"cillum dolore eu fugiat nulla pariatur. ", 
							"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " + 
							"officia deserunt mollit anim id est laborum."};
					JOptionPane.showMessageDialog(
							panel, 								//parent component
							cadena, 							//informacion a mostrar
							"Lorem ipsum", 						//titulo
							JOptionPane.INFORMATION_MESSAGE);	//tipo de mensaje
				}
				
				else if (origen == b4) {	//options dialog
					String[] opciones = {"A Game of Thrones", "A Clash of Kings", "A Storm of Swords",
							"A Feast for Crows", "A Dance with Dragong", "The Winds of Winter"};
					int respuesta = JOptionPane.showOptionDialog(
							panel, 								//padre
							"Elija una opción", 				//mensaje
							"A song of ice and fire", 			//titulo
							JOptionPane.DEFAULT_OPTION,		 	//tipo de opcion
							JOptionPane.QUESTION_MESSAGE, 		//tipo de mensaje
							null, 								//icono
							opciones, 							//opciones
							0);									//valor por defecto
					System.out.println("Se ha elegido " + opciones[respuesta]);
				}
				else 
					System.err.println("Boton pulsado desconocido");				
			}
		};
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
		
		//Añadir elementos a la ventana
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		b1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b1);
		b2.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b2);
		b3.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b3);
		b4.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(b4);
		panel.add(s1);
		panel.add(tf1);
		this.add(panel);
		
	}
	
	public static void main (String[] args) {
		PruebaFrameSwing ventana = new PruebaFrameSwing("prueba swing");
		ventana.setVisible(true);
		ventana.setEnabled(true);
		ventana.setResizable(true);
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
