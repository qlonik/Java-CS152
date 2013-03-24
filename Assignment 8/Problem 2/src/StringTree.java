
public class StringTree {

    private TreeNode list;
    private int steps;

    public StringTree() {
        list = null;
        steps = 0;
    }

    public void add(String text) {
        TreeNode newNode = new TreeNode(text);

        if (list == null) {
            list = newNode;
        } else {
            TreeNode current = list;
            TreeNode previous = null;

            while (current != null) {
                previous = current;
                if (current.getText().compareToIgnoreCase(text) < 0) {
                    current = current.getRightChild();
                } else if (current.getText().compareToIgnoreCase(text) > 0) {
                    current = current.getLeftChild();
                } else {
                    current = null;
                }
            }

            if (previous.getText().compareToIgnoreCase(text) < 0) {
                previous.setRightChild(newNode);
            } else if (previous.getText().compareToIgnoreCase(text) > 0) {
                previous.setLeftChild(newNode);
            }
        }
    }

    public int search(String text) {
        steps = 0;

        recursiveSearch(list, text);

        return steps + 1;
    }

    private void recursiveSearch(TreeNode root, String text) {
        while (root != null && root.getText().compareToIgnoreCase(text) != 0) {
            if (root.getText().compareToIgnoreCase(text) < 0) {
                steps++;
                root = root.getRightChild();
            } else {
                steps++;
                root = root.getLeftChild();
            }
        }

        //if we reached leaf of the tree and didnt find any nodes
        //then this node does not exist
        if (root == null) {
            steps = -1;
        }
    }

    @Override
    public String toString() {
        return "" + list;
    }
}
