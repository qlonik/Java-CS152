import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class WhiteBox
{
	private final int LIMIT = 100;
	private final int DELAY = 50;
	private int alpha, bravo, charlie;
	private int theFinalCountdown = 15;
	private String mxyzptlk = "Hi, I'm Ben DeRoy!";
	private Timer t;
	private boolean running = true;
	private boolean noodles = false;
	
	public WhiteBox()
	{
		alpha = 1;
		bravo = 2;
		charlie = 3;
		Timer t = new Timer(DELAY, new TimerListener());
	}

	public WhiteBox (int a, int b, int c)
	{
		alpha = a;
		bravo = b;
		charlie = c;
		t = new Timer(DELAY, new TimerListener());
	}
	

	public void start()
	{
		t.start();
		while(running); // keep this process alive until we tell it to stop
		
	}
	
	private int Rando()
	{
		Random generator = new Random();
		return generator.nextInt(10) + 1;		
	}
	
	protected void Obnoxio()
	{
		if (alpha % 4 == 0)
		{
			charlie = Rando();
			noodles = false;
		}
		else if (alpha < 0)
		{
			noodles = true;
		}
		else if (bravo % 3 == 0)
		{
			mxyzptlk = "Ben?  Ben DeRoy?  Never heard of him.";
		}
			
		alpha = MySequence.getValue(alpha);
		bravo += 5;		
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (Math.abs(alpha + bravo * charlie) > LIMIT)
			{
				alpha++;
				bravo--;
			}
			else
			{
				Obnoxio();
				System.out.println("Mr. Mxyzptlk says: " + mxyzptlk);
			}
			
			theFinalCountdown--;
			if (theFinalCountdown == 0)
			{
				running = false;
				t.stop();
			}
		}
	}
	
}