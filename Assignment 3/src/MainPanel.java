/*
 * MainPanel.java       Author: Nikita Volodin (127196)
 * CS152                Assignment 3
 * 
 * This class is a main panel which draws bars and animate them
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {

    final int HEIGHT_MULTIPLIER = 2;
    final int BAR_WIDTH = 6;
    final int GAP = 6;
    final int LEFT_RIGHT_SPACING = 50;
    final int TOP_BOTTOM_SPACING = 20;
    final Color DEFAULT_COLOR = Color.green;
    Color barColor;
    ArrayList<Integer> numbers;
    int index;
    boolean done;

    /**
     * Constructor for MainPanel. Here is reading file, setting size for window,
     * asking for color and sorting all ArrayList
     */
    public MainPanel() {
        Numbers nums = new Numbers(this);
        numbers = nums.getNumbers();

        int maxValue = nums.getMax();
        int width = numbers.size() * (BAR_WIDTH + GAP) - GAP + 2 * LEFT_RIGHT_SPACING;
        int height = maxValue * HEIGHT_MULTIPLIER + 2 * TOP_BOTTOM_SPACING;
        setPreferredSize(new Dimension(width, height));

        barColor = getBarColor();

        index = 0;
        done = false;
        sort();
    }

    /**
     * Method draws all bars from ArrayList
     *
     * @param g Graphics page
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < numbers.size(); i++) {
            g.setColor(barColor);
            g.fillRect(LEFT_RIGHT_SPACING + i * (BAR_WIDTH + GAP),
                    getHeight() - numbers.get(i) * HEIGHT_MULTIPLIER - TOP_BOTTOM_SPACING,
                    BAR_WIDTH,
                    numbers.get(i) * HEIGHT_MULTIPLIER);
            g.setColor(Color.black);
            g.drawRect(LEFT_RIGHT_SPACING + i * (BAR_WIDTH + GAP),
                    getHeight() - numbers.get(i) * HEIGHT_MULTIPLIER - TOP_BOTTOM_SPACING,
                    BAR_WIDTH,
                    numbers.get(i) * HEIGHT_MULTIPLIER);
        }
        if (done) {
            g.drawString("Sorting complete", 10, TOP_BOTTOM_SPACING);
        }
    }

    /**
     * Asking user with JColorChooser about color for bars
     *
     * @return Color chosen by user
     */
    private Color getBarColor() {
        Color color = JColorChooser.showDialog(this, "Choose color for bars", DEFAULT_COLOR);
        //if cancel was pressed
        if (color == null) {
            color = DEFAULT_COLOR;
        }

        return color;
    }

    /**
     * Method creates and starts timer. Each timer action is one step of sorting
     */
    private void sort() {
        Timer timer = new Timer(100, new TimerListener());
        timer.start();
    }

    /**
     * Method that happens when timer perform action
     *
     * @param e ActionEvent passed from performing action
     */
    private void timerEvent(ActionEvent e) {
        swap(numbers, index);
        index++;
        repaint();

        if (index == numbers.size()) {
            Timer timer = (Timer) e.getSource();
            done = true;
            timer.stop();
        }
    }

    /**
     * This method swaps element in list at position index with smallest element
     * found at position after index
     *
     * @param list ArrayList where swap elements
     * @param index index after which we looking for smallest element
     */
    public static void swap(ArrayList<Integer> list, int index) {
        int min = index;

        for (int scan = index + 1; scan < list.size(); scan++) {
            if (list.get(scan) < list.get(min)) {
                min = scan;
            }
        }

        if (index != min) {
            int tmp1 = list.get(index);
            int tmp2 = list.get(min);
            list.remove(index);
            list.add(index, tmp2);
            list.remove(min);
            list.add(min, tmp1);
        }
    }

    /**
     * Inner listener class that connected with timer
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timerEvent(e);
        }
    }
}