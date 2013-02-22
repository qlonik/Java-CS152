/*
 * RecursiveTree.java       Author: Nikita Volodin (127196)
 * CS152                    Assignment 5 - Problem #1
 * 
 * Driver for a program that draws recursive tree
 */

import javax.swing.JFrame;

public class RecursiveTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Recursive Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new TreePanel());

        frame.setVisible(true);
        frame.pack();
    }
}
