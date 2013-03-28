/*
 * StringTree.java      author: Nikita Volodin
 * CS152                Assignment 8 - Problem 2
 * 
 * This class represents binary tree of strings
 */

public class StringTree {

    private TreeNode list;
    private int steps;

    public StringTree() {
        list = null;
        steps = 0;
    }

    /**
     * Add text to the tree
     *
     * @param text String to add
     */
    public void add(String text) {
        TreeNode newNode = new TreeNode(text);

        if (list == null) { //if the tree is empty
            list = newNode;
        } else {
            TreeNode current = list;
            TreeNode previous = null;

            while (current != null) {
                previous = current;
                if (current.getText().compareToIgnoreCase(text) < 0) {
                    //if text is bigger than current root then we go to
                    //the right child of that root
                    current = current.getRightChild();
                } else if (current.getText().compareToIgnoreCase(text) > 0) {
                    //if text is small than current root then we go to
                    //the left child
                    current = current.getLeftChild();
                } else {
                    //in all other cases we are trying to add
                    //already existing node, so we skip it
                    current = null;
                    System.err.println("Skipping existing text: " + text);
                }
            }

            if (previous.getText().compareToIgnoreCase(text) < 0) {
                //if text is bigger than a root, then we go to the right child
                previous.setRightChild(newNode);
            } else if (previous.getText().compareToIgnoreCase(text) > 0) {
                //otherwize we go to the left child
                previous.setLeftChild(newNode);
            }
            //because we already skipped existing value (if we tried to add)
            //we do not need check to equal 0
        }
    }

    /**
     * Search for a text
     *
     * @param text Text we are looking for
     * @return Number of comparisons made to find that text
     */
    public int search(String text) {
        steps = 0;

        recursiveSearch(list, text);

        return steps + 1;
    }

    /**
     * Search text recursively from specified root
     *
     * @param root Root where to start searching
     * @param text Text to find
     */
    private void recursiveSearch(TreeNode root, String text) {
        //if we are not at the child of the leaf and if we did not find 
        //the text then we keep searching
        while (root != null && root.getText().compareToIgnoreCase(text) != 0) {
            if (root.getText().compareToIgnoreCase(text) < 0) {
                //if text is bigger than current root then we go to the right child
                steps++;
                root = root.getRightChild();
            } else {
                //otherwize text is smaller and we go to the left child
                steps++;
                root = root.getLeftChild();
            }
        }

        //if we reached child of a leaf and didnt find any nodes
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
