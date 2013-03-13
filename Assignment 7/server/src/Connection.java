
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

    @Override
    public void run() {
        try {
            ArrayList<String> data = receive();

            while (!data.get(0).equals("CLOSE")) {
                String command = data.get(0);

                switch (command) {
                    case "CREATE":
                        System.out.println("CREATING ACC");
                        //create new account
                        break;
                    case "DELETE":
                        //delete account
                        break;
                    case "DEPOSIT":
                        //deposit amount to account
                        break;
                    case "WITHDRAW":
                        //withdraw amount from account
                        break;
                    case "INQUIRE":
                        //send amount on account
                        break;
                    default:
                        //command does not exist message
                        break;
                }
                command = null;
            }

            s.close();
            System.out.println("Client disconnected");
        } catch (IOException ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        } catch (Exception ex) {
            System.err.println(System.currentTimeMillis() + "\t" + ex);
        }
    }

    /**
     * Receives data from server as answer to sent data
     *
     * @return array list of received data
     */
    private ArrayList<String> receive() {
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

    /**
     * Sends data to server
     *
     * @param data array list of data to send
     */
    public void send(ArrayList<String> data) {
        String msg = "";

        for (int i = 0; i < data.size(); i++) {
            msg += data.get(i) + ":";
        }

        msg = msg.substring(0, msg.length() - 1) + ";";

        output.println(msg);
    }
}
