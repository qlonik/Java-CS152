/*
 * Circle.java      Author: Nikita Volodin (127196)
 * CS152            Assignment 2
 * 
 * This class represents 2d circle with defined perimeter (circumference) 
 * and area
 */

class Circle extends Shape {

    protected double radius;

    public Circle() {
        radius = 0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle: raduis is " + fmt.format(radius)
                + "\ncircumference is " + fmt.format(getPerimeter())
                + ", area is " + fmt.format(getArea());
    }
}
