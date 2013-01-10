
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
    
    //file path
    private final String PATHNAME = "canada.dat";
    //input
    private File file;
    //polygon
    private ArrayList<Polygon> polygons;

    public PolygonsPanel() {
        setPreferredSize(new Dimension(1000, 1000));
        
        InputListener listener = new InputListener();
        addMouseListener(listener);
        
        polygons = new ArrayList<>();
        openFile();
        readPolygons();
        
        
    }
    
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        if (!polygons.isEmpty()) {
            for (Polygon poly : polygons) {
                if (poly.isFilled()) {
                    Color rnd = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                    g.setColor(rnd);
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
    
    private void openFile(){
        file = new File(PATHNAME);
        if (!file.exists()) {
            System.out.println("File does not exist");
        }
    }
    
    private void readPolygons() {
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        
        if (scan != null) {
            int [] xPoints = new int [100];
            int [] yPoints = new int [100];
            int nPoints = 0;
            
            while (scan.hasNext()) {
                int x, y;
                
                do {
                    x = scan.nextInt();
                    y = scan.nextInt();
                    if (x != -1 && y != -1) {
                        xPoints[nPoints] = x;
                        yPoints[nPoints] = y;
                        nPoints++;
                    }
                } while (x != -1 && y != -1);
                
                polygons.add(new Polygon(xPoints, yPoints, nPoints));
                nPoints = 0;
            }
        }
    }
    
    private void mouseClick(MouseEvent evt) {
        Point mouse = evt.getPoint();
        for (Polygon poly : polygons) {
            if (poly.contains(mouse)) {
                poly.setFullness(true);
            } else {
                poly.setFullness(false);
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
