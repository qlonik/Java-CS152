
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
        return "Rectangle: width is " + width + ", length is " + length
                + "\nperimeter is " + fmt.format(getPerimeter())
                + ", area is " + fmt.format(getArea());
    }
}
