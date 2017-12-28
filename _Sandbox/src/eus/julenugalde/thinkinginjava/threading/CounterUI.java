package eus.julenugalde.thinkinginjava.threading;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CounterUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JCheckBox[] jcbToggle;
	private final int NUM_COUNTERS = 10;
	private JButton jbStart;
	private JLabel jlCount;
	private Counter[] counters;	
	private int[] countValues;
	private Thread loggingDaemon;
	
	public CounterUI() {
		counters = new Counter[NUM_COUNTERS];
		countValues = new int[NUM_COUNTERS];
		for (int i=0; i<NUM_COUNTERS; i++) {
			counters[i] = new Counter(i, this);
			countValues[i] = 0;
		}
loggingDaemon = new Thread(new LoggingDaemon(this));
loggingDaemon.setDaemon(true);
		
		setTitle("Counter test");
		setSize(900, 150);
		setLocation(100, 100);
		initializeComponents();
		assignListeners();
		placeComponents();
	}
	
	private void initializeComponents() {
		jcbToggle = new JCheckBox[NUM_COUNTERS];
		for (int i=0; i<NUM_COUNTERS; i++) {
			jcbToggle[i] = new JCheckBox("Count " + (i+1));
			jcbToggle[i].setSelected(true);
			jcbToggle[i].setName(""+i);
		}
		jbStart = new JButton("Start");
		jlCount = new JLabel("Press 'Start' to start counting");
	}

	private void assignListeners() {
		for (int i=0; i<jcbToggle.length; i++) {
			jcbToggle[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String text = ((JCheckBox)e.getSource()).getName();
					try {
						int id = Integer.parseInt(text);
						counters[id].setCounting(jcbToggle[id].isSelected());	
					} catch (NumberFormatException nfex) {
						System.err.println("text='" + text + "'");
					}
				}			
			});
		}
		
		jbStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<NUM_COUNTERS; i++) {
					new Thread(counters[i]).start();
				}
				loggingDaemon.start();
				jbStart.setText("Counts started");
				jbStart.setEnabled(false);
			}
		});	
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void placeComponents() {
		setLayout(new GridLayout(3, 1));
		JPanel jpCheckboxes = new JPanel(new FlowLayout());
		for (JCheckBox comp : jcbToggle) {
			jpCheckboxes.add(comp);
		}
		add(jpCheckboxes);
		add(jbStart);
		add(jlCount);
	}
	
	public void setCount(int id, int value) {
		if (id<NUM_COUNTERS) {
			countValues[id] = value;
		}
		updateLabel();
	}
	
	public int[] getCountValues() {
		return countValues;
	}

	private void updateLabel() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<NUM_COUNTERS; i++) {
			sb.append("Count #" + (i+1) + "=" + countValues[i] + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		jlCount.setText(sb.toString());
	}

	public static void main(String[] args) {
		CounterUI window = new CounterUI();
		window.setVisible(true);
	}

}

