
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
//        in.useDelimiter("\\r\\n");
//        in.useDelimiter(";");

        out = new PrintStream(s.getOutputStream());

        this.parent = parent;

//        listen();
    }

    @Override
    public void run() {
        try {
            out.println("To close a connection type \"CLOSE;\"");

            String line = "";
            while (!line.equals("CLOSE")) {
                line = in.nextLine();
                out.println("You sent: \"" + line + "\"");
                System.out.println(line);
            }

            out.println("Closing connection...");
            s.close();
            System.out.println("client disconnected");
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        } catch (Exception ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        }
    }
//    private void listen() {
//        out.print("hi");
////        System.out.println(in);
//        while (in.hasNext()) {
//            String line = in.nextLine();
//            System.out.println(line);
//        }
//    }
}
