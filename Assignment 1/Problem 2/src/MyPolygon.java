
import java.awt.Color;
import java.awt.Polygon;

/*
 * MyPolygon.java   Author: Nikita Volodin (127196)
 * CS152            Assignment 1 - Problem #2
 * 
 * Modification of a standard Polygon class. This class now remembers its
 * color and fullness
 */
public class MyPolygon extends Polygon {

    private boolean filled;
    private Color color;

    public MyPolygon() {
        super();
        filled = false;
        color = null;
    }

    public MyPolygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
        filled = false;
        color = null;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFullness(boolean fullness) {
        filled = fullness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
