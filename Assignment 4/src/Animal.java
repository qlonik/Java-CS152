
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class Animal {

    private String name;
    private ImageIcon image;
    private AudioClip audio;
    
    public Animal(String name, String image, String audio) throws MalformedURLException {
        this.name = name;
        this.image = new ImageIcon(image);
        this.audio = JApplet.newAudioClip(new URL("file", "localhost", audio));
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }

    public AudioClip getAudio() {
        return audio;
    }
    
    
    
}
