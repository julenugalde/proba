package eus.julenugalde.sandbox.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;

public class PruebaJApplet extends JApplet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		super.init();
				
		//Uso de la status bar para mostar texto
		this.showStatus("variable=" + this.getParameter("variable"));
		
		//Creamos componentes, asignamos un listener y los añadimos a un contenedor
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JButton botones[] = {
				new JButton("boton 1"),
				new JButton("boton 2"),
				new JButton("boton 3"),
				new JButton("boton 4"),
				new JButton("boton 5")
		};
		for (JButton boton : botones) {
			boton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton origen = (JButton)e.getSource();
					((JApplet)origen.getTopLevelAncestor()).showStatus(
							"Se ha pulsado el " + origen.getText());				
				}			
			});
			this.add(boton);
		}	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.drawString("Prueba del método paint(Graphics) de JApplet", 50, 200);;
	}
	
	@Override
	public void start () {
		super.start();
		showStatus("START");
	}
	
	@Override
	public void stop() {
		super.stop();
		showStatus("STOP");
	}
	
	@Override
	public void destroy() {
		super.destroy();		
	}
}
