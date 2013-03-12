
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/*
 * Server.java  Nikita Volodin
 * CS152        Assigment 7
 * 
 * Driver class for a server side of an application
 */

public class Server {

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
//            if (!errFile.exists()) {
                errFile.createNewFile();
//            }
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
//            if (!outFile.exists()) {
                outFile.createNewFile();
//            }
            System.setOut(new PrintStream(outFile));
        } catch (IOException ex) {
            System.err.println("Could not create log file");
        }
        //</editor-fold>
        //--END--
        
        Listener listener = new Listener();
        // TODO code application logic here
//        Account acc = new Account("Bill Murrey", 1235674, 90);
//        acc.deposit(10);
//        acc.withdraw(15);
//        acc.deposit(100);
//        acc.withdraw(200);
    }
}
