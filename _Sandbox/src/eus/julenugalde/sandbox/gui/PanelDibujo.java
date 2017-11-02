package eus.julenugalde.sandbox.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelDibujo extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelDibujo() {
		super();
		setBackground(new Color(230, 230, 230));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawLine(35, 35, 125, 185);
		g.setColor(Color.GREEN);
		g.fillRect(250, 100, 120, 80);
		g.setColor(Color.BLUE);
		int[] x = {25, 55, 81, 132, 90, 110};
		int[] y = {59, 135, 75, 99, 101, 30};
		g.drawPolyline(x, y, x.length);
		g.setColor(Color.BLACK);
		g.drawString("prueba de uso de Graphics", 20, 250);
		g.setFont(new Font(getFont().getFontName(), Font.BOLD|Font.ITALIC, 15));
		g.drawString("en negrita, cursiva y más grande", 25, 300);
		g.setColor(Color.MAGENTA);
		g.drawRoundRect(400, 280, 120, 80, 20, 25);
		
		//imagen desde URL
		try {
			URL origen = new URL("https://education.oracle.com/education/images/wdpsub/java.png");
			Image imagen = ImageIO.read(origen);
			g.drawImage(imagen, 400, 100, this);
		} catch (IOException ioex) {}
	}
}
