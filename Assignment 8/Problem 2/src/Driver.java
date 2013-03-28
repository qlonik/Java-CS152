/*
 * Driver.java      author: Nikita Volodin (127196)
 * CS152            Assignment 8 - Problem 2
 * 
 * In this problem we create binary tree, search in it, and print it in order
 * after all
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringTree tree = new StringTree();

        File inFile = new File("as8-words.txt");
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

        //asking user to search in the tree
        //until he enters "ZZZ"
        Scanner kb = new Scanner(System.in);
        String search;
        do {
            System.out.print("Input a string to search (ZZZ to exit): ");
            search = kb.nextLine();
            System.out.println();

            int steps = tree.search(search);
            if (steps == 0) {
                System.out.println("Could not find this string");
            } else {
                System.out.println("Found it with "
                        + ((steps == 1) ? " 1 comparison" : "" + steps + " comparisons"));
            }
        } while (!search.equals("ZZZ"));

        //print sorted tree before exit
        System.out.println(tree);
    }
}
