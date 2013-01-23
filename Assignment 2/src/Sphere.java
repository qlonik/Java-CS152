/*
 * Shpere.java      Author: Nikita Volodin (127196)
 * CS152            Assignment 2
 * 
 * This class represents 3d object based on circle. This object has area, volume
 * and circumference of biggest circle
 */

class Sphere extends Circle {

    public Sphere() {
        super();
    }

    public Sphere(double radius) {
        super(radius);
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double getVolume() {
        return 4d / 3d * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public String toString() {
        return "Sphere: radius is " + fmt.format(radius) + "\ncircumference is "
                + fmt.format(super.getPerimeter()) + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
