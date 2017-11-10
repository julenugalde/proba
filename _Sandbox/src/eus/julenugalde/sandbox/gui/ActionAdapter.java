package eus.julenugalde.sandbox.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class ActionAdapter implements ActionListener {	
	private PruebaFrameSwing ventana;
	private JPanel panelBotones;
	private Accion accion;

	public ActionAdapter (Accion accion, PruebaFrameSwing ventana) {
		this.accion = accion;
		this.ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent origen = (JComponent)e.getSource();
		switch (accion) {
		case CONFIRMACION:
			panelBotones = (JPanel)origen.getParent();
			buttonConfirmacion(e);
			break;
		
		case ENTRADA:
			panelBotones = (JPanel)origen.getParent();
			buttonEntrada(e);
			break;
		
		case MENSAJE:
			panelBotones = (JPanel)origen.getParent();
			buttonMensaje(e);
			break;
		
		case OPCIONES:	
			panelBotones = (JPanel)origen.getParent();
			buttonOpciones(e);
			break;
			
		case MENU_ABRIR:
			menuAbrir(e);
			break;
			
		case MENU_GUARDAR:
			menuGuardar(e);
			break;
			
		case MENU_NUEVO:
			menuNuevo(e);
			break;
			
		case MENU_INVERTIR:
			menuInvertir(e);
			break;
			
		default:
			setStatus("Error: Boton pulsado desconocido");
			break;
		}
	}
	
	private void menuInvertir(ActionEvent e) {
		ventana.invertirColoresTextArea();		
	}

	private void buttonOpciones(ActionEvent e) {
		String[] libros = {"A Game of Thrones", "A Clash of Kings", "A Storm of Swords",
				"A Feast for Crows", "A Dance with Dragong", "The Winds of Winter"};
		int respuesta = JOptionPane.showOptionDialog(
				panelBotones, 								//padre
				"Elija una opción", 				//mensaje
				"A song of ice and fire", 			//titulo
				JOptionPane.DEFAULT_OPTION,		 	//tipo de opcion
				JOptionPane.QUESTION_MESSAGE, 		//tipo de mensaje
				null, 								//icono
				libros, 							//opciones
				0);									//valor por defecto
		if (respuesta == JOptionPane.CLOSED_OPTION) {
			setStatus("Se ha cerrado la ventana");
		} else {
			setStatus("Se ha elegido " + libros[respuesta]);
		}
	}

	private void buttonMensaje(ActionEvent e) {
		String [] cadena = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +  
				"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 
				"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " + 
				"nisi ut aliquip ex ea commodo consequat. ", 
				"Duis aute irure dolor in reprehenderit in voluptate velit esse " + 
				"cillum dolore eu fugiat nulla pariatur. ", 
				"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " + 
				"officia deserunt mollit anim id est laborum."};
		JOptionPane.showMessageDialog(
				panelBotones, 								//parent component
				cadena, 							//informacion a mostrar
				"Lorem ipsum", 						//titulo
				JOptionPane.INFORMATION_MESSAGE);	//tipo de mensaje
	}

	private void buttonEntrada(ActionEvent e) {
		String[] libros = {"A Game of Thrones", "A Clash of Kings", "A Storm of Swords",
				"A Feast for Crows", "A Dance with Dragong", "The Winds of Winter"};
		Object objRespuesta = JOptionPane.showInputDialog(
				panelBotones,							//Parent component
				"Elija un libro:", 				//Mensaje
				"A Song of ice and fire", 		//Titulo de ventana de dialogo
				JOptionPane.QUESTION_MESSAGE, 	//Tipo de ventana
				null, 							//Icono
				libros,						//Elementos entre los que elegir
				0);								//Seleccion inicial
		if (objRespuesta == null) {
			setStatus("No se ha elegido ningun libro");
		}
		else {	
			setStatus("Se ha elegido el libro " + objRespuesta.toString());
		}
	}

	private void buttonConfirmacion(ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(
				panelBotones,		//parent component
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
			break;
		}
	}

	private void menuAbrir(ActionEvent e) {
		setStatus("Abrir archivo");
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView());
		jfc.setDialogTitle("Elija archivo");
		jfc.setCurrentDirectory(new File("C:/temp"));
		int respuesta = jfc.showOpenDialog(null);
		switch(respuesta) {
		case JFileChooser.APPROVE_OPTION:
			File archivo = jfc.getSelectedFile();
			setStatus("Archivo seleccionado: " + archivo.getAbsolutePath());
			break;
		case JFileChooser.CANCEL_OPTION:
			setStatus("Se eligió cancelar");
			break;
		case JFileChooser.ERROR_OPTION:
			setStatus("Error en la apertura del archivo");
			break;
		}
	}
	
	private void menuNuevo(ActionEvent e) {
		setStatus("Crear archivo nuevo");
	}
	
	private void menuGuardar(ActionEvent e) {
		setStatus("Guardar archivo");
	}

	private void setStatus(String string) {
		if (ventana == null) {
			System.err.println("No se puede acceder a la ventana");
		}
		else if (!ventana.setStatus(string)) {
			System.err.println("Estado: " + string);
		}
	}

}
