
import javax.swing.JFrame;

public class AnimalViewer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animal viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        frame.getContentPane().add(new MainPanel());
        frame.getContentPane().add(new MainPanelReplace());
        
        frame.setVisible(true);
        frame.pack();
    }
}
