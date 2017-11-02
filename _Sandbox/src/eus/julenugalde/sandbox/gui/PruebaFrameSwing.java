package eus.julenugalde.sandbox.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.input.KeyCode;

import java.awt.CheckboxMenuItem;
import java.awt.Dimension;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Ejemplo de GUI usando swing */
public class PruebaFrameSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;	
	private JButton b4;
	private JSlider s1;
	private JPanel panel;
	private JTextField tf1;
	private PanelDibujo panelDibujo;
	private JLabel l1;
	private JMenuBar menuBar;
	private JMenu subMenu;
	private JMenuItem m1;
	private JMenuItem m2;
	private JMenuItem m3;
	
	/** Constructor de la ventana
	 * 
	 * @param titulo Titulo de la ventana
	 */
	public PruebaFrameSwing (String titulo) {
		inicializarFrame(titulo);
		crearMenus();
		crearElementos();
		asignarListeners();	
		anadirElementosVentana();
	}
	
	private void crearMenus() {
		menuBar = new JMenuBar();
		subMenu = new JMenu("File");
		subMenu.setMnemonic(KeyEvent.VK_N);		
		
		m1 = new JMenuItem("New");
		m1.setIcon(new ImageIcon("res/filenew_16_16.png"));
		m1.setMnemonic(KeyEvent.VK_N);
		
		m2 = new JMenuItem("Open");
		m2.setIcon(new ImageIcon("res/fileopen_16_16.png"));
		m2.setMnemonic(KeyEvent.VK_O);
		
		m3 = new JMenuItem("Save");
		m3.setIcon(new ImageIcon("res/filesave_16_16.png"));
		m3.setMnemonic(KeyEvent.VK_S);
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
		panelDibujo.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(panelDibujo);
		l1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(l1);
		
		this.add(panel);		
		
		subMenu.add(m1);
		subMenu.add(m2);
		subMenu.addSeparator();
		subMenu.add(m3);
		menuBar.add(subMenu);
		this.setJMenuBar(menuBar);
		
	}

	private void asignarListeners() {
		//Listeners para los botones, que desplegarán ventanas de diálogo
		b1.addActionListener(new ActionAdapter(Accion.CONFIRMACION));	
		b1.addActionListener(new ActionListener() {	//Otros listener para el mismo Button
			@Override
			public void actionPerformed(ActionEvent e) {
				int valorActual = s1.getValue();
				if (valorActual > 10)
					s1.setValue(valorActual-10);
				else
					s1.setValue(0);				
			}
		});
		
		b2.addActionListener(new ActionAdapter(Accion.ENTRADA));
		b2.addActionListener(new ActionListener() {	//Segundo listener
			@Override
			public void actionPerformed(ActionEvent e) {
				int valorActual = s1.getValue();
				if (valorActual < (s1.getMaximum()-10))
					s1.setValue(valorActual+10);
				else
					s1.setValue(s1.getMaximum());				
			}
		});
		
		b3.addActionListener(new ActionAdapter(Accion.MENSAJE));
		b4.addActionListener(new ActionAdapter(Accion.OPCIONES));
		
		//Listeners del slider. Simplemente sacan el nuevo valor en la barra de estado
		s1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider origen = (JSlider)e.getSource();
				setStatus("Nuevo valor: " + origen.getValue());
			}			
		});
		s1.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setStatus("Propiedad cambiada " + evt.getPropertyName() + ". " + 
					evt.getOldValue() + " -> " + evt.getNewValue());				
			}			
		});
		
		//Listener para el campo de texto
		tf1.addKeyListener(new TeclasAdapter());
		
		m1.addActionListener(new ActionAdapter(Accion.MENU_NUEVO));
		m2.addActionListener(new ActionAdapter(Accion.MENU_ABRIR));
		m3.addActionListener(new ActionAdapter(Accion.MENU_GUARDAR));
	}

	private void crearElementos() {
		//Botones que crean los dialogos
		b1 = new JButton("ConfirmDialog");
		b2 = new JButton("InputDialog");
		b3 = new JButton("MessageDialog");
		b3.setName("message");
		b4 = new JButton("OptionsDialog");
		b4.setName("options");

		//slider
		s1 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
		s1.setMajorTickSpacing(10);
		s1.setMinorTickSpacing(1);
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		
		//campo de texto
		tf1 = new JTextField();		
		tf1.setMaximumSize(new Dimension(this.getWidth(), 30));
		
		//panel dibujo
		panelDibujo = new PanelDibujo();
		
		//label de estado
		l1 = new JLabel("");
		
		//panel contenedor
		panel = new JPanel();
		//panel.setBackground(java.awt.Color.RED);
	}

	private void inicializarFrame(String titulo) {
		this.setTitle(titulo);
		this.setSize(700, 600);
		this.setLocation(250, 50);
		
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
		ventana.setVisible(false);	//Ocultamos la ventana mientras se inicializa
		ventana.setEnabled(true);
		ventana.setResizable(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setStatus("Aplicacion inicializada");
		
		//Todo listo. Hacemos visible la ventana
		ventana.setVisible(true);
	}
	
	/** Escribe un texto en la barra de estado de la ventana
	 * 
	 * @param status Texto a mostrar
	 * @return <code>true</code> si ha sido correcto, <code>false</code> si no se ha hecho
	 */
	public boolean setStatus (String status) {
		if (l1 == null) {
			return false;
		}
		l1.setText(status);
		return true;
	}
}


