
public class Tetrahedron extends Triangle {

    private double height;

    public Tetrahedron() {
        super();
        height = 0;
    }

    public Tetrahedron(double side, double height) {
        super(side);
        this.height = height;
    }

    @Override
    public double getArea() {
        return 4 * super.getArea();
    }

    public double getVolume() {
        return 1d / 3d * height * super.getArea();
    }

    @Override
    public String toString() {
        return "Triangle Pyramid: side is " + side + ", height is " + height
                + "\nperimeter of base is " + fmt.format(super.getPerimeter())
                + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
