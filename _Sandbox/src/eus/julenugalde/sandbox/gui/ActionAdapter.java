package eus.julenugalde.sandbox.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActionAdapter implements ActionListener {
	private JLabel estado = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton origen = (JButton)e.getSource();
		JPanel panel = (JPanel)origen.getParent();
		Component[] componentes = panel.getComponents();
		for (Component elemento : componentes) {
			if ((elemento != null) && (elemento instanceof JLabel)) {
				estado = (JLabel)elemento;
			}	
		}
		
		if (origen.getName().equals("confirmation")) {	//dialogo de confirmacion
			int respuesta = JOptionPane.showConfirmDialog(
					panel,		//parent component
					"Mensaje de confirmación",	//message 
					"Confirme, por favor",		//title
					JOptionPane.YES_NO_CANCEL_OPTION,	//option type
					JOptionPane.QUESTION_MESSAGE);
			switch (respuesta) {
			case JOptionPane.YES_OPTION:
				setStatus ("Ha pulsado si");
				break;
			case JOptionPane.NO_OPTION:
				setStatus("Ha pulsado no");
				break;
			case JOptionPane.CANCEL_OPTION:
				setStatus("Ha pulsado cancelar");
				break;
			case JOptionPane.CLOSED_OPTION:
				setStatus("Se ha cerrado la ventana");
				break;
			default:
				setStatus("Opcion desconocida");
			}
		}
		
		else if (origen.getName().equals("input")) {	//input dialog
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
			if (respuesta == null) {
				setStatus("No se ha elegido ningun libro");
			}
			else {	
				setStatus("Se ha elegido el libro " + respuesta.toString());
			}			
		}
		
		else if (origen.getName().equals("message")) {	//message dialog
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
		
		else if (origen.getName().equals("options")) {	//options dialog
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
			if (respuesta == JOptionPane.CLOSED_OPTION) {
				setStatus("Se ha cerrado la ventana");
			} else {
				setStatus("Se ha elegido " + opciones[respuesta]);
			}
			
		}
		else 
			setStatus("Error: Boton pulsado desconocido");				
	}

	private void setStatus(String string) {
		if (estado == null) {
			setStatus("Estado: " + string);
		}
		else
			estado.setText(string);
		
	}

}
