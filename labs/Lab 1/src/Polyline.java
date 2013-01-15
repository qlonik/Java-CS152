
import java.awt.Point;

public class Polyline {

    private Point zero_point;
    private int[] rel_x;
    private int[] rel_y;
    private int numPoints;

    public Polyline() {
        rel_x = new int[100];
        rel_y = new int[100];
        zero_point = new Point(0, 0);
        numPoints = 0;
    }

    public Polyline(Point zero_point, int[] rel_x, int[] rel_y, int numPoints) {
        this.zero_point = zero_point;
        this.rel_x = rel_x;
        this.rel_y = rel_y;
        this.numPoints = numPoints;
    }

    public Polyline copy() {
        return new Polyline(this.zero_point, this.rel_x, this.rel_y, this.numPoints);
    }

    public void setZeroPoint(Point p) {
        zero_point = p;
    }

    public void addPoint(Point p) {
        if (numPoints == 0) {
            zero_point = p;
            numPoints++;
            rel_x[numPoints] = 0;
            rel_y[numPoints] = 0;
        } else {
            rel_x[numPoints] = p.x - zero_point.x;
            rel_y[numPoints] = p.y - zero_point.y;
            numPoints++;
        }
    }

    public int[] sanitized_x() {
        int[] sanitized = new int[100];
        for (int i = 0; i < numPoints; i++) {
            sanitized[i] = rel_x[i] + zero_point.x;
        }
        return sanitized;
    }

    public int[] sanitized_y() {
        int[] sanitized = new int[100];
        for (int i = 0; i < numPoints; i++) {
            sanitized[i] = rel_y[i] + zero_point.y;
        }
        return sanitized;
    }

    public int getNumPoints() {
        return numPoints;
    }
}
