/*
Suoyi Yang                  PIC 20A JAVA With Applications
ID: 304449872               Spring 2016
Email: suoyiy@ucla.edu      Extra Credit Assignment
Section: 1B

Honesty Pledge:
I, Suoyi Yang, pledge that this is my own independent
work, which conforms to the guidelines of academic honesty as
described in the course syllabus.
List of known bugs:
*/

import java.awt.*;

/**
 * This is the ShapeInformation class that stores the shape 
 * with its colors and strokes.
 * 
 * @author Suoyi Yang
 *
 */
public class ShapeInformation {
	 private Shape shape;
     private Color colorline;
     private Color colorfill;
     private int stroke;

     /**
      * This is the default constructor for the ShapeInformation Class
      * 
      * @param shapes shape of ShapeColor Object
      * @param colors color of Shapecolor Object
      * @param types determines whether shape is filled or not
      * @param strokes determines size of stroke
      */
     public ShapeInformation(Shape shapes, Color colors, int types, int strokes) {
         shape = shapes;
         colorline = colors;
         if (types == 0) 
        	 colorfill = new Color(0,0,0,0);
         else
        	 colorfill = colors;
         stroke = strokes;
     }

     /**
      * This method returns shape of ShapeInformation
      * @return shape of object
      */
     public Shape getShape() {
         return shape;
     }

     /**
      * This method returns fillcolor of ShapeInformation
      * @return color of shape's fill
      */
     public Color getColorfill() {
         return colorfill;
     }
     
     /**
      * This method returns line color of ShapeInformation
      * @return color of shape's outline
      */
     public Color getColorline() {
         return colorline;
     }
     
     /**
      * This method returns stroke size of ShapeInformation
      * @return stroke size of shape
      */
     public int getStroke() {
         return stroke;
     }
}
