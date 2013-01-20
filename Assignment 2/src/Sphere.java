
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
        return "Sphere: radius is " + radius
                + "\narea is " + fmt.format(getArea())
                + "\nvolume is " + fmt.format(getVolume());
    }
}
