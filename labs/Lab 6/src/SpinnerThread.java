/*	SpinnerThread.java
 *
 *	Runs as a timer to cause a spinner to revolve.  Takes a delay in msec,
 *	an integer ID number and a parental reference as constructor parameters.
 */

public class SpinnerThread extends Thread {

    private int delay = 0;
    private int spinnerID = 0;
    private SpinnerFrame parent = null;
    private boolean running = true;

    public SpinnerThread(int msec_delay, int ID, SpinnerFrame sp) {
        super();
        delay = msec_delay;
        spinnerID = ID;
        parent = sp;
    }

    public void run() {
        while (true) {
            // sleep for a bit
            try {
                sleep(delay);
            } catch (InterruptedException ie) {
                System.exit(1);
            }

            // trigger a change in the appropriate spinner
            if (running) {
                parent.ChangeSpinner(spinnerID);
            }

        }

    }

    public boolean holdToggle() {
        running = !running;
        return running;
    }
}
