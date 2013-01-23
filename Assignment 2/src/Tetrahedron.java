/*
 * Tetrahedron.java     Author: Nikita Volodin (127196)
 * CS152                Assignment 2
 * 
 * This class represents 3d tetrahedron (pyramid with triangle as a base).
 * Its base has all equal side. This tetrahedron has definition of its area and
 * volume and perimeter of a base
 */

public class Tetrahedron extends Triangle {

    private double height;

    public Tetrahedron() {
        super();
        height = 0;
    }

    public Tetrahedron(double side, double height) {
        super(side);
        this.height = height;
    }

    @Override
    public double getArea() {
        //height and then area of a side triangle
        double heightOfSideTriangle = Math.sqrt(Math.pow(height, 2)
                + Math.pow(1d / 3d * side * Math.sqrt(3) / 2d, 2));
        double areaSide = 1d / 2d * heightOfSideTriangle * side;

        //total area of pyramid
        return super.getArea() + 3 * areaSide;
    }

    public double getVolume() {
        return 1d / 3d * height * super.getArea();
    }

    @Override
    public String toString() {
        return "Triangle Pyramid: side is " + fmt.format(side) + ", height is "
                + fmt.format(height) + "\nperimeter of base is "
                + fmt.format(super.getPerimeter()) + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
