
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
     * Method tries to read file and find max value and it catches possible
     * exceptions from methods
     */
    private void save() {
        try {
            readFile();
            findMax();
        } catch (CancellationException exception) {
            System.err.println("File has not chosen. You cancelled choosing.");
            reopen("File has not chosen.\nDo you want to exit?", true);
        } catch (InputMismatchException exception) {
            System.err.println("Not all content are integers.");
            reopen("Wrong file.\nDo you want to reopen file?", false);
        } catch (IndexOutOfBoundsException exception) {
            System.err.println("Wrong file. Not a text file.");
            reopen("Wrong file.\nDo you want to reopen file?", false);
        }
    }

    /**
     * Shows user a JFileChooser, tries to open file and read its content
     *
     * @throws CancellationException If user pressed cancel in JFileChooser, we
     * ask him to reopen file
     * @throws InputMismatchException If content of text file is not integers,
     * then we ask to reopen file
     */
    private void readFile() throws CancellationException, InputMismatchException {
        File file = null;
        Scanner scan = null;
        JFileChooser chooser = new JFileChooser(new File("./"));

        int returnValue = chooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            throw new CancellationException();
        }

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException exception) {
            System.err.println(exception);
        }

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

    /**
     * If any exception happened, then we ask to reopen file. If true, then we
     * try to read file and max value again
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

        save();
    }
}
