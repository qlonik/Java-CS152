
import javax.swing.JFrame;

public class AnimationSorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation sorting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new MainPanel());

        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }
}
