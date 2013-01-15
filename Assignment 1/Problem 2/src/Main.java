
import javax.swing.JFrame;

/*
 * Main.java    Author: Nikita Volodin (127196)
 * CS152        Assignment 1 - Problem #2
 * 
 * Driver class for a 2nd problem. It creates JFrame and adds PolygonsPanel
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
