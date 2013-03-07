/*
 * Driver.java          Nikita Volodin (127196)
 * CS152,               Assignment 6
 * 
 * In this problem we simulate aquarium with fishes. We create each thread 
 * for a new fish and update it's state in thread
 */

import javax.swing.JFrame;

public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aquarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new Aquarium());

        frame.setVisible(true);
        frame.pack();
    }
}
