
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final ArrayList<String> shapes = new ArrayList<>();
        shapes.add("Sphere");
        shapes.add("Cylinder");
        shapes.add("Tetrahedron");
        shapes.add("Pentahedron");
        shapes.add("Prism");
        final String file = "input.txt";
        Scanner input = null;
        Shape object = null;
        double radius, width, height, length, side;

        try {
            input = new Scanner(new File(file));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        if (input != null) {
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                String name = line.next();
                switch (shapes.indexOf(name)) {
                    case 0:
                        radius = line.nextDouble();
                        object = new Sphere(radius);
                        break;
                    case 1:
                        radius = line.nextDouble();
                        height = line.nextDouble();
                        object = new Cylinder(radius, height);
                        break;
                    case 2:
                        side = line.nextDouble();
                        height = line.nextDouble();
                        object = new Tetrahedron(side, height);
                        break;
                    case 3:
                        width = line.nextDouble();
                        length = line.nextDouble();
                        height = line.nextDouble();
                        object = new Pentahedron(width, length, height);
                        break;
                    case 4:
                        width = line.nextDouble();
                        length = line.nextDouble();
                        height = line.nextDouble();
                        object = new Prism(width, length, height);
                        break;
                }
                if (object != null) {
                    System.out.println(object);
                    System.out.println();
                }
            }
        }
    }
}
