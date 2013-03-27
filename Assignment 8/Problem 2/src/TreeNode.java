
public class TreeNode {

    private String text;
    private TreeNode leftChild, rightChild;

    public TreeNode(String text) {
        this.text = text;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public String getText() {
        return text;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    @Override
    public String toString() {
        return ((leftChild != null) ? leftChild : "")
                + ((text != null) ? text + "\n" : "")
                + ((rightChild != null) ? rightChild : "");
    }
}
