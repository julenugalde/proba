package eus.julenugalde.sandbox.designpatterns.command;

import javax.swing.JButton;

public class ButtonCommand extends JButton implements Command {
	private static final long serialVersionUID = 1L;

	public ButtonCommand() {
		super();
	}
	
	public ButtonCommand(String text) {
		super(text);
	}
	
	@Override
	public void execute() {
		System.out.println("Boton " + getText() + " pulsado");		
	}
	
}
