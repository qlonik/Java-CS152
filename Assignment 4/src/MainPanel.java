
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

class MainPanel extends JPanel {

    private final String IMAGE_PATH = "res/images/";
    private final String AUDIO_PATH = "res/audios/";
    private ListPanel list;
    private ViewerPanel viewer;
    private ArrayList<Animal> animals;
    private int index;

    public MainPanel() {
        index = 0;

        animals = new ArrayList<>();
        try {
            animals.add(new Animal("Bird", IMAGE_PATH + "bird" + ".jpg", AUDIO_PATH + "bird" + ".au"));
            animals.add(new Animal("Cat", IMAGE_PATH + "cat" + ".jpg", AUDIO_PATH + "cat" + ".au"));
            animals.add(new Animal("Cow", IMAGE_PATH + "cow" + ".jpg", AUDIO_PATH + "cow" + ".au"));
            animals.add(new Animal("Cricket", IMAGE_PATH + "cricket" + ".jpg", AUDIO_PATH + "cricket" + ".au"));
            animals.add(new Animal("Crow", IMAGE_PATH + "crow" + ".jpg", AUDIO_PATH + "crow" + ".au"));
            animals.add(new Animal("Dog", IMAGE_PATH + "dog" + ".jpg", AUDIO_PATH + "dog" + ".au"));
            animals.add(new Animal("Donkey", IMAGE_PATH + "donkey" + ".jpg", AUDIO_PATH + "donkey" + ".au"));
            animals.add(new Animal("Duck", IMAGE_PATH + "duck" + ".jpg", AUDIO_PATH + "duck" + ".au"));
            animals.add(new Animal("Goat", IMAGE_PATH + "goat" + ".jpg", AUDIO_PATH + "goat" + ".au"));
            animals.add(new Animal("Hen", IMAGE_PATH + "hen" + ".jpg", AUDIO_PATH + "hen" + ".au"));
            animals.add(new Animal("Horse", IMAGE_PATH + "horse" + ".jpg", AUDIO_PATH + "horse" + ".au"));
            animals.add(new Animal("Hyena", IMAGE_PATH + "hyena" + ".jpg", AUDIO_PATH + "hyena" + ".au"));
            animals.add(new Animal("Monkey", IMAGE_PATH + "monkey" + ".jpg", AUDIO_PATH + "monkey" + ".au"));
            animals.add(new Animal("Owl", IMAGE_PATH + "owl" + ".jpg", AUDIO_PATH + "owl" + ".au"));
            animals.add(new Animal("Pig", IMAGE_PATH + "Pig" + ".jpg", AUDIO_PATH + "pig" + ".au"));
            animals.add(new Animal("Rattlesnake", IMAGE_PATH + "rattlesnake" + ".jpg", AUDIO_PATH + "rattlesnake" + ".au"));
            animals.add(new Animal("Rooster", IMAGE_PATH + "rooster" + ".jpg", AUDIO_PATH + "rooster" + ".au"));
//            animals.add(new Animal("Select", IMAGE_PATH + "select" + ".jpg", AUDIO_PATH + "select" + ".au"));
            animals.add(new Animal("Tiger", IMAGE_PATH + "tiger" + ".jpg", AUDIO_PATH + "tiger" + ".au"));
            animals.add(new Animal("Wolf", IMAGE_PATH + "wolf" + ".jpg", AUDIO_PATH + "wolf" + ".au"));
        } catch (Exception e) {
            System.err.println(e);
        }

        String[] names = new String[animals.size()];
        getNames().toArray(names);
        list = new ListPanel(this, names);

        viewer = new ViewerPanel(this);

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, viewer);

        setPreferredSize(new Dimension(500, 500));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

//        splitter.setOneTouchExpandable(true);
        add(splitter);
    }

    private ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            names.add(animals.get(i).getName());
        }
        return names;
    }

    public void setCurrentIndex(int index) {
        this.index = index;
    }

    public String getImagePath() {
        return IMAGE_PATH;
    }

    public String getAudioPath() {
        return AUDIO_PATH;
    }
}