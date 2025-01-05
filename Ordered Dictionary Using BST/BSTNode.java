public class BSTNode {

    private Record item; // Stores the data/record for this node
    private BSTNode left, right, parent; // Pointers to the left child, right child, and parent of the node

    // Constructor to initialize a node with a given record
    public BSTNode(Record item) {
        this.item = item; // Initialize node's data
        this.right = null; // Set right child to null initially
        this.left = null;  // Set left child to null initially
        this.parent = null; // Set parent to null initially
    }

    // Getter method for retrieving the record stored in this node
    public Record getRecord() {
        return item;
    }

    // Setter method to update the record in this node
    public void setRecord(Record d) {
        this.item = d;
    }

    // Getter for the left child of this node
    public BSTNode getLeftChild() {
        return left;
    }

    // Getter for the right child of this node
    public BSTNode getRightChild() {
        return right;
    }

    // Getter for the parent of this node
    public BSTNode getParent() {
        return parent;
    }

    // Setter for the left child of this node
    public void setLeftChild(BSTNode u) {
        this.left = u;
    }

    // Setter for the right child of this node
    public void setRightChild(BSTNode u) {
        this.right = u;
    }

    // Setter for the parent of this node
    public void setParent(BSTNode u) {
        this.parent = u;
    }

    // Checks if the node is a leaf node (i.e., has no children)
    public boolean isLeaf() {
        return (this.left == null && this.right == null); // Returns true if both children are null
    }
}
