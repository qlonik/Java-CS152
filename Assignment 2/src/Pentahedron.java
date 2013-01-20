
public class Pentahedron extends Rectangle {

    private double height;

    public Pentahedron() {
        super();
        height = 0;
    }

    public Pentahedron(double width, double length, double height) {
        super(width, length);
        this.height = height;
    }

    @Override
    public double getArea() {
        double heightOfLengthSideTriangle = Math.sqrt(Math.pow(height, 2) + Math.pow(width / 2d, 2));
        double areaOfLengthSideTriangle = 1d / 2d * length * heightOfLengthSideTriangle;
        double heightOfWidthSideTriangle = Math.sqrt(Math.pow(height, 2) + Math.pow(length / 2d, 2));
        double areaOfWidthSideTriangle = 1d / 2d * width * heightOfWidthSideTriangle;

        return super.getArea() + 2 * (areaOfLengthSideTriangle + areaOfWidthSideTriangle);
    }

    public double getVolume() {
        return 1d / 3d * super.getArea() * height;
    }

    @Override
    public String toString() {
        return "Square Pyramid: width is " + width + ", length is " + length
                + ", height is " + height
                + "\nperimeter of base is " + fmt.format(super.getPerimeter())
                + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
