
public class GraphEdge {


	private GraphNode u, v; // Endpoints of the edge
	private String label; // Label of the edge
	private int type;  // Type of the edge
	
	// Constructor to initialize the edge
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.u=u;
		this.v=v;
		this.type=type;
		this.label=label;
	}
	
	
	// returns the first endpoint
	public GraphNode firstEndpoint() {
		return u;
		
	}
	
	//returns the second endpoint
	public GraphNode secondEndpoint() {
		return v;
	}
	
	//returns the type of the edge.
	public int getType() {
		return type;
	}
	
	//sets the type of the edge to the specified value.
	public void setType(int newType) {
	    this.type = newType;
	}

	
	// returns the label of the edge.
	public String getLabel() {
		return label;
	}
	
	//sets the label of the edge to the specified value.
	public void setLabel(String label) {
		this.label=label;
	}
	
}
