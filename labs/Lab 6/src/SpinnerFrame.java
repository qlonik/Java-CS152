/*	CounterFrame.java
 *
 *	Provides the visual display on which three counters will display their
 *	values.
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpinnerFrame extends JFrame {

    private JPanel[] spinnerPanel = new JPanel[6];
    private int[] spinnerVal = new int[6];
    private SpinnerThread[] spinnerThread = new SpinnerThread[6];
    private JPanel buttonPanel;
    private JButton[] controlButton = new JButton[6];

    public SpinnerFrame() {
        super();
        this.setTitle("Threads Example");
        this.setVisible(true);
        this.setSize(650, 650);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
//        this.getContentPane().setLayout(new GridLayout(2, 3));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up spinner display elements
        spinnerPanel[0] = MakeSpinnerPanel(Color.black, Color.white);
        spinnerPanel[1] = MakeSpinnerPanel(Color.red, Color.green);
        spinnerPanel[2] = MakeSpinnerPanel(Color.blue, Color.yellow);
        spinnerPanel[3] = MakeSpinnerPanel(Color.cyan, Color.orange);
        spinnerPanel[4] = MakeSpinnerPanel(Color.pink, Color.gray);
        spinnerPanel[5] = MakeSpinnerPanel(Color.darkGray, Color.magenta);

        spinnerThread[0] = new SpinnerThread(100, 0, this);
        spinnerThread[1] = new SpinnerThread(200, 1, this);
        spinnerThread[2] = new SpinnerThread(300, 2, this);
        spinnerThread[3] = new SpinnerThread(400, 3, this);
        spinnerThread[4] = new SpinnerThread(500, 4, this);
        spinnerThread[5] = new SpinnerThread(600, 5, this);

        controlButton[0] = MakeControlButton(Color.white, Color.black, "One", spinnerThread[0]);
        controlButton[1] = MakeControlButton(Color.green, Color.red, "Two", spinnerThread[1]);
        controlButton[2] = MakeControlButton(Color.yellow, Color.blue, "Three", spinnerThread[2]);
        controlButton[3] = MakeControlButton(Color.orange, Color.cyan, "Four", spinnerThread[3]);
        controlButton[4] = MakeControlButton(Color.gray, Color.pink, "Five", spinnerThread[4]);
        controlButton[5] = MakeControlButton(Color.magenta, Color.darkGray, "Six", spinnerThread[5]);

        buttonPanel = new JPanel();
        for (int x = 0; x < 6; x++) {
            spinnerVal[x] = 0;
            buttonPanel.add(controlButton[x]);
        }

        this.getContentPane().add(spinnerPanel[0]);
        this.getContentPane().add(spinnerPanel[1]);
        this.getContentPane().add(spinnerPanel[2]);
        this.getContentPane().add(spinnerPanel[3]);
        this.getContentPane().add(spinnerPanel[4]);
        this.getContentPane().add(spinnerPanel[5]);
        this.getContentPane().add(buttonPanel);

    }

    public void startSpinners() {
        for (int x = 0; x < 6; x++) {
            spinnerThread[x].start();
        }
    }

    private JButton MakeControlButton(Color fc, Color bc, String title, SpinnerThread st) {
        JButton b = new JButton(title + " RUN ");
        b.setForeground(fc);
        b.setBackground(bc);
        b.addActionListener(new ButtonListener(title, st));
        return b;
    }

    private JPanel MakeSpinnerPanel(Color fc, Color bc) {
        JPanel s = new JPanel();
        s.setPreferredSize(new Dimension(600, 200));
        s.setForeground(fc);
        s.setBackground(bc);
        return s;
    }

    public void ChangeSpinner(int spinnerNum) {
        // get appropriate colours
        Color fc = spinnerPanel[spinnerNum].getForeground();
        Color bc = spinnerPanel[spinnerNum].getBackground();
        // get graphics area
        Graphics g = spinnerPanel[spinnerNum].getGraphics();

        // undraw current position
        g.setColor(bc);
        DrawLine(g, spinnerVal[spinnerNum], spinnerNum);

        // go to next position
//        if (spinnerNum < 3) {
            spinnerVal[spinnerNum] = (spinnerVal[spinnerNum] + 1) % 4;
//        } else if (spinnerNum < 6) {
//            spinnerVal[spinnerNum] = (spinnerVal[spinnerNum] - 1) % 4;
//            spinnerVal[spinnerNum]++;
//            spinnerVal[spinnerNum] = 3 - spinnerVal[spinnerNum] % 4;
//        }

        // draw new position
        g.setColor(fc);
        DrawLine(g, spinnerVal[spinnerNum], spinnerNum);

    }

    private void DrawLine(Graphics page, int phase, int ID) {
        if (ID < 3) {
            switch (phase) {
                case 0:
                    page.drawLine(300, 50, 300, 150);
                    break;
                case 1:
                    page.drawLine(250, 150, 350, 50);
                    break;
                case 2:
                    page.drawLine(250, 100, 350, 100);
                    break;
                case 3:
                    page.drawLine(250, 50, 350, 150);
                    break;
            }
        } else if (ID < 6) {
            switch (phase) {
                case 0:
                    page.drawLine(250, 50, 350, 150);
                    break;
                case 1:
                    page.drawLine(250, 100, 350, 100);
                    break;
                case 2:
                    page.drawLine(250, 150, 350, 50);
                    break;
                case 3:
                    page.drawLine(300, 50, 300, 150);
                    break;
            }
        }
    }

    class ButtonListener implements ActionListener {

        private String name;
        private SpinnerThread spinnerThread;

        public ButtonListener(String btn, SpinnerThread st) {
            name = btn;
            spinnerThread = st;
        }

        public void actionPerformed(ActionEvent ae) {
            JButton theButton = (JButton) ae.getSource();
            if (spinnerThread.holdToggle()) {
                theButton.setText(name + " RUN ");
            } else {
                theButton.setText(name + " STOP");
            }

        }
    }
}
