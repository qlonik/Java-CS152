
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * This class listens for new asks for connection, and creates new connection
 * for each new client
 */
public class Listener extends Thread {

    ArrayList<Connection> connections = new ArrayList<>();
    ServerSocket ss = null;

    public Listener(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        }

        System.out.println("Inet addr: " + ss.getInetAddress()
                + "\tPort: " + ss.getLocalPort());

        this.setName("Listener");
        this.start();
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Waiting for a client");
                Socket s = ss.accept();
                System.out.println(System.currentTimeMillis()
                        + "\tclient from " + s.getInetAddress());

                Connection conn = new Connection(s, this);
                conn.start();
                connections.add(conn);
            }
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        }
    }
}
