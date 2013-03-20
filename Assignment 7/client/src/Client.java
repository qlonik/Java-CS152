
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private String ip;
    private int port;
    private Socket s;
    private Scanner input;
    private PrintStream output;
    private NumberFormat fmt;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;

        fmt = NumberFormat.getCurrencyInstance();
    }

    /**
     * Since server closes connection each time after answer, we need to
     * reconnect to it.
     *
     * @throws IOException If could not connect to server
     */
    private void openConnection() throws IOException {
        this.s = new Socket(ip, port);

        this.input = new Scanner(s.getInputStream());
        input.useDelimiter(";");

        this.output = new PrintStream(s.getOutputStream());
    }

    /**
     * Sends data to server
     *
     * @param data array list of data to send
     */
    private void send(ArrayList<String> data) {
        String msg = "";

        for (int i = 0; i < data.size(); i++) {
            msg += data.get(i) + ":";
        }

        msg = msg.substring(0, msg.length() - 1) + ";";

        output.print(msg);
    }

    /**
     * Receives data from server as answer to sent data
     *
     * @return array list of received data
     */
    private ArrayList<String> receive() {
        ArrayList<String> data = new ArrayList<>();

        String msg = input.next();

        Scanner scan = new Scanner(msg);
        scan.useDelimiter(":");

        while (scan.hasNext()) {
            String piece = scan.next();
            data.add(piece);
        }

        return data;
    }

    /**
     * Send data to server and receives answer from it
     *
     * @param data Data to send on server
     * @return Data received form server
     */
    public ArrayList<String> communicate(ArrayList<String> data) {
        try {
            openConnection();
        } catch (IOException ex) {
            System.out.println("Could not connect to server");
            System.err.println(ex);
        }

        send(data);

        return receive();
    }

    /**
     * Prints help to standard output
     */
    private void showHelp() {
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

        //quit program
        System.out.println("\t" + "q");
        System.out.println("\t\t" + "Quits program");
        System.out.println();
        //--END-- usage
    }

    private void createAccount(String token) {
        token = token.substring(token.indexOf(" ") + 1); //remove command character
        Scanner tokenScanner = new Scanner(token);

        String accountID = tokenScanner.next(); //read first token
        accountID = Integer.toString(Integer.parseInt(accountID)); //check if it is int

        String amount = tokenScanner.next(); //read second token
        Double enteredAmount = Double.parseDouble(amount);  //check if it is double
        if (enteredAmount < 0) { //check if it is positive
            throw new NumberFormatException("You cannot enter negative numbers");
        }
        amount = Double.toString(enteredAmount);
        //skip everything that left

        ArrayList<String> data = new ArrayList<>();
        data.add("CREATE");     //command to server to create account
        data.add(accountID);    //account id
        data.add(amount);       //starting amount of money

        ArrayList<String> received = communicate(data);

        switch (received.get(0)) {
            case "SUCCESS":
                Double receivedAmount = Double.parseDouble(received.get(1));
                System.out.println("Account ID#" + accountID + " successfully"
                        + " created. Starting balance: " + fmt.format(receivedAmount));
                break;

            case "FAIL":
                int failCode = Integer.parseInt(received.get(1));
                if (failCode == 2) {
                    System.out.println("Account ID#" + data.get(1)
                            + " was not created. Current account ID is in use");
                }
                break;
        }
    }

    private void deleteAccount(String token) {
        token = token.substring(token.indexOf(" ") + 1); //remove command character
        Scanner tokenScanner = new Scanner(token);

        String accountID = tokenScanner.next(); //read first token
        accountID = Integer.toString(Integer.parseInt(accountID)); //check if it is int
        //skip everything that left

        ArrayList<String> data = new ArrayList<>();
        data.add("DELETE");
        data.add(accountID);

        ArrayList<String> received = communicate(data);

        switch (received.get(0)) {
            case "SUCCESS":
                Double receivedAmount = Double.parseDouble(received.get(1));
                System.out.println("Account ID#" + accountID
                        + " was successfully deleted. Closing balance: "
                        + fmt.format(receivedAmount));
                break;

            case "FAIL":
                int failCode = Integer.parseInt(received.get(1));
                if (failCode == 1) {
                    System.out.println("Account ID#" + accountID
                            + " was not deleted. This account does not exist");
                }
                break;
        }
    }

    private void deposit(String token) {
        token = token.substring(token.indexOf(" ") + 1); //remove command character
        Scanner tokenScanner = new Scanner(token);

        String accountID = tokenScanner.next(); //read first token
        accountID = Integer.toString(Integer.parseInt(accountID)); //check if it is int

        String amount = tokenScanner.next(); //read second token
        Double enteredAmount = Double.parseDouble(amount);  //check if it is double
        if (enteredAmount < 0) { //check if it is positive
            throw new NumberFormatException("You cannot enter negative numbers");
        }
        amount = Double.toString(enteredAmount);
        //skip everything that left

        ArrayList<String> data = new ArrayList<>();
        data.add("DEPOSIT");     //command to server to create account
        data.add(accountID);    //account id
        data.add(amount);       //starting amount of money

        ArrayList<String> received = communicate(data);

        switch (received.get(0)) {
            case "SUCCESS":
                Double receivedAmount = Double.parseDouble(received.get(1));
                System.out.println("Account ID#" + accountID + "; amount"
                        + " successfully received. Current balance: "
                        + fmt.format(receivedAmount));
                break;

            case "FAIL":
                int failCode = Integer.parseInt(received.get(1));
                if (failCode == 1) {
                    System.out.println("Account ID#" + accountID + " did not receive "
                            + "deposit. Current account does not exist.");
                }
                break;
        }
    }

    private void withdraw(String token) {
        token = token.substring(token.indexOf(" ") + 1); //remove command character        
        Scanner tokenScanner = new Scanner(token);

        String accountID = tokenScanner.next(); //read first token
        accountID = Integer.toString(Integer.parseInt(accountID)); //check if it is int
        
        String amount = tokenScanner.next(); //read second token
        Double enteredAmount = Double.parseDouble(amount);  //check if it is double
        if (enteredAmount < 0) { //check if it is positive
            throw new NumberFormatException("You cannot enter negative numbers");
        }
        amount = Double.toString(enteredAmount);
        //skip everything that left

        ArrayList<String> data = new ArrayList<>();
        data.add("WITHDRAW");     //command to server to create account
        data.add(accountID);    //account id
        data.add(amount);       //starting amount of money

        ArrayList<String> received = communicate(data);

        switch (received.get(0)) {
            case "SUCCESS":
                Double receivedAmount = Double.parseDouble(received.get(1));
                System.out.println("You successfully withdraw from account ID#"
                        + accountID + ". Now you have " + fmt.format(receivedAmount));
                break;

            case "FAIL":
                int failCode = Integer.parseInt(received.get(1));
                if (failCode == 1) {
                    System.out.println("Could not withdraw from account ID#" + accountID
                            + ". This account does not exist.");
                } else if (failCode == 3) {
                    System.out.println("Could not withdraw from account ID#" + accountID
                            + ". This account does not have enough money.");
                }
                break;
        }
    }

    private void inquire(String token) {
        token = token.substring(token.indexOf(" ") + 1); //remove command character
        Scanner tokenScanner = new Scanner(token);

        String accountID = tokenScanner.next(); //read first token
        accountID = Integer.toString(Integer.parseInt(accountID)); //check if it is int
        //skip everything that left

        ArrayList<String> data = new ArrayList<>();
        data.add("INQUIRE");
        data.add(accountID);

        ArrayList<String> received = communicate(data);

        switch (received.get(0)) {
            case "SUCCESS":
                Double receivedAmount = Double.parseDouble(received.get(1));
                System.out.println("Current balance of account ID#" + accountID
                        + " is " + fmt.format(receivedAmount));
                break;

            case "FAIL":
                int failCode = Integer.parseInt(received.get(1));
                if (failCode == 1) {
                    System.out.println("Could not receive current balance of account "
                            + "ID#" + accountID + ". This account does not exist.");
                }
                break;
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
            System.err.println("Could not create error log file");
        }
        //</editor-fold>
        //--END--

//        //--BEGIN--
//        //setup output log
//        final String OUT_PATH = "./log/out.log";
//        //<editor-fold defaultstate="collapsed" desc="reassign output log to file">
//        try {
//            File outFile = new File(OUT_PATH);
//            outFile.createNewFile();
//            System.setOut(new PrintStream(outFile));
//        } catch (IOException ex) {
//            System.err.println("Could not create output file");
//        }
//        //</editor-fold>
//        //--END--

        //greetings message
        System.out.println("This is a system of out HT bank. Please, input first "
                + "IP and port of the server");

        Scanner kb = new Scanner(System.in);

        final String DEFAULT_IP = "127.0.0.1";
        final int DEFAULT_PORT = 56848;
        String ip;      //IP from user
        int port;       //port from user
        //<editor-fold defaultstate="collapsed" desc="asking for IP and port">
        System.out.print("Type hostname or IP adress of the server (default is "
                + DEFAULT_IP + "): ");
        ip = kb.nextLine();
        System.out.println();
        if (ip == null || ip.equals("")) {
            System.err.println("Input string is empty. Using default address " + DEFAULT_IP);
            ip = DEFAULT_IP;
        }

        try {
            System.out.print("Type port (default is " + DEFAULT_PORT + "): ");
            String portString = kb.nextLine();
            System.out.println();
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

        Client client = new Client(ip, port);

        //greetings message continue
        System.out.println("Type the command (\"h\" for help, \"q\" to quit)");

        //parsing user input
        if (client != null) {
            String token;
            do {
                System.out.print("> ");
                token = kb.nextLine();
                System.out.println();

                token = token.trim();
                String command;
                if (token.indexOf(" ") > -1) {
                    command = token.substring(0, token.indexOf(" "));
                } else {
                    command = token;
                }

                try {
                    switch (command) {
                        case "h":
                            client.showHelp();
                            break;

                        case "c":
                            client.createAccount(token);
                            break;

                        case "x":
                            client.deleteAccount(token);
                            break;

                        case "d":
                            client.deposit(token);
                            break;

                        case "w":
                            client.withdraw(token);
                            break;

                        case "i":
                            client.inquire(token);
                            break;

                        case "q":
                            //do nothing on q, so we do not show help
                            break;

                        default:
                            client.showHelp();
                            break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Error in your input. "
                            + "It does not match criteria. " + ex.getMessage());
                    System.err.println(ex);
                } catch (NoSuchElementException ex) {
                    System.out.println("Wrong number of items in command");
                    System.err.println(ex);
                }
            } while (!token.equals("q"));
        }
    }
}
