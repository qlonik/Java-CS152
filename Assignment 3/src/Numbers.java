/*
 * Numbers.java     Author: Nikita Volodin (127196)
 * CS152            Assignment 3
 * 
 * This class reads input file and saves it content in array list. 
 * This class also handle possible exceptions.
 */

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Numbers {

    private Component parent;
    private ArrayList<Integer> numbers;
    private int max;

    /**
     * Constructor. It opens file, reads it, and find max value; it catches
     * errors
     *
     * @param parent Parent component for JFileChooser and JOptionPane
     */
    public Numbers(Component parent) {
        this.parent = parent;
        numbers = new ArrayList<>();
        max = -1;

        save();
    }

    /**
     *
     * @return ArrayList of Integers from file
     */
    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    /**
     *
     * @return max value of ArrayList
     */
    public int getMax() {
        return max;
    }

    /**
     * Method tries to read file and find max value until we actually can do it
     * without any exceptions
     */
    private void save() {
        //result of exception catching
        int result;
        do {
            try {
                readFile();
                findMax();
                result = 0;
            } catch (CancellationException exception) {
                System.err.println("File has not chosen. You cancelled choosing.");
                result = 1;
            } catch (FileNotFoundException exception) {
                System.err.println("File has not chosen. File does not exist.");
                result = 2;
            } catch (InputMismatchException exception) {
                System.err.println("Not all content are integers.");
                result = 3;
            } catch (IndexOutOfBoundsException exception) {
                System.err.println("Wrong file. Not a text file.");
                result = 4;
            } catch (Exception exception) {
                System.err.println(exception);
                result = 5;
            }

            //exception handling
            if (result == 1) {
                reopen("Cancel was pressed.\nDo you want to exit?", true);
            } else if (result == 2) {
                reopen("File does not exist.\nDo you want to reopen?", false);
            } else if (result == 3) {
                reopen("Wrong file.\nDo you want to reopen file?", false);
            } else if (result == 4) {
                reopen("Wrong file.\nDo you want to reopen file?", false);
            } else if (result == 5) {
                reopen("Exception happened.\nDo you want to continue?", false);
            }
        } while (result != 0);
    }

    /**
     * If any exception happened, then we ask user for confirmation of our next
     * steps. If we do not continue, then we exit from program
     *
     * @param msg message in confirm dialog
     * @param inverse If true - YES option exits, if false - NO option exits
     */
    private void reopen(String msg, boolean inverse) {
        int returnValue = JOptionPane.showConfirmDialog(parent, msg);
        if (!inverse && (returnValue == JOptionPane.NO_OPTION
                || returnValue == JOptionPane.CANCEL_OPTION)) {
            System.exit(0);
        } else if (inverse && returnValue == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Shows user a JFileChooser, tries to open file and read its content
     *
     * @throws CancellationException If user pressed cancel in JFileChooser, we
     * ask him to reopen file
     * @throws InputMismatchException If content of text file is not integers,
     * then we ask to reopen file
     * @throws FileNotFoundException If user inputs name file into open box, but
     * file does not exist, then this exception is called
     */
    private void readFile() throws CancellationException, InputMismatchException,
            FileNotFoundException {
        File file = null;
        JFileChooser chooser = new JFileChooser(new File("./"));

        int returnValue = chooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            throw new CancellationException();
        }

        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            numbers.add(scan.nextInt());
        }
    }

    /**
     * Find max value from input ArrayList/file
     *
     * @throws IndexOutOfBoundsException If ArrayList is empty we assume that
     * opened file is not text file
     */
    private void findMax() throws IndexOutOfBoundsException {
        max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > max) {
                max = numbers.get(i);
            }
        }
    }
}
