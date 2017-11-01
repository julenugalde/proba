package eus.julenugalde.sandbox.designpatterns.command;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CommandTest extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ButtonCommand b1;
	private ButtonCommand b2;
	private CheckBoxCommand cb1;
	private TextFieldCommand tf1;
	private JPanel panel;
	
	public CommandTest(String title) {
		configureFrame(title);
		initializeComponents();
		setListeners();
		addComponents();
	}
	
	private void addComponents() {
		
		panel.add(b1);
		panel.add(b2);
		panel.add(cb1);
		panel.add(tf1);
		this.add(panel);
	}

	private void setListeners() {
		b1.addActionListener(this);
		b2.addActionListener(this);
		cb1.addActionListener(this);
		tf1.addActionListener(this);		
	}

	private void initializeComponents() {
		b1 = new ButtonCommand("accion 1");
		b2 = new ButtonCommand("accion 2");
		cb1 = new CheckBoxCommand ("opcion", false);
		tf1 = new TextFieldCommand ("");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

	}

	private void configureFrame(String title) {
		setTitle(title);
		setSize(300, 150);
		setLocation(250, 300);
		setLayout(new BorderLayout());
	}

	public static void main(String[] args) {
		CommandTest frame = new CommandTest("Command design pattern test");
		frame.setVisible(true);
		frame.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Command source = (Command)e.getSource();
		source.execute();		
	}
}
