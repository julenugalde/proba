package eus.julenugalde.sandbox.gui;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Ejemplo de GUI usando swing */
public class PruebaFrameSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static PruebaFrameSwing ventana;	//TODO Ver si esto tiene sentido
	private JButton b1;
	private JButton b2;
	private JButton b3;	
	private JButton b4;
	private JSlider s1;
	private JPanel panel;
	private JTextField tf1;
	private JTextArea ta1;
	private PanelDibujo panelDibujo;
	private JLabel l1;
	private JMenuBar menuBar;
	private JMenu mFile;
	private JMenuItem miNew;
	private JMenuItem miOpen;
	private JMenuItem miSave;
	private JMenuItem miExit;
	private JMenu mOpciones;
	private JCheckBoxMenuItem cbmiInvertirColores;
	
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
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);		
		
		miNew = new JMenuItem("New");
		miNew.setIcon(new ImageIcon("res/filenew_16_16.png"));
		miNew.setMnemonic(KeyEvent.VK_N);
		
		miOpen = new JMenuItem("Open");
		miOpen.setIcon(new ImageIcon("res/fileopen_16_16.png"));
		miOpen.setMnemonic(KeyEvent.VK_O);
		
		miSave = new JMenuItem("Save");
		miSave.setIcon(new ImageIcon("res/filesave_16_16.png"));
		miSave.setMnemonic(KeyEvent.VK_S);
		
		miExit = new JMenuItem("Exit");
		miExit.setIcon(new ImageIcon("res/exit_16_16.png"));
		miExit.setMnemonic(KeyEvent.VK_X);
		
		mOpciones = new JMenu("Options");
		mOpciones.setMnemonic(KeyEvent.VK_P);
		
		cbmiInvertirColores = new JCheckBoxMenuItem("Invert colors", false);
		cbmiInvertirColores.setIcon(new ImageIcon("res/invert_16_16.png"));
		cbmiInvertirColores.setMnemonic(KeyEvent.VK_I);
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
		ta1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		JScrollPane jsp = new JScrollPane(ta1);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setMaximumSize(new Dimension(this.getWidth(), 400));
		panel.add(jsp);
		l1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(l1);
		
		this.add(panel);		
		
		mFile.add(miNew);
		mFile.add(miOpen);
		mFile.add(miSave);
		mFile.addSeparator();
		mFile.add(miExit);
		mOpciones.add(cbmiInvertirColores);
		menuBar.add(mFile);
		menuBar.add(mOpciones);
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
		
		//Listeners de los menús
		miNew.addActionListener(new ActionAdapter(Accion.MENU_NUEVO));
		miOpen.addActionListener(new ActionAdapter(Accion.MENU_ABRIR));
		miSave.addActionListener(new ActionAdapter(Accion.MENU_GUARDAR));
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispatchEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSING));
			}			
		});
		cbmiInvertirColores.addActionListener(new ActionAdapter(Accion.MENU_INVERTIR));
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
		
		//area de texto
		ta1 = new JTextArea("", 8, 70);
	
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
		this.setSize(800, 700);
		this.setLocation(250, 20);
		
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
		configurarListenersVentana(ventana);
		//Todo listo. Hacemos visible la ventana
		ventana.setVisible(true);
	}
	
	private static void configurarListenersVentana(PruebaFrameSwing v) {
		v.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);				
			}
		});
		
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
	
	public void invertirColoresTextArea () {
		
		if (ta1.getBackground().equals(Color.WHITE)) {	//Colores normales
			ta1.setBackground(Color.BLACK);
			ta1.setForeground(Color.WHITE);
		}
		else {
			ta1.setBackground(Color.WHITE);
			ta1.setForeground(Color.BLACK);
		}
	}
}


