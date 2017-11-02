package eus.julenugalde.sandbox.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

class TeclasAdapter extends KeyAdapter {
	@Override
	public void keyReleased(KeyEvent e) {
		//Convierte el texto a mayusculas
		JTextField origen = (JTextField)e.getSource();
		origen.setText(origen.getText().toUpperCase());
	}
}