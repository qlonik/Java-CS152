
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

        for (int i = 0; i < data.size(); i++) {
            msg += data.get(i) + ":";
        }

        msg = msg.substring(0, msg.length() - 1) + ";";

        output.print(msg);
    }

    private void createAccount(ArrayList<String> data) {
        ArrayList<String> toSend = new ArrayList<>();

        if (parent.createAccount(data)) {
            toSend.add("SUCCESS");
            toSend.add(data.get(2));
        } else {
            toSend.add("FAIL");
            toSend.add("2");
        }

        send(toSend);
    }

    private void deleteAccount(ArrayList<String> data) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void deposit(ArrayList<String> data) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void withdraw(ArrayList<String> data) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void inquire(ArrayList<String> data) {
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void run() {
        try {
            ArrayList<String> data = receive();

            String command = data.get(0);
            ArrayList<String> toSend = null;

            switch (command) {
                case "CREATE":
                    System.out.println("CREATING ACC");
                    createAccount(data);
                    //create new account
                    break;
                case "DELETE":
                    System.out.println("DELETING ACC");
                    deleteAccount(data);
                    //delete account
                    break;
                case "DEPOSIT":
                    System.out.println("DEPOSITING MONEY");
                    deposit(data);
                    //deposit amount to account
                    break;
                case "WITHDRAW":
                    System.out.println("WITHDRAWING MONEY");
                    withdraw(data);
                    //withdraw amount from account
                    break;
                case "INQUIRE":
                    System.out.println("INQUIRING");
                    inquire(data);
                    //send amount on account
                    break;
                default:
                    //command does not exist message
                    break;
            }

            s.close();
            System.out.println("Client from " + s.getRemoteSocketAddress() + " disconnected");
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        } catch (Exception ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        }
    }
}
