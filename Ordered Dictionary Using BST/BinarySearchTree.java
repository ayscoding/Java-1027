// Represents A BST
public class BinarySearchTree {

    private BSTNode r; // Root of the tree
    
    public BinarySearchTree() {
        this.r = null;  // initializing root to null
    }
    
    public BSTNode getRoot() {
        return r;
    }
    
    // Searches for a node with the specified key k in the subtree rooted at r
    public BSTNode get(BSTNode r, Key k) {
        if (r == null) return null; // Base case: key not found
        int comparison = k.compareTo(r.getRecord().getKey());
        if (comparison == 0) {
            return r; // Found the node
        } else if (comparison < 0) {
            return get(r.getLeftChild(), k); // Search in the left subtree
        } else {
            return get(r.getRightChild(), k); // Search in the right subtree
        }
    }
    
    // Inserts a record into the tree, throws an exception if key already exists
    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (this.r == null) {
            this.r = new BSTNode(d); // insert as root if tree is empty
            return;
        }
        int comparison = d.getKey().compareTo(r.getRecord().getKey());
        
        // Key exists case
        if (comparison == 0) {
            throw new DictionaryException("Duplicate key");
        } else if (comparison < 0) { // If the key is smaller, go left
            if (r.getLeftChild() == null) {
                BSTNode newNode = new BSTNode(d); // Creating new left child
                r.setLeftChild(newNode);
                newNode.setParent(r); // Set parent pointer for new node
            } else {
                insert(r.getLeftChild(), d); // recursive step to left subtree
            }
        } else if (comparison > 0) { // If the key is larger, go right
            if (r.getRightChild() == null) {
                BSTNode newNode = new BSTNode(d); // Creating new right child
                r.setRightChild(newNode);
                newNode.setParent(r); // Set parent pointer for new node
            } else {
                insert(r.getRightChild(), d); // recursive step to right subtree
            }
        }
    }
    
    // Removes a node with the specified key from the subtree rooted at r
    public void remove(BSTNode r, Key k) throws DictionaryException {
        if (r == null) {
            throw new DictionaryException("Key not found"); // Key not in tree
        }
        
        int comparison = k.compareTo(r.getRecord().getKey());

        // Case 1: Key is smaller, search in the left subtree
        if (comparison < 0) {
            if (r.getLeftChild() != null) {
                remove(r.getLeftChild(), k);
            }
        }
        // Case 2: Key is larger, search in the right subtree
        else if (comparison > 0) {
            if (r.getRightChild() != null) {
                remove(r.getRightChild(), k);
            }
        }
        // Case 3: Key matches, node to delete found
        else {
            // Sub-case 3a: Node has two children
            if (r.getLeftChild() != null && r.getRightChild() != null) {
                // Find successor (smallest in the right subtree)
                BSTNode successor = smallest(r.getRightChild());
                // Copy successor's record to current node
                r.setRecord(successor.getRecord());
                // Remove successor node recursively (successor has at most one child)
                remove(r.getRightChild(), successor.getRecord().getKey());
            }
            // Sub-case 3b: Node has one child or is a leaf
            else {
                BSTNode child = (r.getLeftChild() != null) ? r.getLeftChild() : r.getRightChild();
                
                if (r == this.r) {
                    // Special case: Removing the root
                    this.r = child;
                    if (child != null) {
                        child.setParent(null); // Update parent of the new root
                    }
                } else {
                    // Link the parent to this node's child
                    BSTNode parent = r.getParent();
                    if (parent != null) {  // Check if parent exists
                        if (parent.getLeftChild() == r) {
                            parent.setLeftChild(child);
                        } else {
                            parent.setRightChild(child);
                        }
                    }
                    
                    // Update child's parent link if child is not null
                    if (child != null) {
                        child.setParent(parent);
                    }
                }
            }
        }
    }

    // Finds the successor of the node with key k in the subtree rooted at r
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode current = get(r, k); // Find the node with the given key
        if (current == null) return null; // Node not found

        // Case 1: Node has a right subtree, find the smallest node in the right subtree
        if (current.getRightChild() != null) {
            return smallest(current.getRightChild());
        }

        // Case 2: No right subtree, go up to the ancestor to find successor
        BSTNode parent = current.getParent();
        while (parent != null && current == parent.getRightChild()) {
            current = parent;
            parent = parent.getParent();
        }
        
        // Parent will be the successor if it exists
        return parent;
    }

    // Finds the predecessor of the node with key k in the subtree rooted at r
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode current = get(r, k); // Find the node with the given key
        if (current == null) return null; // Node not found

        // Case 1: Node has a left subtree, find the largest node in the left subtree
        if (current.getLeftChild() != null) {
            return largest(current.getLeftChild());
        }

        // Case 2: No left subtree, go up to the ancestor to find predecessor
        BSTNode parent = current.getParent();
        while (parent != null && current == parent.getLeftChild()) {
            current = parent;
            parent = parent.getParent();
        }

        // Parent will be the predecessor if it exists
        return parent;
    }
    
    // Finds and returns the smallest node in the subtree rooted at r
    public BSTNode smallest(BSTNode r) {
        if (r == null) return null; // Base case: subtree is empty
        while (r.getLeftChild() != null) {
            r = r.getLeftChild(); // Traverse to the leftmost node
        }
        return r; // Leftmost node is the smallest
    }
    
    // Finds and returns the largest node in the subtree rooted at r
    public BSTNode largest(BSTNode r) {
        if (r == null) return null; // Base case: subtree is empty
        while (r.getRightChild() != null) {
            r = r.getRightChild(); // Traverse to the rightmost node
        }
        return r; // Rightmost node is the largest
    }    
}
