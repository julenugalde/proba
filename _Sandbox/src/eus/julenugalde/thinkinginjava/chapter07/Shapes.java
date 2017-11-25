package eus.julenugalde.thinkinginjava.chapter07;

//: polymorphism/Shapes.java
// Polymorphism in Java.
import eus.julenugalde.thinkinginjava.chapter07.shape.*;

public class Shapes {
  private static RandomShapeGenerator gen = new RandomShapeGenerator();
  
  public static void main(String[] args) {
    Shape[] s = new Shape[9];
    // Fill up the array with shapes:
    for(int i = 0; i < s.length; i++)
      s[i] = gen.next();
    // Make polymorphic method calls:
    for(Shape shp : s) {
    	System.out.print(shp.getClass().getSimpleName() + " - ");
    	shp.draw();
    }
      
  }
} /* Output:
Triangle.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Square.draw()
Triangle.draw()
Circle.draw()
*///:~