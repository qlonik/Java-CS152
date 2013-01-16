
import javax.swing.JFrame;

/**
 * Driver for lab 1. It creates JFrame and adds PolygonsPanel
 *
 * @author qlonik
 */
public class Main {

    /**
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Poligon mover");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new PolygonsPanel());

        frame.pack();
        frame.setVisible(true);
    }
}
