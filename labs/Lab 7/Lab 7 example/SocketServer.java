import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketServer {

  public final static int DEFAULT_PORT = 40322;

  public static void main(String[] args) {
    // We only need to worry about the port; the host address is the address of this machine
    int port = DEFAULT_PORT;
    
    if (args.length > 0) { // a different port was specified; use it instead of the default.
      try {
        port = Integer.parseInt(args[0]);
        if (port < 0 || port > 65535) {
          System.err.println("Error:  invalid port " + port);
          System.exit(1);
        }
      }
      catch (NumberFormatException nfe) {
        System.err.println("Malformed port number exception (" + nfe + ") - using default: " + port);
      }
    }
    
    try {
      ServerSocket server = new ServerSocket(port);
      Socket connection = null;
      while (true) {
        try {
          System.out.println("Waiting for socket connection on port " + port);
          connection = server.accept();
          System.out.println("Connection accepted from: " + connection.getInetAddress().getHostName());
          // set up the client's communication path to the server
          InputStream is = connection.getInputStream();
          Scanner scan = new Scanner(is);
          // set up the server's communication path to the client
          PrintWriter out = new PrintWriter(connection.getOutputStream());
          // do the joke
          System.out.println("Telling the joke now.");
          doKnockKnock(out, scan);
          // cheesy - we're outta here
          System.out.println("Finished the joke.  Nobody laughed.");
          connection.close();
        }
        catch (IOException ioe) {
          System.err.println("IO Exception in writing: " + ioe);
        }
        finally {
          try {
            if (connection != null)
              connection.close();
          }
          catch (IOException ioe) {
            System.err.println("IO Exception in closing: " + ioe);
          }
        } // finally
      } // while
    }
    catch (IOException ioe) {
      System.err.println("Server socket exception: " + ioe);
    }
  }

  public static String hear(Scanner scan) {
    String s = null;
    System.out.println("Waiting for the client to speak.");
    s = scan.nextLine();
    System.out.println("Client said: " + s);
    return s;
  }

  public static void say(PrintWriter p, String s) {
    p.println(s);
    p.flush();
    System.out.println("I said: " + s);
  }

  // the part that actually does the joke

  public static void doKnockKnock(PrintWriter p, Scanner scan) {
    
	// do the same thing 3 times
    for (int i = 0; i < 3; i++)
    {
    	hear(scan);
    	say(p,"response");
    	hear(scan);
    	say(p,"Knock knock!");
    	hear(scan);
    	say(p,"response");
    	hear(scan);
    	say(p,"Banana!");
	}

	// now the variation
    hear(scan);
    say(p,"response");
    hear(scan);
    say(p,"Knock knock!");
    hear(scan);
    say(p,"response");
    hear(scan);
    say(p,"Orange!");

	// ba-boom clang!
    hear(scan);
    say(p,"punchline");
    hear(scan);
    say(p,"Orange you glad I didn't say banana again?");
  }
}



