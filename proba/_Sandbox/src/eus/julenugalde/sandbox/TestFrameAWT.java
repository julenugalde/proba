package eus.julenugalde.sandbox;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class TestFrameAWT extends Frame {	
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private TextField tf1;
	private Checkbox cb1;
	private ScrollPane sp;
	
	public TestFrameAWT (String titulo) {
		this.setTitle(titulo);
		this.setSize(400, 300);
		this.setResizable(true);
		this.setLayout(new GridLayout(5,1));
		this.setLocation(300, 200);
		this.addWindowListener(new WindowListener() {						
			@Override
			public void windowActivated(WindowEvent arg0) {
				System.out.println("ventana activada");				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.out.println("ventana cerrada");				
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.out.println("cerrando...");
				dispose();				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				System.out.println("ventana desactivada");
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				System.out.println("ventana deicoinificada");
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				System.out.println("ventana iconificada");
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				System.out.println("ventana abierta");
			}			
		});
		
		//Se definen los elementos
		b1 = new Button("Iparralde");
		b1.setActionCommand("iparraldera noa");
		b2 = new Button("Hegoalde");
		b2.setActionCommand("hegoaldera noa");
		b3 = new Button("Ekialde");
		b3.setActionCommand("ekialdera noa");
		b4 = new Button("Mendebalde");
		b4.setActionCommand("mendebaldera noa");
		b5 = new Button("Erdia");
		b5.setActionCommand("hemen geratuko naiz");
		tf1 = new TextField();
		tf1.setFont(Font.getFont("Helvetica"));
		tf1.setEditable(true);
		tf1.setForeground(Color.RED);
		tf1.setBackground(Color.LIGHT_GRAY);
		tf1.setText("");
		tf1.setCaretPosition(0);
		cb1 = new Checkbox("opcion marcada", true);
		sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		sp.setBackground(Color.GREEN);
		
		//Se  definen los listeners
		b1.addActionListener(new ButtonPressListener());
		b2.addActionListener(new ButtonPressListener());
		b3.addActionListener(new ButtonPressListener());
		b4.addActionListener(new ButtonPressListener());
		b5.addActionListener(new ButtonPressListener());
		cb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Checkbox cb = (Checkbox)e.getSource();
				if (e.getStateChange() == ItemEvent.DESELECTED)
					cb.setLabel("opcion desmarcada");
				else if (e.getStateChange() == ItemEvent.SELECTED)
					cb.setLabel("opcion marcada");					
			}			
		});
		sp.getHAdjustable().addAdjustmentListener(new PaneAdjustmentListener());
		sp.getVAdjustable().addAdjustmentListener(new PaneAdjustmentListener());
		tf1.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				TextField tf = (TextField)e.getSource();
				System.out.println("Texto cambiado:\"" + tf.getText() + "\"");
			}			
		});
		tf1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.print("tecla pulsada: ");
				if (e.isShiftDown())
					System.out.print("SHIFT ");
				if (e.isControlDown())
					System.out.print("CTRL");
				if (e.isAltDown())
					System.out.println("ALT");
				if (e.isAltGraphDown())
					System.out.print("ALTGR");
				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A: 
					System.out.println("se ha escrito la A");
					break;
				case KeyEvent.VK_E: case KeyEvent.VK_I: 
				case KeyEvent.VK_O: case KeyEvent.VK_U:
					System.out.println("se ha escrito una vocal");
					break;
				case 0:
					System.err.println("da cero");
				default:
					System.out.println("no se ha escrito una vocal");
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("tecla soltada");
			}

			@Override
			public void keyTyped(KeyEvent e) {				
				System.out.println(e.getKeyChar());
			}
			
		});
		
		//Se añaden los elementos a la ventana
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(tf1);
		add(cb1);
		add(sp);
	}

	public static void main(String[] args) {
		TestFrameAWT ventana = new TestFrameAWT("prueba");
		ventana.setEnabled(true);
		ventana.setVisible(true);
		
	}

	private class ButtonPressListener implements ActionListener  {
		private SimpleDateFormat sdf;
		
		public ButtonPressListener() {
			sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Button b = (Button)e.getSource();
			int mascara = e.getModifiers();
			
			System.out.print(sdf.format(e.getWhen()) + ": "+ b.getActionCommand() + 
					" pulsado con modificadores ");
			if ((mascara & InputEvent.ALT_DOWN_MASK) == InputEvent.ALT_DOWN_MASK)
				System.out.print("ALT ");
			if ((mascara & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK)
				System.out.print("Control ");
			if ((mascara & InputEvent.BUTTON2_DOWN_MASK) == InputEvent.BUTTON2_DOWN_MASK)
				System.out.print("Button2 ");
			if ((mascara & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK)
				System.out.print("Button3 ");
			if ((mascara & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK)
				System.out.print("Shift ");
			System.out.println();
		}		
	}
	
	private class PaneAdjustmentListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			System.out.println("ScrollPane ajustado. Parametros: " + e.paramString() + 
					", valor=" + e.getValue() + "tipo de ajuste: " + e.getAdjustmentType());	
		}
		
	}
}

