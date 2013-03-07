/*
 * Aquarium.java        Nikita Volodin (127196)
 * CS152                Assignment 6
 * 
 * This is main panel that creates some fishes and starts threads with fishes
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class Aquarium extends JPanel {

    private int NUM_OF_FISHES = 3;
    private Fish[] fishes = new Fish[NUM_OF_FISHES];

    /**
     * Constructor of an Aquarium class
     */
    public Aquarium() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.cyan);
        fishes[0] = new Fish(130, 50, Color.red, 5, this);
        fishes[1] = new Fish(230, 400, Color.yellow, 30, this);
        fishes[2] = new Fish(600, 200, Color.blue, 20, this);
    }

    /**
     * System call that paints graphics
     * @param g Graphics page provided by Java
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < NUM_OF_FISHES; i++) {
            fishes[i].draw(g);
        }

        //wait until graphics inits
        //and after that start threads
        for (int i = 0; i < NUM_OF_FISHES; i++) {
            //if statement makes sure that each thread started only once
            if (fishes[i].getState() == Thread.State.NEW) {
                fishes[i].start();
            }
        }
    }
}
