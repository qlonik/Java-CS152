/*
 * Rectangle.java       Author: Nikita Volodin (127196)
 * CS152                Assignment 2
 * 
 * This class represents 2d rectange with width and length,
 * defined perimeter and area
 */

public class Rectangle extends Shape {

    protected double length, width;

    public Rectangle() {
        length = 0;
        width = 0;
    }

    public Rectangle(double width, double length) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Rectangle: width is " + fmt.format(width) + ", length is "
                + fmt.format(length) + "\nperimeter is " + fmt.format(getPerimeter())
                + ", area is " + fmt.format(getArea());
    }
}
