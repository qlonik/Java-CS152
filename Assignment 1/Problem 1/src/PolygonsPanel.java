
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println(ex);
        }

        completeness = true;
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

    private void saveToFile(Polygon polygon) {
        PrintWriter output = null;

        try {
            //try open file
            output = new PrintWriter(file);

            //write all points of polygon into file and add "-1 -1" after polygon
            for (int i = 0; i < polygon.npoints; i++) {
                output.println(polygon.xpoints[i] + "\t" + polygon.ypoints[i]);
            }
            output.println("-1\t-1");
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        output.close();
    }

    private void completePolygon() {
        completeness = true;

        Polygon tmpPolygon = new Polygon(xPoints, yPoints, nPoints);
        polygons.add(tmpPolygon);
        saveToFile(tmpPolygon);

        nPoints = 0;
        xPoints = new int[100];
        yPoints = new int[100];

        repaint();
    }

    private void quit(int code) {
        if (!completeness) {
            Polygon tmpPolygon = new Polygon(xPoints, yPoints, nPoints);
            saveToFile(tmpPolygon);
        }

        System.exit(code);
    }

    private void keyPress(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_C) {
            completePolygon();
        }
//        if (e.getKeyCode() == KeyEvent.VK_Q) {
        if (evt.getKeyChar() == 'q' || evt.getKeyChar() == 'Q') {
            quit(0);
        }
    }

    private void mouseClick(MouseEvent evt) {
        if (completeness) {
            completeness = false;
        }

        xPoints[nPoints] = evt.getPoint().x;
        yPoints[nPoints] = evt.getPoint().y;
        nPoints++;

        repaint();
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
