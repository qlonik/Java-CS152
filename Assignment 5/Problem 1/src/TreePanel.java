/*
 * TreePanel.java       Author: Nikita Volodin (127196)
 * CS152                Assignment 5 - Problem #1
 * 
 * Panel that draws recursive tree
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class TreePanel extends JPanel {

    // position of first trunk
    final private float START_X = 500;
    final private float START_Y = 750;
    //radius of last circle
    final private float CIRCLE_RADIUS = 2;
    //length and direction of first trunk
    final private float TRUNK_LENGTH = 200;
    final private float TRUNK_ANGLE = +90;
    //relative length and rotation of left branch
    final private float LEFT_BRANCH_MULTIPLIER = 3f / 4f;
    final private float LEFT_BRANCH_RELATIVE_ANGLE = +30;
    //relative length and rotation of right branch
    final private float RIGHT_BRANCH_MULTIPLIER = 2f / 3f;
    final private float RIGHT_BRANCH_RELATIVE_ANGLE = -50;
    //default size of window
    final private Dimension size = new Dimension(1000, 750);

    public TreePanel() {
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        drawTree(g, START_X, START_Y, TRUNK_LENGTH, TRUNK_ANGLE);
    }

    /*
     * *---------------------> x
     * |               x2, y2
     * |                *
     * |              /
     * |           /
     * |        /
     * |     /     angle "a"
     * |   * _________
     * | x1, y1
     * V
     * y
     * 
     * Bottom point is first, upper point is second. We know length of the line, 
     * and we know angle between line and x axis.
     * So, x2 = x1 + len * cos(a)
     * and y2 = y1 - len * sin(a)
     */
    /**
     * Method that recursively draws tree
     * @param g Graphics page where to draw
     * @param startX starting x coordinate of trunk
     * @param startY starting y coordinate of trunk
     * @param trunkLenght length of trunk
     * @param angle angle of trunk
     */
    private void drawTree(Graphics g, float startX, float startY,
            float trunkLenght, float angle) {
        if (trunkLenght < 4) {
            g.setColor(new Color(0x00CC00));
            g.drawOval(Math.round(startX), Math.round(startY),
                    Math.round(CIRCLE_RADIUS), Math.round(CIRCLE_RADIUS));
        } else {
            float endX = startX + trunkLenght * (float) Math.cos(Math.toRadians(angle));
            float endY = startY - trunkLenght * (float) Math.sin(Math.toRadians(angle));

            g.setColor(new Color(0x964B00));
            g.drawLine(Math.round(startX), Math.round(startY),
                    Math.round(endX), Math.round(endY));

            //left branch
            drawTree(g, endX, endY, trunkLenght * LEFT_BRANCH_MULTIPLIER, angle + LEFT_BRANCH_RELATIVE_ANGLE);

            //right branch
            drawTree(g, endX, endY, trunkLenght * RIGHT_BRANCH_MULTIPLIER, angle + RIGHT_BRANCH_RELATIVE_ANGLE);
        }
    }
}
