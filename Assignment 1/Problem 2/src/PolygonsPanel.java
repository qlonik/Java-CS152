
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JPanel;

/*
 * PolygonsPanel.java   Author: Nikita Volodin (127196)
 * CS152                Assignment 1 - Problem #2
 * 
 * JPanel that draws all polygons from file and catches mouse presses. If mouse
 * press was inside polygon, then we fill this polygon with random color
 */
class PolygonsPanel extends JPanel {

    //filepath
    private final String PATHNAME = "canada.dat";
    //storage of modified polygons
    private ArrayList<MyPolygon> polygons;

    public PolygonsPanel() {
        setPreferredSize(new Dimension(1000, 1000));

        InputListener listener = new InputListener();
        addMouseListener(listener);

        polygons = new ArrayList<>();
        readPolygons();
    }

    /**
     * Method draws all polygons from array list
     *
     * @param g Graphics page to draw
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!polygons.isEmpty()) {
            for (MyPolygon poly : polygons) { //every polygon in arraylist
                if (poly.isFilled()) {
                    g.setColor(poly.getColor()); //get color from polygon
                    g.fillPolygon(poly);
                    g.setColor(Color.BLACK); //draw border
                    g.drawPolygon(poly);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawPolygon(poly);
                }
            }
        }
    }

    /**
     * Method opens scanner from file and prints errors if they are exist
     *
     * @param path path to a file
     * @return opened scanner
     */
    private Scanner openScanner(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist");
        }

        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        return scan;
    }

    /**
     * Method reads polygons from a file and saves into arraylist.
     */
    private void readPolygons() {
        Scanner scan = openScanner(PATHNAME);

        if (scan != null) {
            //create temporary variables to store data until "-1 -1"
            int[] xPoints = new int[100];
            int[] yPoints = new int[100];
            int nPoints = 0;

            while (scan.hasNext()) {
                int x, y;

                x = scan.nextInt();
                y = scan.nextInt();
                while (x != -1 && y != -1) {
                    xPoints[nPoints] = x;
                    yPoints[nPoints] = y;
                    nPoints++;

                    x = scan.nextInt();
                    y = scan.nextInt();
                }

                //add new polygon if hit "-1 -1" string
                polygons.add(new MyPolygon(xPoints, yPoints, nPoints));
                nPoints = 0;
            }
        }
    }

    /**
     * MouseClicked event handling. Goes through all polygons in arraylist and
     * if point is inside polygon, then set this polygon to full and generate
     * new color; and then repaint all.
     *
     * @param evt
     */
    private void mouseClick(MouseEvent evt) {
        Point mouse = evt.getPoint();
        for (MyPolygon poly : polygons) {
            if (poly.contains(mouse)) {
                poly.setFullness(true);
                Color rnd = new Color(new Random().nextInt(256),
                        new Random().nextInt(256), new Random().nextInt(256));
                poly.setColor(rnd);
            }
        }

        repaint();
    }

    private class InputListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            mouseClick(e);
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
