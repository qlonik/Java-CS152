
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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

    /**
     * Current time in format "dd MMM yyyy HH:mm:ss"
     *
     * @return String representation of current time
     */
    public String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

        return sdf.format(cal.getTime());
    }

    /**
     *
     * @param msg Message from client
     * @return Server's reaction to this input
     */
    public String parseMsg(String msg) {
        ArrayList<String> receivedData = decode(msg);
        ArrayList<String> sendingData = new ArrayList<>();

        String command = receivedData.get(0);
        int ID = Integer.parseInt(receivedData.get(1));
        double balance;

        switch (command) {
            case "CREATE":
                //create new account
                System.out.print(getTime() + "\tCREATING ACCOUNT ID#" + ID + "\t\t");

                balance = Double.parseDouble(receivedData.get(2));
                sendingData = createAccount(ID, balance);

                if (sendingData.get(0).equals("SUCCESS")) {
                    System.out.println("Successful operation");
                } else {
                    System.out.println("Failed to create. "
                            + "This account number already exist");
                }
                break;

            case "DELETE":
                //delete account
                System.out.print(getTime() + "\tDELETING ACCOUNT ID#"
                        + ID + "\t\t");

                sendingData = deleteAccount(ID);

                if (sendingData.get(0).equals("SUCCESS")) {
                    System.out.println("Successful operation");
                } else {
                    System.out.println("Failed to delete. "
                            + "This account does not exist");
                }
                break;

            case "DEPOSIT":
                //deposit amount to account
                System.out.print(getTime() + "\tDEPOSITING TO ACCOUNT ID#"
                        + ID + "\t\t");

                balance = Double.parseDouble(receivedData.get(2));
                sendingData = deposit(ID, balance);

                if (sendingData.get(0).equals("SUCCESS")) {
                    System.out.println("Successful operation");
                } else {
                    System.out.println("Failed to deposit. "
                            + "This account does not exist");
                }
                break;

            case "WITHDRAW":
                //withdraw amount from account
                System.out.print(getTime() + "\tWITHDRAWING FROM ACCOUNT ID#"
                        + ID + "\t\t");

                balance = Double.parseDouble(receivedData.get(2));
                sendingData = withdraw(ID, balance);

                if (sendingData.get(0).equals("SUCCESS")) {
                    System.out.println("Successful operation");
                } else if (sendingData.get(1).equals("3")) {
                    System.out.println("Failed to withdraw. "
                            + "This account does not have enough funds");
                } else {
                    System.out.println("Failed to withdraw. "
                            + "This account does not exist");
                }
                break;

            case "INQUIRE":
                //inquire amount on account
                System.out.print(getTime() + "\tINQUIRING ACCOUNT ID#"
                        + ID + "\t\t");


                sendingData = inquire(ID);

                if (sendingData.get(0).equals("SUCCESS")) {
                    System.out.println("Successful operation");
                } else {
                    System.out.println("Failed to inquire. "
                            + "This account does not exist");
                }
                break;
        }

        return encode(sendingData);
    }

    /**
     * Decode from input string to Array List of parameters according to
     * protocol
     *
     * @param msg Input String from user
     * @return ArrayList of Strings with command and parameters
     */
    private ArrayList<String> decode(String msg) {
        ArrayList<String> data = new ArrayList<>();

        Scanner scan = new Scanner(msg);
        scan.useDelimiter(":");

        while (scan.hasNext()) {
            String piece = scan.next();
            data.add(piece);
        }

        return data;
    }

    /**
     * Encode server's react to a single String to send to client
     *
     * @param data ArrayList with answer from server
     * @return Encoded line for client
     */
    private String encode(ArrayList<String> data) {
        String msg = "";

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                msg += data.get(i) + ":";
            }

            msg = msg.substring(0, msg.length() - 1) + ";"; //cut last colon and add semicolon to the end
        }

        return msg;
    }

    private ArrayList<String> createAccount(int ID, double amount) {
        ArrayList<String> result = new ArrayList<>();

        Double createdBalance = storage.create(ID, amount);
        if (createdBalance != null) {
            result.add("SUCCESS");
            result.add("" + createdBalance);
        } else {
            result.add("FAIL");
            result.add("2"); //code of "Existing account" error
        }

        return result;
    }

    private ArrayList<String> deleteAccount(int ID) {
        ArrayList<String> result = new ArrayList<>();

        Double deletedBalance = storage.delete(ID);
        if (deletedBalance != null) {
            result.add("SUCCESS");
            result.add("" + deletedBalance);
        } else {
            result.add("FAIL");
            result.add("1"); //code of "Account does not exist" error
        }

        return result;
    }

    private ArrayList<String> deposit(int ID, double amount) {
        ArrayList<String> result = new ArrayList<>();

        Double depositedBalance = storage.deposit(ID, amount);
        if (depositedBalance != null) {
            result.add("SUCCESS");
            result.add("" + depositedBalance);
        } else {
            result.add("FAIL");
            result.add("1"); //code of "Account does not exist" error
        }

        return result;
    }

    private ArrayList<String> withdraw(int ID, double amount) {
        ArrayList<String> result = new ArrayList<>();

        Double withdrawingBalance = storage.withdraw(ID, amount);
        if (withdrawingBalance != null && withdrawingBalance != -1) {
            result.add("SUCCESS");
            result.add("" + withdrawingBalance);
        } else if (withdrawingBalance == -1) {
            result.add("FAIL");
            result.add("3"); //code of "Insufficient funds" error

        } else {
            result.add("FAIL");
            result.add("1"); //code of "Account does not exist" error
        }

        return result;
    }

    private ArrayList<String> inquire(int ID) {
        ArrayList<String> result = new ArrayList<>();

        Double inquiredBalance = storage.inquire(ID);
        if (inquiredBalance != null) {
            result.add("SUCCESS");
            result.add("" + inquiredBalance);
        } else {
            result.add("FAIL");
            result.add("1"); //code of "Account does not exist" error
        }

        return result;
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
            System.err.println(getTime() + "\t" + ex);
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
