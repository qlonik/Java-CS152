
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringTree tree = new StringTree();

        File inFile = new File("input.txt");
        Scanner input = null;
        try {
            input = new Scanner(inFile);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        if (input != null) {
            while (input.hasNext()) {
                String line = input.nextLine();
                tree.add(line);
            }
        }
        
        Scanner kb = new Scanner(System.in);
        String search;
        do {
            System.out.print("Input a string to search: ");
            search = kb.nextLine();
            System.out.println();
            
            int steps = tree.search(search);
            if (steps == -1) {
                System.out.println("Could not find this string");
            } else {
                System.out.println("Found it with " + steps + " comparisons");
            }
        } while (!search.equals("ZZZ"));

        System.out.println(tree);
    }
}
