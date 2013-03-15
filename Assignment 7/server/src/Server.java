
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Server.java  Nikita Volodin
 * CS152        Assigment 7
 * 
 * Server starter class. It listens for new connections, and creates each new
 * for each client
 */
public class Server extends Thread {

    AccountStorage storage;
//    ArrayList<Connection> connections = new ArrayList<>();
    ServerSocket ss = null;

    public Server(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        }

        System.out.println("Inet addr: " + ss.getInetAddress()
                + "\tPort: " + ss.getLocalPort());

        this.setName("Server");
        
        //storage for all accounts
        storage = new AccountStorage();
    }

    public boolean createAccount(ArrayList<String> data) {
        Account acc = new Account(data.get(1), data.get(2));
        return storage.add(acc);
    }

    public void deleteAccount() {
    }

    public void deposit() {
    }

    public void withdraw() {
    }

    public void inquire() {
    }

    /**
     * Thread of server listener
     */
    public void run() {
        try {
            while (true) {
                System.out.println("Waiting for a new client");
                Socket s = ss.accept();
                System.out.println("New client from " + s.getRemoteSocketAddress());

                Connection conn = new Connection(s, this);
                conn.start();
//                connections.add(conn);
            }
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t"
                    + ex.getMessage() + "\n" + ex.getStackTrace());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //--BEGIN--
        //setup error log
        final String ERR_PATH = "./log/error.log";
        //<editor-fold defaultstate="collapsed" desc="reassign error log to output file">
        //reassign error log output
        try {
            File errFile = new File(ERR_PATH);
            errFile.createNewFile();
            System.setErr(new PrintStream(errFile));
        } catch (IOException ex) {
            System.err.println("Could not create log file");
        }
        //</editor-fold>
        //--END--

        //--BEGIN--
        //setup output log
        final String OUT_PATH = "./log/out.log";
        //<editor-fold defaultstate="collapsed" desc="reassign output log to file">
        try {
            File outFile = new File(OUT_PATH);
            outFile.createNewFile();
            System.setOut(new PrintStream(outFile));
        } catch (IOException ex) {
            System.err.println("Could not create log file");
        }
        //</editor-fold>
        //--END--

        final int DEFAULT_PORT = 56848;

//        Scanner kb = new Scanner(System.in);
//        
//        int port;
        //<editor-fold defaultstate="expanded" desc="asking for port">
//        try {
//            System.out.print("Type port (default is " + DEFAULT_PORT + "): ");
//            String portString = kb.nextLine(); //the line could be empty
//            System.out.println();
//            port = Integer.parseInt(portString);
//            if (port < 0 || port > 65535) {
//                System.err.println("Port is out of bound. Using default port " + DEFAULT_PORT);
//                port = DEFAULT_PORT;
//            }
//        } catch (NumberFormatException ex) {
//            System.err.println("Input is not an integer. Using default port " + DEFAULT_PORT);
//            port = DEFAULT_PORT;
//        }
//        //</editor-fold>

        int port = DEFAULT_PORT;
        Server server = new Server(port);
        server.start();
    }
}
