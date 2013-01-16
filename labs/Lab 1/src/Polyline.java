
import java.awt.Point;

/*
 * Lab 1
 * 
 * This class represents polyline.
 * It stores a zero point (first point of polyline) and displacements from 
 * zero point to every point of polylines
 */
public class Polyline {

    //first point of polyline
    private Point zero_point;
    //arrays of relative displacements for next points of polyline
    private int[] rel_x;
    private int[] rel_y;
    //number of points
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

    /**
     * Method creates a copy of existing polyline
     *
     * @return copy of polyline
     */
    public Polyline copy() {
        return new Polyline(this.zero_point, this.rel_x, this.rel_y, this.numPoints);
    }

    /**
     * Method sets zero point
     *
     * @param p zero point
     */
    public void setZeroPoint(Point p) {
        zero_point = p;
    }

    /**
     * Method gets point and save it to zero point if it is first point; or
     * calculate displacement from zero point to current point and save this
     * displacement
     *
     * @param p Point to add
     */
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

    /**
     * Method transfers all displacements between points and zero point to
     * actual points
     *
     * @return array of x coordinates of points of polyline
     */
    public int[] sanitized_x() {
        int[] sanitized = new int[100];
        for (int i = 0; i < numPoints; i++) {
            sanitized[i] = rel_x[i] + zero_point.x;
        }
        return sanitized;
    }

    /**
     * Method transfers all displacements between points and zero point to
     * actual points
     *
     * @return array of y coordinates of points of polyline
     */
    public int[] sanitized_y() {
        int[] sanitized = new int[100];
        for (int i = 0; i < numPoints; i++) {
            sanitized[i] = rel_y[i] + zero_point.y;
        }
        return sanitized;
    }

    /**
     *
     * @return number of points in polyline
     */
    public int getNumPoints() {
        return numPoints;
    }
}
