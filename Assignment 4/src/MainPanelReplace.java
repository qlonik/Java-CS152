
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainPanelReplace extends JPanel {

    private final String IMAGE_PATH = "res/images/";
    private final String AUDIO_PATH = "res/audios/";
    private JPanel listPanel, playerPanel;
    private ViewerPanel blankPanel;
    private ImageIcon currentImage;
    private AudioClip currentAudio;
    private JLabel icon;
    private JButton play, stop;
    private JList<String> list;
    private String[] animals = {"Bird", "Cat", "Cow", "Cricket", "Crow",
        "Dog", "Donkey", "Duck", "Goat", "Hen",
        "Horse", "Hyena", "Monkey", "Owl", "Pig",
        "Rattlesnake", "Rooster", "Tiger", "Wolf"};

    public MainPanelReplace() {
        //content
        currentImage = new ImageIcon(IMAGE_PATH + "select" + ".jpg");
        currentAudio = null;
        
        //GUI
        listPanel = new JPanel();
        blankPanel = new ViewerPanel();

        //listPanel
        list = new JList<>(animals);
        list.addListSelectionListener(new ListListener());
        listPanel.add(list);

        //mainPanel
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, blankPanel);
        setPreferredSize(new Dimension(700, 700));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(splitter);
    }

    private class ViewerPanel extends JPanel {

        public ViewerPanel() {
            playerPanel = new JPanel();

            //viewerPanel
            setLayout(new BorderLayout());

            //imagePanel
            icon = new JLabel(currentImage);
            icon.setHorizontalAlignment(JLabel.CENTER);
            icon.setVerticalAlignment(JLabel.CENTER);
            JScrollPane scrollImagePanel = new JScrollPane(icon);

            //playerPanel
            play = new JButton("Play");
            stop = new JButton("Stop");
            ButtonListener listener = new ButtonListener();
            play.addActionListener(listener);
            stop.addActionListener(listener);
            playerPanel.add(play);
            playerPanel.add(stop);

            //adding on viewerPanel
            add(scrollImagePanel, BorderLayout.CENTER);
            add(playerPanel, BorderLayout.SOUTH);
        }
    }

    private class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String selectedItem = list.getSelectedValue().toLowerCase();
            try {
                currentAudio = JApplet.newAudioClip(new URL("file", "localhost",
                        AUDIO_PATH + selectedItem + ".au"));
            } catch (Exception exception) {
                System.out.println(exception);
            }
            String path = IMAGE_PATH + selectedItem + ".jpg";
            currentImage = new ImageIcon(path);
            icon.setIcon(currentImage);
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == play && currentAudio != null) {
                currentAudio.play();
            }
            if (e.getSource() == stop && currentAudio != null) {
                currentAudio.stop();
            }
        }
    }
}
