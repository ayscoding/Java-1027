import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graph implements GraphADT {
	
	
    // Adjacency list: Each index represents a node, and its ArrayList stores its edges
	private ArrayList<ArrayList<GraphEdge>> adjacencyList;
	private ArrayList<GraphNode> nodes; // List to store all the nodes in the graph
	
	public Graph(int n) {
		
		//initialize representation with empty adjacency lists
		adjacencyList = new ArrayList<>();
		nodes = new ArrayList<>();
		
		for (int i =0; i<n ; i++) {
			GraphNode node = new GraphNode(i); // Create a node with name 'i'
			nodes.add(node); // Add the node to the nodes list
			adjacencyList.add(new ArrayList<>()); // Add an empty list for the node's edges			
		}
	}
	
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
  
		// Check if both nodes exist in the graph
		if (!nodes.contains(nodeu) || !nodes.contains(nodev)) {
		throw new GraphException("");
		}
		
        // Check if an edge already exists between the nodes
		ArrayList<GraphEdge> edges = adjacencyList.get(nodeu.getName());
		for (GraphEdge edge : edges) {
		    if ((edge.firstEndpoint().equals(nodeu) && edge.secondEndpoint().equals(nodev)) ||
		            (edge.firstEndpoint().equals(nodev) && edge.secondEndpoint().equals(nodeu))) {
		            throw new GraphException("Edge already exists between the specified nodes.");
		    }
		}
		
		// Create a new edge and add it to both nodes' adjacency lists
		GraphEdge edge = new GraphEdge(nodeu, nodev, type, label);
		adjacencyList.get(nodeu.getName()).add(edge); // Add to both nodes since it's undirected
		adjacencyList.get(nodev.getName()).add(edge); // Add to both nodes since it's undirected
	
	}

	@Override
	public GraphNode getNode(int u) throws GraphException {
		
		// Validate if the node index exists
		if (u < 0 || u >= nodes.size()) {
            throw new GraphException("");
        }
		
		return nodes.get(u);
	}

	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {

		// Validate if the node exists
		if (!nodes.contains(u)) {
			throw new GraphException("");
		}
		
		// Get the adjacency list for the node and return its iterator
		ArrayList<GraphEdge> edges = adjacencyList.get(u.getName());
		return edges.isEmpty()? null : edges.iterator();
	}

	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		
		// Validate if both nodes exist
		if (!nodes.contains(u) || !nodes.contains(v)) {
			throw new GraphException("");
		}
		
		// Search for the edge in the adjacency list of 'u'
		ArrayList<GraphEdge> edges = adjacencyList.get(u.getName());
		for (GraphEdge edge : edges) {
			// Check both endpoints appropriately.
	        if ((edge.firstEndpoint().equals(u) && edge.secondEndpoint().equals(v)) ||
	            (edge.firstEndpoint().equals(v) && edge.secondEndpoint().equals(u))) {
	            return edge;
	        }
	    }
	    // Throw exception if no such edge exists
	    throw new GraphException("No edge exists between the specified nodes.");
	}

	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		
		try {
	        getEdge(u, v);
	        return true;
	    } catch (GraphException e) {
	        return false;
	    }
	}
	
	

}
