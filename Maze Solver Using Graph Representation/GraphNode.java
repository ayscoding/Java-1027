
public class GraphNode {


	private int node; // The name or of the node
	private boolean mark; // The mark status of the node (true or false)
	
	// Constructs a GraphNode with the given name (int)
	public GraphNode(int node) {
		
		this.node=node;
	}

	
	// Marks the node with the specified boolean value
	public void mark(boolean mark) {
		this.mark=mark;
	}
	
	// Checks if the node is marked.
	public boolean isMarked() {
		return mark;
	}
	
	// Returns the name of the node.
	public int getName() {
		return node;
	}
	
}
