
import javax.swing.JFrame;


/**
 *
 * @author qlonik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon filler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new PolygonsPanel());
        
        frame.pack();
        frame.setVisible(true);
    }
}
