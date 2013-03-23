import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketClient {

  public final static int DEFAULT_PORT = 40322;

  public static void main(String[] args) {
    // set our initial default host and port, in case the user doesn't choose anything
    String targetHost = "127.0.0.1";
    int targetPort = DEFAULT_PORT;
    
    // if we have a single argument, it'd be the hostname
    if (args.length > 0)
    	targetHost = args[0];

	// if we have a second argument, that'd be the optional port number
	// try to convert it, but give an error if we can't    	
    if (args.length > 1)
        try {
	        targetPort = Integer.parseInt(args[1]);
        	if (targetPort < 0 || targetPort > 65535) {
          		System.err.println("Error:  invalid port " + targetPort);
          		System.exit(1);
        	}
      	}
      	catch (NumberFormatException nfe) {
        	System.err.println("Malformed port number exception (" + nfe + ") - using default: " + targetPort);
        }

	// create an internet address for our connection      
    InetAddress addr = null;
    try {
      addr = InetAddress.getByName(targetHost);
    }
    catch (UnknownHostException uhe) {
      System.err.println(targetHost + ": invalid host name.");
    }

	// make the connection
    if (addr != null)
      try {
      	System.out.println("Establishing connection to " + targetHost + " on port " + targetPort);
        Scanner kb = new Scanner(System.in);
        String hostname = addr.getHostName();
        Socket s = new Socket(addr, targetPort);
        InputStream is = s.getInputStream();
        Scanner scan = new Scanner(is);
        PrintWriter pr = new PrintWriter(s.getOutputStream());

        say(pr, "go");
        String text = hear(scan);
        while (!text.equalsIgnoreCase("punchline")) { // until the server gives the punchline, it's just dialog
          say(pr, "go");
          text = hear(scan);
          System.out.println(text);
          kb.nextLine(); // note - nothing stored, what user says is irrelevant
          say(pr, "go");
          text = hear(scan);
        }
        say(pr, "go");
        text = hear(scan); // ok, hit us with the punchline.
        System.out.println(text);
        System.out.println();
        System.out.println("What a cheesy joke.  Wanna hear one about the dead comedian?");
        s.close();
    }
    catch (IOException ioe) {
      System.err.println("IO Exception: " + ioe);
    }
  } //main

  // get input from the remote server
  public static String hear(Scanner scan) {
    String s = null;
    System.out.println("[Waiting for the server to speak.]");
    s = scan.nextLine();
    System.out.println("[Server said: " + s + "]");
    return s;
  }

  // send output to the remote server
  public static void say(PrintWriter p, String s) {
    p.println(s);
    p.flush();
    System.out.println("[I said: " + s + "]");
  }

}// class
