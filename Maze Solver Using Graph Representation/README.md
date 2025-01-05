# Maze Solver Using Graph Representation

This project implements a maze-solving program in Java. The maze is represented as an undirected graph, with nodes representing rooms and edges representing corridors or doors. The program identifies a path from the maze entrance to the exit, respecting constraints on the number of coins available to open doors.

## Features

### 1. Maze Representation
- **Graph-Based Structure**:
  - Nodes represent rooms in the maze.
  - Edges represent corridors or doors.
  - Each door edge has a cost in coins to open (0-9 coins).
- **Entrance and Exit**:
  - Special nodes in the graph denote the entrance and exit of the maze.

### 2. Graph Implementation
- Supports graph representation using adjacency lists or matrices.
- Implements essential graph operations:
  - Adding nodes and edges.
  - Checking adjacency between nodes.
  - Retrieving incident edges for a node.

### 3. Maze Solving Algorithm
- Uses a **modified Depth-First Search (DFS)** to find a path from entrance to exit.
- Respects coin constraints:
  - Tracks remaining coins and avoids paths requiring more coins than available.
- Backtracking ensures all possible paths are explored.
- Returns a valid path as an ordered sequence of nodes if one exists; otherwise, it indicates no solution.

### 4. Input File Format
- The maze is read from a structured text file with the following components:
  - **Scale Factor (S)**: Not used in the program.
  - **Width (A)** and **Length (L)**: Dimensions of the maze grid.
  - **Coins (k)**: Total coins available to open doors.
  - Maze layout in grid format:
    - `s`: Entrance.
    - `x`: Exit.
    - `o`: Room.
    - `c`: Corridor.
    - `w`: Wall.
    - `0-9`: Door requiring the specified number of coins to open.

### 5. Program Structure
- **GraphNode**:
  - Represents a node (room) in the graph.
  - Supports marking and retrieval of node properties.
- **GraphEdge**:
  - Represents an edge (corridor or door) in the graph.
  - Supports setting and retrieving edge properties.
- **Graph**:
  - Implements the graph data structure and related operations.
- **Maze**:
  - Parses the input file and builds the graph.
  - Solves the maze using DFS or a similar approach.
  - Tracks entrance and exit nodes for solution computation.

### 6. Example Input File


### 7. How It Works
1. Parse the input file to construct the graph:
   - Nodes represent rooms.
   - Edges represent corridors and doors.
2. Implement a search algorithm:
   - Use DFS to explore paths from entrance to exit.
   - Respect coin constraints while traversing doors.
3. Return the path from entrance to exit as a list of nodes.

### 8. Execution
- Compile and run the program with:
  ```bash
  java Solve inputFile
