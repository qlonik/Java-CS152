
import javax.swing.JFrame;

/*
 * Main.java    Author: Nikita Volodin (127196)
 * CS152        Assignment 1 - Problem #1
 * 
 * Driver class for a first problem in Assignment.
 * It creates JFrame and adds PolygonPanel
 */
public class Main {

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Poligon saver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new PolygonsPanel());

        frame.pack();
        frame.setVisible(true);
    }
}
