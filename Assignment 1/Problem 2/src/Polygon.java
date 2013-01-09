
/**
 *
 * @author qlonik
 */
public class Polygon extends java.awt.Polygon{
    private boolean filled;
    
    public Polygon() {
        super();
        filled = false;
    }
    
    public Polygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
        filled = false;
    }
    
    public boolean isFilled() {
        return filled;
    }
    
    public void setFullness(boolean fullness) {
        filled = fullness;
    }
}
