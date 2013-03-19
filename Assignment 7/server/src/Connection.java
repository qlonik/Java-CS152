
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
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
            String receivedMsg = input.next();
            String sendingMsg = parent.parseMsg(receivedMsg);
            output.print(sendingMsg);

            s.close();
            System.out.println(parent.getTime() + "\tCLIENT "
                    + s.getRemoteSocketAddress() + " was disconnected");
        } catch (IOException ex) {
            System.out.println(parent.getTime() + " Could not disconnect "
                    + "client from" + s.getRemoteSocketAddress());
            System.err.println(parent.getTime() + "\t[EX] " + ex);
        }
    }
}
