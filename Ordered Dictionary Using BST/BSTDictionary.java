
public class BSTDictionary implements BSTDictionaryADT{
	
	private BinarySearchTree tree;
	
	// Constructor initializes an empty Binary Search Tree
    public BSTDictionary() {
        tree = new BinarySearchTree();
    }

	@Override
	public Record get(Key k) {
		// Uses the BinarySearchTree's get method to find the node
		BSTNode node = tree.get(tree.getRoot(), k);
		return (node !=null) ? node.getRecord() : null;
	}

	@Override
	public void put(Record d) throws DictionaryException {
		// Check if key already exists
		if (tree.get(tree.getRoot(), d.getKey()) != null) {
			throw new DictionaryException("Duplicate key");
		} else {
			// if it doesn't exist
			tree.insert(tree.getRoot(), d);
		}
	}

	@Override
	public void remove(Key k) throws DictionaryException {
		// Check if exist
		if (tree.get(tree.getRoot(), k) == null) {
			throw new DictionaryException("Key not found");
		} else {
			// if it exist, then remove it
			tree.remove(tree.getRoot(), k);
		}
	}

	@Override
	public Record successor(Key k) {
		// To get the successor of a node
		BSTNode successorNode = tree.successor(tree.getRoot(), k);
		return (successorNode != null) ? successorNode.getRecord() : null;
	}

	@Override
	public Record predecessor(Key k) {
		// To get a predecessor of a node
		BSTNode predecessorNode = tree.predecessor(tree.getRoot(), k);
		return (predecessorNode != null) ? predecessorNode.getRecord() : null;
		
	}

	@Override
	public Record smallest() {
		// Smallest node in a tree
		BSTNode smallestNode = tree.smallest(tree.getRoot());
		return (smallestNode !=null) ? smallestNode.getRecord() : null;
	}

	@Override
	public Record largest() {
		// Largest node in a tree
		BSTNode largestNode = tree.largest(tree.getRoot());
		return (largestNode !=null) ? largestNode.getRecord() : null;
	}	
	
}
