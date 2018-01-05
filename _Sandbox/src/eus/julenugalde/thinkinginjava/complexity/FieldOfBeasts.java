package eus.julenugalde.thinkinginjava.complexity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FieldOfBeasts extends JPanel implements Runnable {
	public static float fieldOfView = (float)(Math.PI / 4);	//In radians
	public static float minimumDistance = 10.0f;	//In pixels
	public static float decayRate = 1.0f;	//Deceleration % per second
	public static int halfFieldOfView = (int)((fieldOfView * 360) / (2 * Math.PI));
	
	public static int maxSpeed = 20;	//pixels/second
	public static int xExtent = 640;
	public static int yExtent = 480;
	public static int numBeasts = 50;
	
	private Vector<Beast> beasts;
	private boolean uniqueColors = true;
	private Thread thisThread;
	private int delay = 25;
	
	public FieldOfBeasts() {
		setBackground(Color.WHITE);
		setSize(xExtent, yExtent);
		makeBeastsVector();
	}
	
	public void start() {
		thisThread = new Thread(this);
		thisThread.start();
	}
	
	private void makeBeastsVector() {
		beasts = new Vector<Beast>();
		Random randomGenerator = new Random();
		double cubeRootOfBeastNumber = Math.pow((double)numBeasts, (1.0/3.0));
		float colorCubeStepSize = (float)(1.0 / cubeRootOfBeastNumber);
		float r = 0.0f;
		float g = 0.0f;
		float b = 0.0f;
		for (int i=0; i<numBeasts; i++) {	//Create the beasts
			//Define the position
			int x = (int)(randomGenerator.nextFloat() * xExtent);
			if (x > (xExtent - Beast.GSIZE)) {
				x -= Beast.GSIZE;
			}
			int y = (int)(randomGenerator.nextFloat() * yExtent);
			if (y > (yExtent - Beast.GSIZE)) {
				y -= Beast.GSIZE;
			}
			
			//Define the direction and speed
			float direction = (float)(randomGenerator.nextFloat() * 2 * Math.PI);
			int speed = (int)(randomGenerator.nextFloat() * (float)maxSpeed);
			
			//Define the color if it has to be unique
			if (uniqueColors) {
				r += colorCubeStepSize;
				if (r > 1.0) {
					r -= 1.0f;
					g += colorCubeStepSize;
					if (g > 1.0) {
						g -= 1.0f;
						b += colorCubeStepSize;
						if (b > 1.0) {
							b -= 1.0f;
						}
					}
				}
			}
			
			//Generate the beast and add it to the vector
			beasts.addElement(new Beast(this, x, y, direction, speed, new Color(r,g,b)));
		}
	}

	public Vector<Beast> beastListInSector(Beast viewer) {
		Point viewerPosition = viewer.getPosition();
		Vector<Beast> result = new Vector<Beast>();
		Iterator<Beast> iterator = beasts.iterator();
		Beast beast = beasts.elementAt(0);
		while (iterator.hasNext()) {
			beast = iterator.next();
			if (beast != viewer) {
				float bearing = beast.bearingFromPointAlongAxis(
						viewerPosition.x, viewerPosition.y, viewer.getCurrentDirection());
				if (Math.abs(bearing) < (fieldOfView/2)) {
					result.addElement(beast);
				}
			}
		}
		return result;
	}

	public void paint (Graphics g) {
		super.paint(g);
		Iterator<Beast> iterator = beasts.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(g);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (true) {
			for (int i=0; i<beasts.size(); i++) {
				beasts.elementAt(i).step();
			}
			try {
				thisThread.sleep(delay);
			} catch (InterruptedException iex) {
				System.err.println(
						"InterruptedException @ FieldOfBeasts: " + iex.getLocalizedMessage());
			}
			repaint();
		}		
	}
}
