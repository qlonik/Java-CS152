/*
 * Connection.java
 * Assignement 7 - Bank system. Server side.
 * 
 * CS152    Nikita Volodin (127196)
 * 
 * This class represents connection with one client
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Thread {

    private Socket s;
    private Scanner input;
    private PrintStream output;
    private Server parent;

    /**
     * Creates connection with client, receives data from it and sends answer
     * back
     *
     * @param s Socket to connect with.
     * @param parent Parent obj of this class
     * @throws IOException thrown if it cannot get input or output streams
     */
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
            //as soon as it gets input, transfer it to server
            String receivedMsg = input.next();
            String sendingMsg = parent.parseMsg(receivedMsg);
            output.print(sendingMsg);

            //close connection when we answer to client
            s.close();
        } catch (IOException ex) {
            System.out.println(Server.getTime() + " Could not disconnect "
                    + "client from" + s.getRemoteSocketAddress());
            System.err.println(Server.getTime() + "\t[EX] " + ex);
        }
    }
}
