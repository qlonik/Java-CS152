
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 23456;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        System.out.println("started");

        if (ss != null) {
            while (true) {
                try {
                    System.out.println("waiting for client");
                    Socket s = ss.accept();

                    System.out.println("Client connected from " + s.getRemoteSocketAddress());

                    Scanner scan = new Scanner(s.getInputStream());
                    scan.nextLine();

                    PrintWriter output = new PrintWriter(s.getOutputStream());
                    Scanner fileScan = new Scanner(new File("./index.html"));

                    while (fileScan.hasNextLine()) {
                        String line = fileScan.nextLine();
                        output.println(line);
                    }

//                    if (s != null) {
//                        Connection conn = new Connection(s);
//                        conn.start();
//                    }
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}
