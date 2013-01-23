/*
 * Shape.java       Author: Nikita Volodin (127196)
 * CS152            Assignment 2
 * 
 * This is abstract class that represents 2d shape with area and
 * perimeter (circumference for circle)
 */

import java.text.DecimalFormat;

public abstract class Shape {

    DecimalFormat fmt = new DecimalFormat("#.00");

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public abstract String toString();
}
