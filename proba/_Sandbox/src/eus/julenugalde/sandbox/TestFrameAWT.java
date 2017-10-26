package eus.julenugalde.sandbox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TestFrameAWT extends Frame {	

	public TestFrameAWT (String titulo) {
		this.setTitle(titulo);
		this.setSize(500, 400);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		
		Button b1 = new Button("Iparralde");
		Button b2 = new Button("Hegoalde");
		Button b3 = new Button("Ekialde");
		Button b4 = new Button("Mendebalde");
		Button b5 = new Button("Erdia");
		
		b1.addActionListener(new ButtonPressListener());
		
		add(b1, BorderLayout.NORTH);
		add(b2, BorderLayout.SOUTH);
		add(b3, BorderLayout.EAST);
		add(b4, BorderLayout.WEST);
		add(b5, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		TestFrameAWT ventana = new TestFrameAWT("prueba");
		ventana.setVisible(true);
		
	}

	
}

public class ButtonPressListener implements ActionListener  {

	public ButtonPressListener() {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}