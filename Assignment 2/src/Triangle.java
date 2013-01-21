
public class Triangle extends Shape {

    protected double side;

    public Triangle() {
        side = 0;
    }

    public Triangle(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2) * Math.sqrt(3) / 4d;
    }

    @Override
    public double getPerimeter() {
        return 3 * side;
    }

    @Override
    public String toString() {
        return "Triangle: side is " + fmt.format(side)
                + "\nperimeter is " + fmt.format(getPerimeter())
                + ", area is " + fmt.format(getArea());
    }
}
