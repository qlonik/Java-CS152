
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewerPanel extends JPanel {

    private JPanel parent;
    
    private ImageIcon currentImage;
    private AudioClip currentAudio;
    private JPanel image, music;
    private JLabel imageLabel;
    private JButton playButton, stopButton;
    
    public ViewerPanel(JPanel parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        currentAudio = null;
        currentImage = new ImageIcon("res/images/select.jpg");

        image = new JPanel();
        music = new JPanel();
        
        imageLabel = new JLabel();
        imageLabel.setIcon(currentImage);
        
//        if (imageLabel.getSize().width <= parent.getSize().width && 
//                imageLabel.getSize().height <= parent.getSize().height) {
//            image.add(imageLabel);
//        } else {
            JScrollPane imageScroll = new JScrollPane(imageLabel);
            image.add(imageScroll);
//        }
        
        playButton = new JButton("Play audio");
        
        playButton.addActionListener(new ButtonListener());
        
        music.add(playButton);
        
        add(image, BorderLayout.CENTER);
        add(music, BorderLayout.SOUTH);
    }

    public void setAnimal(ImageIcon image, AudioClip audio) {
        currentAudio = audio;
        imageLabel.setIcon(image);
        
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            if (source.getText().equals("Play audio")) {
                currentAudio.play();
                
            }
        }

    }
}
