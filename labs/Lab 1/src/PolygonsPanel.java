
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * JPanel that draw a polyline and after "C" button it changes state and copies
 * current polyline in the different zero point
 *
 * @author qlonik
 */
public class PolygonsPanel extends JPanel {

    //polyline
    private ArrayList<Polyline> polylines;
    private Polyline tmp;
    //true if button "C" was pressed
    private boolean cPress;

    public PolygonsPanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setFocusable(true);

        InputListener listener = new InputListener();
        this.addKeyListener(listener);
        this.addMouseListener(listener);

        cPress = false;

        polylines = new ArrayList<>();
        tmp = new Polyline();
    }

    /**
     * Method draw only first polyline if "C" button was not pressed and draws
     * all polylines from arraylist if "C" button was pressed (including first
     * one)
     *
     * @param g Graphics page to draw polylines
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!cPress) {
            g.drawPolyline(tmp.sanitized_x(), tmp.sanitized_y(), tmp.getNumPoints());
        }
        if (!polylines.isEmpty()) {
            for (int i = 0; i < polylines.size(); i++) {
                Polyline draw = polylines.get(i);
                g.drawPolyline(draw.sanitized_x(), draw.sanitized_y(), draw.getNumPoints());
            }
        }
    }

    /**
     * KeyPressed event handling
     *
     * @param evt KeyEvent that happened
     */
    private void keyPress(KeyEvent evt) {
        // if "C" button was pressed we save polyline that was created before
        // and set flag to true (was pressed)
        if (evt.getKeyCode() == KeyEvent.VK_C) {
            polylines.add(tmp);
            cPress = true;
        }
    }

    /**
     * MouseClicked event handling
     *
     * @param evt MouseEvent that happened
     */
    private void mouseClick(MouseEvent evt) {
        if (!cPress) {
            // if "C" button was not pressed yet we add all points to a new
            // polyline and repaint this polyline
            tmp.addPoint(evt.getPoint());
            repaint();
        } else if (cPress) {
            // if "C" button was pressed, we create a copy of this polyline
            // with changed zero point, add this polyline and redraw everything
            Polyline copy = tmp.copy();
            copy.setZeroPoint(evt.getPoint());
            polylines.add(copy);

            repaint();
        }
    }

    private class InputListener implements KeyListener, MouseListener {

        @Override
        public void keyPressed(KeyEvent e) {
            keyPress(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            mouseClick(e);
        }

        //not implemented
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
