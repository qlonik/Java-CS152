
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

/**
 *
 * @author qlonik
 */
class PolygonsPanel extends JPanel {
    
    //filepath
    private final String PATHNAME = "canada.dat";
    //polygons strorage
    private ArrayList<MyPolygon> polygons;

    public PolygonsPanel() {
        setPreferredSize(new Dimension(1000, 1000));
        
        InputListener listener = new InputListener();
        addMouseListener(listener);
        
        polygons = new ArrayList<>();
        readPolygons();
        
        
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        if (!polygons.isEmpty()) {
            for (MyPolygon poly : polygons) {
                if (poly.isFilled()) {
                    g.setColor(poly.getColor());
                    g.fillPolygon(poly);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(poly);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawPolygon(poly);
                }
            }
        }
    }
    
    private Scanner openScanner(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist");
        }
        
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        
        return scan;
    }
    
    private void readPolygons() {
        Scanner scan = openScanner(PATHNAME);
        
        if (scan != null) {
            int [] xPoints = new int [100];
            int [] yPoints = new int [100];
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
                
                polygons.add(new MyPolygon(xPoints, yPoints, nPoints));
                nPoints = 0;
            }
        }
    }
    
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

    private class InputListener implements MouseListener{

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
