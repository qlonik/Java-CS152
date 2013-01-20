
public class Prism extends Rectangle {

    private double height;

    public Prism() {
        super();
        height = 0;
    }

    public Prism(double width, double length, double height) {
        super(width, length);
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0;
    }

    public double getVolume() {
        return super.getArea() * height;
    }

    @Override
    public String toString() {
        return "Prism: width is " + width + ", length is " + length
                + ", heigth is " + height + "\nperimeter of base is "
                + fmt.format(super.getPerimeter()) + ", area is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
