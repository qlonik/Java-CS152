
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class represents connection with one client
 */
public class Connection extends Thread {

    private Socket s;
    private Scanner input;
    private PrintStream output;
    private Server parent;

    public Connection(Socket s, Server parent) throws IOException {
        this.s = s;

        input = new Scanner(s.getInputStream());
        input.useDelimiter(";");
        output = new PrintStream(s.getOutputStream());

        this.parent = parent;
    }

    /**
     * Receives data from server as answer to sent data
     *
     * @return array list of received data
     */
    private ArrayList<String> receive() {
        ArrayList<String> data = new ArrayList<>();

        String msg = input.next();
        //we do not need it anymore because we use delimeter instead
//        msg = msg.substring(0, msg.length() - 1); //cut last ";" in message

        Scanner scan = new Scanner(msg);
        scan.useDelimiter(":");

        while (scan.hasNext()) {
            String piece = scan.next();
            data.add(piece);
        }

        return data;
    }

    /**
     * Sends data to server
     *
     * @param data array list of data to send
     */
    private void send(ArrayList<String> data) {
        String msg = "";

        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                msg += data.get(i) + ":";
            }

            msg = msg.substring(0, msg.length() - 1) + ";";
        }

        output.print(msg);
    }

    /*
     * All operational methods (create, delete, deposit, withdraw, inquire) say to parent (Server) to do stuff
     * Server class asks storage class to create or generate error
     * And later all operational methods parse result from storage class (passed through Server class) and 
     * generate answer to client
     */
    
    private boolean createAccount(ArrayList<String> data) {
        boolean result;
        ArrayList<String> toSend = new ArrayList<>();

        Account created = parent.createAccount(data);
        if (created != null) {
            result = true;

            toSend.add("SUCCESS");
            toSend.add("" + created.getBalance());
        } else {
            result = false;

            toSend.add("FAIL");
            toSend.add("2");
        }

        send(toSend);
        return result;
    }

    private boolean deleteAccount(ArrayList<String> data) {
        boolean result;
        ArrayList<String> toSend = new ArrayList<>();

        Account removed = parent.deleteAccount(data);
        if (removed != null) {
            result = true;

            toSend.add("SUCCESS");
            toSend.add("" + removed.getBalance());
        } else {
            result = false;

            toSend.add("FAIL");
            toSend.add("1");
        }

        send(toSend);
        return result;
    }

    private boolean deposit(ArrayList<String> data) {
        boolean result;
        ArrayList<String> toSend = new ArrayList<>();

        Account deposited = parent.deposit(data);
        if (deposited != null) {
            result = true;

            toSend.add("SUCCESS");
            toSend.add("" + deposited.getBalance());
        } else {
            result = false;

            toSend.add("FAIL");
            toSend.add("1");
        }

        send(toSend);
        return result;
    }

    private boolean withdraw(ArrayList<String> data) {
        boolean result;
        ArrayList<String> toSend = new ArrayList<>();

        Object[] withdrawed = parent.withdraw(data);
        Account acc = (Account) withdrawed[1];
        boolean moneyLack = (boolean) withdrawed[0];

        if (acc != null && !moneyLack) {
            result = true;

            toSend.add("SUCCESS");
            toSend.add("" + acc.getBalance());
        } else if (acc == null) {
            result = false;

            toSend.add("FAIL");
            toSend.add("1");
        } else {
            result = false;

            toSend.add("FAIL");
            toSend.add("3");
        }

        send(toSend);
        return result;
    }

    private boolean inquire(ArrayList<String> data) {
        boolean result;
        ArrayList<String> toSend = new ArrayList<>();

        Account inquired = parent.inquire(data);
        if (inquired != null) {
            result = true;

            toSend.add("SUCCESS");
            toSend.add("" + inquired.getBalance());
        } else {
            result = false;

            toSend.add("FAIL");
            toSend.add("1");
        }

        send(toSend);
        return result;
    }

    @Override
    public void run() {
        try {
            ArrayList<String> data = receive();
            String command = data.get(0);

            switch (command) {
                case "CREATE":
                    //create new account
                    System.out.print("CLIENT " + s.getRemoteSocketAddress()
                            + "\t\tCREATING ACCOUNT ID#" + data.get(1));
                    if (createAccount(data)) {
                        System.out.println("\t\tSuccessful operation");
                    } else {
                        System.out.println("\t\tFailed to create. This account number "
                                + "already exist");
                    }
                    break;

                case "DELETE":
                    //delete account
                    System.out.print("CLIENT " + s.getRemoteSocketAddress()
                            + "\t\tDELETING ACCOUNT ID#" + data.get(1));
                    if (deleteAccount(data)) {
                        System.out.println("\t\tSuccessful operation");
                    } else {
                        System.out.println("\t\tFailed to delete. This account "
                                + "does not exist");
                    }
                    break;

                case "DEPOSIT":
                    //deposit amount to account
                    System.out.print("CLIENT " + s.getRemoteSocketAddress()
                            + "\t\tDEPOSITING TO ACCOUNT ID#" + data.get(1));
                    if (deposit(data)) {
                        System.out.println("\t\tSuccessful operation");
                    } else {
                        System.out.println("\t\tFailed to deposit. This account "
                                + "does not exist");
                    }
                    break;

                case "WITHDRAW":
                    //withdraw amount from account
                    System.out.print("CLIENT " + s.getRemoteSocketAddress()
                            + "\t\tWITHDRAWING FROM ACCOUNT ID#" + data.get(1));
                    if (withdraw(data)) {
                        System.out.println("\t\tSuccessful operation");
                    } else {
                        System.out.println("\t\tFailed to withdraw. ");
                        //#TODO different messages dependant upon code of fail
                    }
                    break;

                case "INQUIRE":
                    //inquire amount on account
                    System.out.print("CLIENT " + s.getRemoteSocketAddress()
                            + "\t\tINQUIRING ACCOUNT ID#" + data.get(1));
                    if (inquire(data)) {
                        System.out.println("\t\tSuccessful operation");
                    } else {
                        System.out.println("\t\tFailed to inquire. "
                                + "This account does not exist");
                    }
                    break;

                default:
                    //#TODO command does not exist message
                    break;
            }

            s.close();
            System.out.println("Client from " + s.getRemoteSocketAddress() + " disconnected");
        } catch (IOException ex) {
            System.err.println(parent.getTime() + "\t" + ex);
        } catch (Exception ex) {
            System.out.println("exception on line 191 of connection.java");
            System.err.println(parent.getTime() + "\t" + ex);
        }
    }
}
