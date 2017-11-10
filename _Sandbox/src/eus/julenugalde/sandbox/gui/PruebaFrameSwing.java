package eus.julenugalde.sandbox.gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/** Ejemplo de GUI usando swing */
public class PruebaFrameSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static PruebaFrameSwing ventana;
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
	
	private static ResourceBundle bundle;
	
	/** Constructor de la ventana
	 * 
	 * @param titulo Titulo de la ventana
	 */
	public PruebaFrameSwing (String titulo) {
		ventana = this;
		inicializarFrame(titulo);
		crearMenus();
		crearElementos();
		asignarListeners();	
		anadirElementosVentana();
	}
	
	private void crearMenus() {
		menuBar = new JMenuBar();
		mFile = new JMenu();
		mFile.setText(bundle.getString("menu.file"));
		mFile.setMnemonic(KeyEvent.VK_F);		
		
		miNew = new JMenuItem();
		miNew.setText(bundle.getString("menu.new"));
		miNew.setIcon(cargarIcono("filenew_16_16.png"));
		miNew.setMnemonic(KeyEvent.VK_N);
		
		miOpen = new JMenuItem();
		miOpen.setText(bundle.getString("menu.open"));
		miOpen.setIcon(cargarIcono("fileopen_16_16.png"));
		miOpen.setMnemonic(KeyEvent.VK_O);
		
		miSave = new JMenuItem();
		miSave.setText(bundle.getString("menu.save"));
		miSave.setIcon(cargarIcono("filesave_16_16.png"));
		miSave.setMnemonic(KeyEvent.VK_S);
		
		miExit = new JMenuItem();
		miExit.setText(bundle.getString("menu.exit"));
		miExit.setIcon(cargarIcono("exit_16_16.png"));
		miExit.setMnemonic(KeyEvent.VK_X);
		
		mOpciones = new JMenu();
		mOpciones.setText(bundle.getString("menu.options"));
		mOpciones.setMnemonic(KeyEvent.VK_P);
		
		cbmiInvertirColores = new JCheckBoxMenuItem("", false);
		cbmiInvertirColores.setText(bundle.getString("menu.invert"));
		cbmiInvertirColores.setIcon(cargarIcono("invert_16_16.png"));
		cbmiInvertirColores.setMnemonic(KeyEvent.VK_I);
	}

	private void anadirElementosVentana() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel panelBotones = new JPanel(new FlowLayout());
		panelBotones.setBackground(Color.DARK_GRAY);
		panelBotones.add(b1);
		panelBotones.add(b2);
		panelBotones.add(b3);
		panelBotones.add(b4);
		//panel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(panelBotones);
		
		//s1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(s1);
		//tf1.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		panel.add(tf1);
		//panelDibujo.setAlignmentX(JComponent.LEFT_ALIGNMENT);
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
		b1.addActionListener(new ActionAdapter(Accion.CONFIRMACION, ventana));	
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
		
		b2.addActionListener(new ActionAdapter(Accion.ENTRADA, ventana));
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
		
		b3.addActionListener(new ActionAdapter(Accion.MENSAJE, ventana));
		b4.addActionListener(new ActionAdapter(Accion.OPCIONES, ventana));
		
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
		miNew.addActionListener(new ActionAdapter(Accion.MENU_NUEVO, ventana));
		miOpen.addActionListener(new ActionAdapter(Accion.MENU_ABRIR, ventana));
		miSave.addActionListener(new ActionAdapter(Accion.MENU_GUARDAR, ventana));
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//(JFrame.getFrames()[0]).dispose();
				ventana.dispose();
			}			
		});
		cbmiInvertirColores.addActionListener(new ActionAdapter(Accion.MENU_INVERTIR, ventana));
	}

	private void crearElementos() {
		//Botones que crean los dialogos
		b1 = new JButton("");
		b1.setText(bundle.getString("button.confirm"));
		b2 = new JButton("");
		b2.setText(bundle.getString("button.input"));
		b3 = new JButton("");
		b3.setText(bundle.getString("button.message"));
		b3.setName("message");
		b4 = new JButton("");
		b4.setText(bundle.getString("button.options"));
		b4.setName("options");

		//slider
		s1 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
		s1.setMajorTickSpacing(10);
		s1.setMinorTickSpacing(1);
		s1.setPaintTicks(true);
		s1.setPaintLabels(true);
		
		//campo de texto
		tf1 = new JTextField();		
		tf1.setMaximumSize(new Dimension(this.getWidth(), 40));
		
		//area de texto
		ta1 = new JTextArea("", 8, 70);
		ta1.setFont(new Font("Arial", Font.PLAIN, 12));
		ta1.setLineWrap(true);
		ta1.setWrapStyleWord(true); //Hace que el salto de línea sea por palabras
		Scanner scan = new Scanner(this.getClass().getResourceAsStream("/res/lorem.txt"));
		while (scan.hasNextLine()) {
			ta1.append(scan.nextLine() + System.getProperty("line.separator"));
		}
		scan.close();
	
		//panel dibujo
		panelDibujo = new PanelDibujo();
		
		//label de estado
		l1 = new JLabel("");
		
		//panel contenedor
		panel = new JPanel();
		//panel.setBackground(java.awt.Color.RED);
	}
	
	/** Inicializa la ventana, incluyendo título, tamaño, look&feel... */
	private void inicializarFrame(String titulo) {
		this.setTitle(titulo);
		this.setSize(900, 700);
		this.setLocation(350, 10);
		
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
		Locale locale = new Locale("eu", "ES");
		bundle = ResourceBundle.getBundle(
				"eus.julenugalde.sandbox.localization.PruebaFrameSwing", locale);
		
		PruebaFrameSwing ventana = 
				new PruebaFrameSwing(bundle.getString("window.title"));
		ventana.setVisible(false);	//Ocultamos la ventana mientras se inicializa
		ventana.setEnabled(true);
		ventana.setResizable(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setStatus("Aplicacion inicializada");
		configurarListenersVentana(ventana);
		//Todo listo. Hacemos visible la ventana
		ventana.setVisible(true);
		
		String saludo = MessageFormat.format(bundle.getString("text.greeting"), 
				"Julen", new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		System.out.println(saludo);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.fillOval(250, 160, 200, 80);
		g.setColor(Color.GREEN);
		g.fillOval(275, 170, 150, 60);
		g.setColor(Color.BLUE);
		g.fillOval(300, 180, 100, 40);
		g.setColor(Color.GRAY);
		g.fillOval(325, 190, 50, 20);
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
	
	/** Método que invierte los colores de la TextArea, de forma que pase de fondo blanco
	 * y letras negras a fondo negro y letras blancas.
	 */
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
	
	/** Método que devuelve un icono a partir de su nombre 
	 * 
	 * @param nombre Nombre del fichero del icono dentro del directorio /res
	 * @return objeto con el icono cargado
	 */
	private Icon cargarIcono(String nombre) {
		java.net.URL direccion = this.getClass().getResource("/res/" + nombre);
		return new ImageIcon(direccion);
	}
}


