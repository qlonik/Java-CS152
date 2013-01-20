
import java.text.DecimalFormat;

public abstract class Shape {

    DecimalFormat fmt = new DecimalFormat("#.00");

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public abstract String toString();
}
