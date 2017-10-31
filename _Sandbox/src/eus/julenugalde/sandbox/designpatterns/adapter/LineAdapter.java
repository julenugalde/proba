package eus.julenugalde.sandbox.designpatterns.adapter;

import java.awt.Point;

/** Clase adaptadora que permite utilizar {@link Line} */
public class LineAdapter implements Shape {
	private Line lineaAntigua;
	
	public LineAdapter(Line linea) {
		lineaAntigua = linea;
	}
	
	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		lineaAntigua.draw(new Point(x1,y1), new Point(x2,y2));

	}

}
