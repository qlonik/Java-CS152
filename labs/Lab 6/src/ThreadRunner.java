/*	ThreadRunner.java
 *
 *	Creates a counter panel with 3 separate counter displays, then
 *	creates three separate counters, each with its own thread.  Counters
 *	then modify the contents of the original counter panel with their updated
 *	counts.
 */
 
 public class ThreadRunner
 {
  	public static void main (String[] args)
 	{
 		SpinnerFrame sf = new SpinnerFrame();
 		sf.pack();
 		sf.startSpinners();	
 	}
 }