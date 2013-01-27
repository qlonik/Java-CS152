/*
 * AnimationSorting.java    Author: Nikita Volodin (127196)
 * CS152                    Assignment 3
 * 
 * In this problem we exploring sortings. We animate insertion sort
 * in this assignment.
 * This class creates frame where all is shown.
 */

import javax.swing.JFrame;

public class AnimationSorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation sorting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new MainPanel());

        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }
}
