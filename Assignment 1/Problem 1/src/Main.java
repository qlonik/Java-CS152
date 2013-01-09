
import javax.swing.JFrame;

/**
 *
 * @author qlonik
 */
public class Main {

    public static void main (String[] args) {
        JFrame frame = new JFrame("Poligons Saving");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new PolygonsPanel());
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
