
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author qlonik
 */
public class PolygonsPanel extends JPanel {

    //polyline
    private int[] xPoints, yPoints;
    private int nPoints;
    //polygon
    private ArrayList<Polygon> polygons;
    private boolean completeness;
    private File file;

    public PolygonsPanel() {
        setPreferredSize(new Dimension(1000, 1000));

        InputListener listener = new InputListener();
        this.addKeyListener(listener);
        this.addMouseListener(listener);

        file = new File("polygons.txt");

        completeness = false;
        polygons = new ArrayList<>();

        nPoints = 0;
        xPoints = new int[100];
        yPoints = new int[100];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (nPoints > 0) {
            g.drawPolyline(xPoints, yPoints, nPoints);
        }
        if (!polygons.isEmpty()) {
            for (Polygon poly : polygons) {
                g.drawPolygon(poly);
            }
        }

    }

    private void saveToFile(Polygon polygon) throws Exception {
        PrintWriter output = new PrintWriter(file);
        for (int i = 0; i < polygon.npoints; i++) {
            output.println(polygon.xpoints[i] + "\t" + polygon.ypoints[i]);
        }
        output.println("-1\t-1");
    }

    private void completePolygon() {
        polygons.add(new Polygon(xPoints, yPoints, nPoints));
        completeness = true;

        nPoints = 0;
        xPoints = new int[100];
        yPoints = new int[100];

        repaint();
    }

    private void quit(int code) {
        if (!completeness) {
            polygons.add(new Polygon(xPoints, yPoints, nPoints));
        }
        try {
            saveToFile(polygons.get(polygons.size()));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.exit(code);
    }

    private class InputListener implements KeyListener, MouseListener {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyChar() + "\t" + e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_C) {
                completePolygon();
            }
//            if (e.getKeyCode() == KeyEvent.VK_Q) {
            if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
                quit(0);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            completeness = false;

            xPoints[nPoints] = e.getPoint().x;
            yPoints[nPoints] = e.getPoint().y;
            nPoints++;

            repaint();
        }

        //not implemented
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
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
