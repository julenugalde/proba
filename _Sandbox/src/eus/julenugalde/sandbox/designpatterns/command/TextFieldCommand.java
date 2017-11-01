package eus.julenugalde.sandbox.designpatterns.command;

import javax.swing.JTextField;

public class TextFieldCommand extends JTextField implements Command {
	private static final long serialVersionUID = 1L;

	public TextFieldCommand() {
		super();
	}

	public TextFieldCommand(String text) {
		super(text);
	}

	@Override
	public void execute() {
		System.out.println("TextField con texto " + getText());
	}

}
