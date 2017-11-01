package eus.julenugalde.sandbox.designpatterns.command;

import javax.swing.JCheckBox;

public class CheckBoxCommand extends JCheckBox implements Command {
	private static final long serialVersionUID = 1L;
	
	public CheckBoxCommand () {
		super();
	}

	public CheckBoxCommand (String text) {
		super(text);
	}

	public CheckBoxCommand (String text, boolean selected) {
		super(text, selected);
	}
	
	@Override
	public void execute() {
		System.out.println("CheckBox " + getText() + 
				(isSelected() ? " seleccionado" : " no seleccionado"));
	}
}
