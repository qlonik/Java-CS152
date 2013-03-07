/*
 * Fish.java        Nikita Volodin (127196)
 * CS152            Assignment 6
 * 
 * This class represents a fish object. It has a thread capability. It can draw
 * itself on the graphics page and in thread it updates its coordinate
 */

import java.awt.Color;
import java.awt.Graphics;

public class Fish extends Thread {

    private final int MULTIPLIER = 8;
    //relative displacements for each next point from previous
    private final double[] SHAPE_X_REL = {+2, +1.5, +3.5, +1, +1, +3, +2.5, -1.5,
        +1.5, -2.5, -3, -1, -1, -3.5, -1.5, -2};
    private final double[] SHAPE_Y_REL = {-0.5, -1, -0.5, -1, +1, +1, -1, +2,
        +2, -1, +1, +1, -1, -0.5, -1, -0.5};
    private Color color = null;
    private int speed;
    private boolean direction = true; // true to the left, false to the right
    private int[] shapeX = new int[SHAPE_X_REL.length + 1];
    private int[] shapeY = new int[SHAPE_Y_REL.length + 1];
    private Aquarium parent;

    /**
     * Constructor for a Fish object
     *
     * @param x0 Default x position of nose
     * @param y0 Default y position of nose
     * @param color Color of fish
     * @param speed Speed of fish
     * @param parent Aquarium of this fish
     */
    public Fish(int x0, int y0, Color color, int speed, Aquarium parent) {
        shapeX[0] = x0;
        shapeY[0] = y0;

        this.color = color;
        this.speed = speed;

        this.parent = parent;

        getShape();
    }

    /**
     * System method for Thread class. It updates x coordinate of a fish
     * depending upon direction of it.
     */
    public void run() {
        while (true) {
            //if we hit any of bounds we flip fish to a different direction
            if (shapeX[0] < 0 || shapeX[0] > parent.getWidth()) {
                direction = !direction;
                //if it just turned to right then we set nose position to lenght of the fish
                //otherwise we set nose position to width minus length of fish
                if (!direction) {
                    shapeX[0] = (int) (MULTIPLIER * 14.5);
                } else {
                    shapeX[0] = parent.getSize().width - (int) (MULTIPLIER * 14.5);
                }
            }
            
            //if direction true - to the left; if false - to the right
            if (direction) {
                shapeX[0] -= 1;
            } else {
                shapeX[0] += 1;
            }
            
            //update x and y coordinates of all points of fish after changing of
            //nose position, redraw fish and wait some time
            getShape();
            parent.repaint();
            try {
                sleep(speed);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Method draws fish on a provided graphics page
     * @param g provided Graphics page
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(shapeX, shapeY, shapeX.length);
    }

    /**
     * Update x and y coordinates of all points of the fish
     */
    private void getShape() {
        if (direction) {
            for (int i = 0; i < SHAPE_X_REL.length; i++) {
                shapeX[i + 1] = (int) (shapeX[i] + MULTIPLIER * SHAPE_X_REL[i]);
            }
        } else {
            for (int i = 0; i < SHAPE_X_REL.length; i++) {
                shapeX[i + 1] = (int) (shapeX[i] - MULTIPLIER * SHAPE_X_REL[i]);
            }
        }

        for (int i = 0; i < SHAPE_Y_REL.length; i++) {
            shapeY[i + 1] = (int) (shapeY[i] + MULTIPLIER * SHAPE_Y_REL[i]);
        }
    }
}
