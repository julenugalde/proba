package eus.julenugalde.sandbox.designpatterns.adapter;

import java.awt.Point;

/** Clase antigua que se quiere adaptar */
public class Line {
    public void draw(Point a, Point b) {
    	int x1 = a.x;
    	int x2 = b.x;
    	int y1 = a.y;
    	int y2 = b.y;
        System.out.println("Line from point A(" + x1 + ";" + y1 + "), to point B(" + x2 + ";" + y2 + ")");
    }
}
