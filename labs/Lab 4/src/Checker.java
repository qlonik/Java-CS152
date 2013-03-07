
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Checker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("BookShipmentManifest.txt"));
        ArrayList<String> isbnArrayList = new ArrayList<>();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String isbn = line.substring(0, line.indexOf("#"));
            isbnArrayList.add(isbn);
        }
        try {
            check(isbnArrayList.get(3));
        } catch (ISBNFormatException ex) {
            System.out.println(ex);
        }
    }

    public static boolean check(String isbn) throws ISBNFormatException {
        boolean result = true;

        if (isbn.length() != 10) {
            result = false;
            throw new ISBNFormatException("Invalid ISBN-10 Length");
        }

        String nineFirst = isbn.substring(0, 9);
        String lastDigit = isbn.substring(9);

        int i = 0;
        boolean f = true;
        while (i < nineFirst.length()) {
            try {
                Integer.parseInt(nineFirst.substring(i, i + 1));
            } catch (NumberFormatException ex) {
                throw new ISBNFormatException("Invalid ISBN-10 Content");
            }
            i++;
        }
        try {
            if (!lastDigit.toUpperCase().equals("X")
                    && !(Integer.parseInt(lastDigit) >= 0 && Integer.parseInt(lastDigit) <= 9)) {
                throw new ISBNFormatException("Invalid ISBN-10 Content");
            }
        } catch (NumberFormatException ex) {
            throw new ISBNFormatException("Invalid ISBN-10 Content");
        }
        
        int sum = 0;
        for (int j = nineFirst.length(); j > 1; j--) {
            sum += (j + 1) * Integer.parseInt(nineFirst.substring(j-1, j));
        }

        return result;
    }
}
