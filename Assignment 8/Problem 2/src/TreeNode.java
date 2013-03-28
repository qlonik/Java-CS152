/*
 * TreeNode.java        author: Nikita Volodin
 * CS152                Assignment 8 - Problem 2
 * 
 * This class represents node of a binary tree
 */

public class TreeNode {

    private String text;
    private TreeNode leftChild, rightChild;

    /**
     * Created new node with specified text
     *
     * @param text Data of this node
     */
    public TreeNode(String text) {
        this.text = text;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Set the left child of node
     *
     * @param leftChild Link to a left child
     */
    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Set the right child of node
     *
     * @param rightChild Link to a right child
     */
    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Data stored in this node
     *
     * @return Data String
     */
    public String getText() {
        return text;
    }

    /**
     * Gets left child of this node
     *
     * @return Link to left tree node
     */
    public TreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * Gets right child of this node
     *
     * @return Link to right tree node
     */
    public TreeNode getRightChild() {
        return rightChild;
    }

    /**
     * Prints tree node recursively until children are not null
     *
     * @return String representation of tree node
     */
    @Override
    public String toString() {
        return ((leftChild != null) ? leftChild : "")
                + text + "\n"
                + ((rightChild != null) ? rightChild : "");
    }
}
