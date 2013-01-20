
public class Cylinder extends Circle {

    private double height;

    public Cylinder() {
        super();
        height = 0;
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * Math.pow(radius, 2) + 2 * Math.PI * radius * height;
    }

    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cylinder: base radius is " + radius + ", height is " + height
                + "\nperimeter of base is " + fmt.format(super.getPerimeter())
                + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
