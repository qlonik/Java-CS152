/*
 * Prism.java       Author: Nikita Volodin (127196)
 * CS152            Assignment 2
 * 
 * This class represents 3d pentahedron (pyramid with rectange in a base).
 * Its base has width and length; pyramid has a height.
 * This pyramid has definitions of its area, volume, and perimeter of its base.
 */

public class Pentahedron extends Rectangle {

    private double height;

    public Pentahedron() {
        super();
        height = 0;
    }

    public Pentahedron(double width, double length, double height) {
        super(width, length);
        this.height = height;
    }

    @Override
    public double getArea() {
        //height and then area of a side triange
        //with a base "length" and a top point of pyramid
        double heightOfLengthSideTriangle = Math.sqrt(Math.pow(height, 2) + Math.pow(width / 2d, 2));
        double areaOfLengthSideTriangle = 1d / 2d * length * heightOfLengthSideTriangle;
        //height and then area of triange with a base "width" and a top point of pyramid
        double heightOfWidthSideTriangle = Math.sqrt(Math.pow(height, 2) + Math.pow(length / 2d, 2));
        double areaOfWidthSideTriangle = 1d / 2d * width * heightOfWidthSideTriangle;

        //all area of pyramid
        return super.getArea() + 2 * (areaOfLengthSideTriangle + areaOfWidthSideTriangle);
    }

    public double getVolume() {
        return 1d / 3d * super.getArea() * height;
    }

    @Override
    public String toString() {
        return "Square Pyramid: width is " + fmt.format(width) + ", length is "
                + fmt.format(length) + ", height is " + fmt.format(height)
                + "\nperimeter of base is " + fmt.format(super.getPerimeter())
                + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
