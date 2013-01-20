
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
        return "Circle: raduis is " + radius
                + "\ncircumference is " + fmt.format(getPerimeter())
                + ", area is " + fmt.format(getArea());
    }
}
