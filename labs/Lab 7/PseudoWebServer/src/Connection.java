
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread {

    private Socket s;
    final private String path = "index.html";

    public Connection(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(s.getOutputStream());
            File file = new File(path);

            Scanner scan  = new Scanner(s.getInputStream());
            scan.nextLine();
            
            System.out.println("Start printing");

            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                output.println(line);
            }
            
            System.out.println("Closing connection");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
