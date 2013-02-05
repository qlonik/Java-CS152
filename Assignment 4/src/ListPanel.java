
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListPanel extends JPanel {

    private JPanel parent;
    private JList<String> list;

    public ListPanel(JPanel parent, String[] items) {
        this.parent = parent;
        list = new JList<>(items);
        list.addListSelectionListener(new ListListener());
        add(list);
    }
    
    private void itemSelected(ListSelectionEvent evt) {
//        System.out.println(parent);
    }

    private class ListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            itemSelected(e);
        }
    }
}
