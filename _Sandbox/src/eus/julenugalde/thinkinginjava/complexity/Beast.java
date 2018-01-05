package eus.julenugalde.thinkinginjava.complexity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

public class Beast {
	public static final int GSIZE = 10;	//Graphic size
	
	private int x;	//Screen position
	private int y;
	private int currentSpeed;	//pixels/second
	private float currentDirection;	//radians
	private Color color;	//Fill color
	private FieldOfBeasts field;	//Where the beasts roam
		
	public Beast (FieldOfBeasts field, int x, int y, float direction, int speed, Color fillColor) {
		this.field = field;
		this.x = x;
		this.y = y;
		this.setCurrentDirection(direction);
		this.currentSpeed = speed;
		this.color = fillColor;
	}
	
	public void step() {
		//Move based on those within your sight
		Vector<Beast> seen = field.beastListInSector(this);
		
		if (seen.size() > 0) { //If you are not in the front
			//Gather data on those you see
			int totalSpeed = 0;
			float totalBearing = 0.0f;
			float distanceToNearest = 1000000.0f;
			Beast nearestBeast = seen.elementAt(0);
			Iterator<Beast> enumBeasts = seen.iterator();
			while (enumBeasts.hasNext()) {
				Beast beast = enumBeasts.next();
				totalSpeed += beast.currentSpeed;
				float bearing = beast.bearingFromPointAlongAxis(x, y, getCurrentDirection());
				totalBearing += bearing;
				float distanceToBeast = beast.distanceFromPoint(x, y);
				if (distanceToBeast < distanceToNearest) {
					nearestBeast = beast;
					distanceToNearest = distanceToBeast;
				}
			}
			
			//Rule 1: Match average speed of those in the list
			currentSpeed = totalSpeed / seen.size();
			//Rule 2: Move towards the perceived center of gravity of the herd
			setCurrentDirection(totalBearing / seen.size());
			//Rule 3: Maintain a minimum distance from those around you
			if (distanceToNearest <= FieldOfBeasts.minimumDistance) {
				setCurrentDirection(nearestBeast.getCurrentDirection());
				currentSpeed = nearestBeast.currentSpeed;
				if (currentSpeed > FieldOfBeasts.maxSpeed) {
					currentSpeed = FieldOfBeasts.maxSpeed;
				}
			}
		}		
		else {	//You're in front, so slow down
			currentSpeed = (int)(currentSpeed * FieldOfBeasts.decayRate);
		}
		
		//Make the beast move
		x += (int)(Math.cos(getCurrentDirection()) * currentSpeed);
		x %= FieldOfBeasts.xExtent;
		if (x<0) {
			x += FieldOfBeasts.xExtent;
		}
		
		y += (int)(Math.sin(getCurrentDirection()) * currentSpeed);
		y %= FieldOfBeasts.yExtent;
		if (y<0) {
			y += FieldOfBeasts.yExtent;
		}
	}

	private float distanceFromPoint(int x0, int y0) {
		return (float)Math.sqrt(Math.pow(x-x0, 2) + Math.pow(y-y0, 2));
	}
	
	public Point getPosition() {
		return new Point(x,y);
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		int directionInDegrees = (int)((getCurrentDirection() * 360) / (2 * Math.PI));
		int startAngle = directionInDegrees - FieldOfBeasts.halfFieldOfView;
		int endAngle = 90;
		g.fillArc(x, y, GSIZE, GSIZE, startAngle, endAngle);
	}

	/**
	 * Returns bearing angle of the current {@link Beast} in the world coordinate system.
	 */
	public float bearingFromPointAlongAxis(int originX, int originY, float axis) {
		try {
			double bearingInRadians = Math.atan((y-originY) / (x-originX));
			// atan has 2 solutions, so needs to be corrected for other quarters
			if (x < originX) {
				if (y < originY) {
					bearingInRadians += (-(float)Math.PI);
				}
			}
			else {
				bearingInRadians = (float)Math.PI - bearingInRadians;
			}
			return (float)(axis - bearingInRadians);
		} catch (ArithmeticException aex) {
			//Divide by zero possible
			if (x > originX) {
				return 0;
			}
			else {
				return (float)Math.PI;
			}
		}
	}

	/**
	 * @return the currentDirection
	 */
	public float getCurrentDirection() {
		return currentDirection;
	}

	/**
	 * @param currentDirection the currentDirection to set
	 */
	public void setCurrentDirection(float currentDirection) {
		this.currentDirection = currentDirection;
	}
}
