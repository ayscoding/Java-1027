import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze {

    private Graph graph; // Graph representation of the maze
    private int totalNodes; // Total number of nodes in the maze
    private int entranceNode; // ID of the entrance node
    private int exitNode; // ID of the exit node
    private int coins; // Number of coins available for opening doors
    private List<GraphNode> path; // List to store the current path during DFS

    // Constructor: Reads the input file and builds the graph
    public Maze(String inputFile) throws MazeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            path = new ArrayList<>(); // Initialize the path list
            readInput(reader); // Read and parse the input file
        } catch (IOException | GraphException e) {
            throw new MazeException(e.getMessage());
        }
    }

    // Returns the graph representing the maze
    public Graph getGraph() throws MazeException {
        if (graph == null) {
            throw new MazeException("Graph is null");
        }
        return graph;
    }

    // Solves the maze and returns an iterator over the path from entrance to exit
    public Iterator<GraphNode> solve() {
        try {
            // Clear any previous marks
            for (int i = 0; i < totalNodes; i++) {
                graph.getNode(i).mark(false);
            }
            path.clear();
            Iterator<GraphNode> result = DFS(coins, graph.getNode(entranceNode)); // Start DFS from entrance
            return result;
        } catch (GraphException e) {
            return null; // Return null if the DFS cannot find a path
        }
    }

    // DFS implementation for path finding
    private Iterator<GraphNode> DFS(int k, GraphNode current) throws GraphException {

        current.mark(true); // Mark the current node as visited
        path.add(current); // Add the current node to the path

        // Base case: Reached the exit node
        if (current.getName() == exitNode) {
            return path.iterator();
        }

        // Explore neighbors
        Iterator<GraphEdge> edges = graph.incidentEdges(current);
        while (edges != null && edges.hasNext()) {
            GraphEdge edge = edges.next();
            GraphNode neighbor = edge.firstEndpoint().equals(current) ? edge.secondEndpoint() : edge.firstEndpoint();

            if (!neighbor.isMarked() && k >= edge.getType()) { // Check if neighbor is unvisited and coins are sufficient
                Iterator<GraphNode> result = DFS(k - edge.getType(), neighbor);
                if (result != null) {
                    return result;
                }
            }
        }

        // Backtrack
        current.mark(false);
        path.remove(path.size() - 1);
        return null;
    }

    // Reads and parses the input file to build the graph
    private void readInput(BufferedReader inputReader) throws IOException, GraphException {
        int width, length;

        inputReader.readLine(); // Read and ignore S (scale factor)
        String widthStr = inputReader.readLine();
        String lengthStr = inputReader.readLine();
        String coinsStr = inputReader.readLine();

        if (widthStr == null || lengthStr == null || coinsStr == null) {
            throw new IOException("Invalid input file format");
        }

        width = Integer.parseInt(widthStr);
        length = Integer.parseInt(lengthStr);
        coins = Integer.parseInt(coinsStr);

        totalNodes = width * length;
        graph = new Graph(totalNodes);

        String line;
        List<String> mazeLines = new ArrayList<>();

        while ((line = inputReader.readLine()) != null) {
            mazeLines.add(line);
        }

        for (int row = 0; row < mazeLines.size(); row++) {
            String mazeLine = mazeLines.get(row);
            if (mazeLine.length() == 0) {
                continue; // Skip empty lines
            }
            if (row % 2 == 0) {
                // Even row: rooms and horizontal edges
                for (int col = 0; col < mazeLine.length(); col++) {
                    if (col % 2 == 0) {
                        // Room
                        char roomChar = mazeLine.charAt(col);
                        int nodeId = (row / 2) * width + (col / 2);
                        if (roomChar == 's') {
                            entranceNode = nodeId;
                        } else if (roomChar == 'x') {
                            exitNode = nodeId;
                        }
                        // No need to store the room unless needed
                    } else {
                        // Horizontal edge between rooms
                        char edgeChar = mazeLine.charAt(col);
                        if (edgeChar != 'w') {
                            int node1 = (row / 2) * width + (col - 1) / 2;
                            int node2 = (row / 2) * width + (col + 1) / 2;
                            int edgeType = edgeChar == 'c' ? 0 : Character.getNumericValue(edgeChar);
                            String label = edgeChar == 'c' ? "corridor" : "door";
                            insertEdge(node1, node2, edgeType, label);
                        }
                    }
                }
            } else {
                // Odd row: vertical edges
                for (int col = 0; col < mazeLine.length(); col++) {
                    if (col % 2 == 0) {
                        // Vertical edge between rooms
                        char edgeChar = mazeLine.charAt(col);
                        if (edgeChar != 'w') {
                            int node1 = ((row - 1) / 2) * width + (col / 2);
                            int node2 = ((row + 1) / 2) * width + (col / 2);
                            int edgeType = edgeChar == 'c' ? 0 : Character.getNumericValue(edgeChar);
                            String label = edgeChar == 'c' ? "corridor" : "door";
                            insertEdge(node1, node2, edgeType, label);
                        }
                    }
                }
            }
        }
    }

    // Inserts an edge between two nodes in the graph
    private void insertEdge(int node1, int node2, int linkType, String label) throws GraphException {
        if (node1 < 0 || node1 >= totalNodes || node2 < 0 || node2 >= totalNodes) {
            throw new GraphException("Invalid node indices");
        }

        GraphNode u = graph.getNode(node1);
        GraphNode v = graph.getNode(node2);

        graph.insertEdge(u, v, linkType, label);
    }
}
