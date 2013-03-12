
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private Socket s;
    private Scanner input;
    private PrintStream output;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String ERR_PATH = "./log/error.log";
        //<editor-fold defaultstate="collapsed" desc="reassign error log to output file">
        //reassign error log output
        try {
            File errFile = new File(ERR_PATH);
            if (!errFile.exists()) {
                errFile.createNewFile();
            }
            PrintStream errLog = new PrintStream(errFile);
            System.setErr(errLog);
        } catch (IOException ex) {
            System.err.println("Could not create log file");
        }
        //</editor-fold>

        Scanner kb = new Scanner(System.in);

        final int DEFAULT_PORT = 56848;
        String ip;      //IP from user
        int port;       //port from user
        //<editor-fold defaultstate="collapsed" desc="asking for IP and port">
        System.out.print("Type hostname or IP adress of the server: ");
        ip = kb.nextLine();
        //        System.out.println();

        try {
            System.out.print("Type port: ");
            String portString = kb.nextLine();
            port = Integer.parseInt(portString);
            if (port < 0 || port > 65535) {
                System.err.println("Port is out of bound. Using default port " + DEFAULT_PORT);
                port = DEFAULT_PORT;
            }
        } catch (NumberFormatException ex) {
            System.err.println("Input is not an integer. Using default port " + DEFAULT_PORT);
            port = DEFAULT_PORT;
        }
        //</editor-fold>

        Client client = null;
        //<editor-fold defaultstate="collapsed" desc="assigning socket to client object">
        try {
            Socket s = new Socket(ip, port);
            client = new Client(s);
        } catch (IOException ex) {
            System.out.println("Could not connect to server");
            System.err.println(ex);
        }
        //</editor-fold>

        if (client != null) {
            String token;
            do {
                System.out.print("Write a command (\"h\" for help, \"q\" to quit): ");
                token = kb.nextLine();
                System.out.println();

                token = token.trim();
                String command = token.substring(0, token.indexOf(" "));

                try {
                    switch (command) {
                        case "h":
                            client.callHelp();
                            break;
                        case "c":
                            client.createAccount(token);
                            break;
                        case "x":
                            client.deleteAccount(token);
                            break;
                        default:
                            client.callHelp();
                            break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error entering amount. It is not a double");
                    System.err.println(ex);
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            } while (!token.equals("q"));
        }
    }

    public Client(Socket s) {
        this.s = s;

        try {
            input = new Scanner(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /*
     * Receives array list of strings of data in format
     *      "SUCCESS:<amount>;"
     *      "FAIL:<fail code>;"
     */
    /**
     * Receives data from server as answer to sent data
     *
     * @return array list of received data
     */
    public ArrayList<String> receive() {
        ArrayList<String> data = new ArrayList<>();

        String msg = input.nextLine();
        msg = msg.substring(0, msg.length() - 1);

        Scanner scan = new Scanner(msg);
        scan.useDelimiter(":");

        while (scan.hasNext()) {
            String piece = scan.next();
            data.add(piece);
        }

        return data;
    }

    /*
     * Sends data converted to a line in format:
     *      "CREATE:<account id>;"
     *      "DELETE:<account id>;"
     *      "DEPOSIT:<account id>:<amount>;"
     *      "WITHDRAW:<account id>:<amount>;"
     *      "INQUIRE:<account id>;"
     */
    /**
     * Sends data to server to access some data
     *
     * @param data array list of data to send
     */
    public void send(ArrayList<String> data) {
        String msg = "";

        for (int i = 0; i < data.size(); i++) {
            msg += data.get(i) + ":";
        }

        msg = msg.substring(0, msg.length() - 1) + ";";

        output.print(msg);
    }

    /**
     * Prints help to standard output
     */
    private void callHelp() {
        //--BEGIN-- intro
        System.out.println("Client for bank system");
        System.out.println();
        //--END-- intro

        //--BEGIN-- usage
        System.out.println("Usage: ");

        //creating account
        System.out.println("\t" + "c <accound id> <starting balance>");
        System.out.println("\t\t" + "Creates new account with specified id "
                + "and starting balance");
        System.out.println("\t\t" + "Returns starting balance if created right "
                + "or return error (account number is in use)");
        System.out.println();

        //deposit amount
        System.out.println("\t" + "d <account id> <amount>");
        System.out.println("\t\t" + "Adds amount to balance");
        System.out.println("\t\t" + "At success returns new balance or error "
                + "(missing account)");
        System.out.println();

        //inquire amount
        System.out.println("\t" + "i <account id>");
        System.out.println("\t\t" + "Current balance");
        System.out.println("\t\t" + "At success returns amount on the account "
                + "or error (missing account)");
        System.out.println();

        //withdraw amount
        System.out.println("\t" + "w <account id> <amount>");
        System.out.println("\t\t" + "Withdraw amount from account");
        System.out.println("\t\t" + "At success returns new balance or error "
                + "(missing account; insufficient funds)");
        System.out.println();
        
        //deleting account
        System.out.println("\t" + "x <account id>");
        System.out.println("\t\t" + "Deletes account with specified id");
        System.out.println("\t\t" + "Returns balance before closing or error "
                + "(missing account)");
        System.out.println();
        //--END-- usage
    }

    private void createAccount(String token) throws NumberFormatException {
        token = token.substring(token.indexOf(" ") + 1); //remove command character

        String accountID = token.substring(0, token.indexOf(" ")); //from beginning to first space
        String amount = token.substring(token.indexOf(" ") + 1); //from space to the end
        amount = Double.toString(Double.parseDouble(amount)); //checking if it is double

        ArrayList<String> data = new ArrayList<>();
        data.add(accountID);
        data.add(amount);
        send(data);

        ArrayList<String> received = receive();

        if (received.get(0).equals("SUCCESS")) {
            System.out.println("Account successfully created with id: " + accountID
                    + " and starting amount: " + amount);
        } else if (received.get(0).equals("FAIL")) {
            System.out.println("Account was not created. Current account id is in use");
        }
    }

    private void deleteAccount(String token) {
    }
}
