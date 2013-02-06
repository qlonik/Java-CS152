/*
 * AnimalViewer.java    Author: Nikita Volodin (127196)
 * CS152                Assignment 4
 * 
 * Driver class for program. This class creates main panel and adds it.
 * This program is showing list of animals, their pictures, and can play
 * their noises.
 */

import javax.swing.JFrame;

public class AnimalViewer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animal viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new MainPanel());
        
        frame.setVisible(true);
        frame.pack();
    }
}
