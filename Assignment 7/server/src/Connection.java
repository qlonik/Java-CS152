
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * This class represents connection with one client
 */
public class Connection extends Thread {

    private Socket s;
    private Scanner in;
    private PrintStream out;
    private Listener parent;

    public Connection() {
        s = null;
        
        in = null;
        out = null;
    }

    public Connection(Socket s, Listener parent) throws IOException {
        this.s = s;
        
        in = new Scanner(s.getInputStream());
        in.useDelimiter(";");
        out = new PrintStream(s.getOutputStream());

        this.parent = parent;
    }

    @Override
    public void run() {
        try {
            String line;
            do {
                line = in.next();
                System.out.println("Received: " + line);
            } while (!line.equals("CLOSE"));

            s.close();
            System.out.println("Client disconnected");
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        } catch (Exception ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        }
    }
}
