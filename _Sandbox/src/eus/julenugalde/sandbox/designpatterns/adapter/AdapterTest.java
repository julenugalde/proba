package eus.julenugalde.sandbox.designpatterns.adapter;

public class AdapterTest {
	public static void main (String[] args) {
		Shape[] shapes = {
				new LineAdapter(new Line()),
				new RectangleAdapter(new Rectangle())
		};
		
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;
		
		for (Shape shape : shapes)
			shape.draw(x1, y1, x2, y2);
	}
}
