/*
 * Prism.java       Author: Nikita Volodin (127196)
 * CS152            Assignment 2
 * 
 * This class represents 3d prism (prism with all side to be parallel
 * and rectangle in a base).
 * Its base has width and length; prism has a height.
 * This prism has definitions of its area, volume, and perimeter of its base.
 */

public class Prism extends Rectangle {

    private double height;

    public Prism() {
        super();
        height = 0;
    }

    public Prism(double width, double length, double height) {
        super(width, length);
        this.height = height;
    }

    @Override
    public double getArea() {
        return 2 * (width * length + width * height + length * height);
    }

    public double getVolume() {
        return super.getArea() * height;
    }

    @Override
    public String toString() {
        return "Prism: width is " + fmt.format(width) + ", length is " + fmt.format(length)
                + ", heigth is " + fmt.format(height) + "\nperimeter of base is "
                + fmt.format(super.getPerimeter()) + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
