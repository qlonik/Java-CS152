
/**
 * In this lab we transforming text to Morse code
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class MorseCode {

    private final String path = "translation.txt";
    private String[] table;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MorseCode coding = new MorseCode();
        File file = new File("SMart.txt");
        Scanner scan = null;

        JFileChooser chooser = new JFileChooser(new File("./"));
        int returnResult = chooser.showOpenDialog(null);
        if (returnResult == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException exception) {
            System.err.println(exception);
        }

        if (scan != null) {
            while (scan.hasNext()) {
                String line = scan.nextLine();
                line = line.toUpperCase();
                for (int i = 0; i < line.length(); i++) {
                    Character replaceCharacter = line.charAt(i);
                    if (replaceCharacter == ' ') {
                        System.out.print('/');
                    } else {
                        System.out.print(' ' + coding.replace(replaceCharacter) + ' ');
                    }
                }
                System.out.println();
            }
        }
    }

    public MorseCode() {
        table = new String[255];
        readFile();
    }

    private void readFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException exception) {
            System.err.println(exception);
        }

        if (scan != null) {
            while (scan.hasNext()) {
                String readCharacter = scan.next();
                String readCode = scan.next();
                table[readCharacter.charAt(0)] = readCode;
            }
        }
    }

    public String replace(Character inputCharacter) {
        if (table[inputCharacter] == null) {
            return "";
        } else {
            return table[inputCharacter];
        }
    }
}
