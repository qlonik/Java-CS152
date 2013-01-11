
import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author qlonik
 */
public class MyPolygon extends Polygon{
    private boolean filled;
    private Color color;
    
    public MyPolygon() {
        super();
        filled = false;
    }
    
    public MyPolygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
        filled = false;
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
