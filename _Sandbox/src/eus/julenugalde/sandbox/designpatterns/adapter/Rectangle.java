package eus.julenugalde.sandbox.designpatterns.adapter;

/** Clase antigua que se quiere adaptar */
public class Rectangle {
	public void draw(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-down point (" + x + ";" + y + 
        		"), width: " + width + ", height: " + height);
    }
}
