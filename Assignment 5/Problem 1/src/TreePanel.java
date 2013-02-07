
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class TreePanel extends JPanel{
    
    final private float START_X = 500;
    final private float START_Y = 750;
    final private float CIRCLE_RADIUS = 2;
    final private float TRUNK_LENGTH = 200;
    final private float TRUNK_ANGLE = +90;
    final private float LEFT_BRANCH_MULTIPLIER = 3f / 4f;
    final private float LEFT_BRANCH_RELATIVE_ANGLE = +30;
    final private float RIGHT_BRANCH_MULTIPLIER = 2f / 3f;
    final private float RIGHT_BRANCH_RELATIVE_ANGLE = -50;
    
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
     * 
     * @param g
     * @param startX
     * @param startY
     * @param trunkLenght
     * @param angle 
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
