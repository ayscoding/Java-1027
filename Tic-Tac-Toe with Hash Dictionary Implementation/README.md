# Tic-Tac-Toe with Hash Dictionary Implementation

This project implements a Tic-Tac-Toe game where the computer and a human player compete on a customizable board. The game is enhanced by efficient data storage and retrieval techniques using a custom-built hash table (HashDictionary) with separate chaining for collision resolution. The program also evaluates game configurations to determine optimal moves for the computer player.

## Features

### 1. Hash Dictionary (HashDictionary)
- Implements a Dictionary Abstract Data Type (DictionaryADT) using a hash table with separate chaining.
- Efficiently stores and retrieves board configurations and their scores using a custom polynomial hash function.
- Handles collisions gracefully while maintaining performance.
- Key operations:
  - `put(Data record)`: Adds a record to the dictionary and detects collisions.
  - `remove(String config)`: Removes a record based on its configuration key.
  - `get(String config)`: Retrieves the score of a given configuration.
  - `numRecords()`: Returns the total number of stored records.

### 2. Game Logic (Configurations)
- Supports a customizable board size (`n x n`) and winning sequence length (`k`).
- Allows saving and querying board configurations.
- Implements efficient game evaluations:
  - Checks for win conditions (rows, columns, diagonals).
  - Determines if the game is a draw or still undecided.
  - Evaluates board state for optimal computer moves using a recursive algorithm and memoization with the hash table.
- Key operations:
  - `createDictionary()`: Initializes an empty hash table.
  - `addConfiguration()`: Stores a new board configuration and its score.
  - `repeatedConfiguration()`: Checks if a configuration exists in the hash table.
  - `wins(char symbol)`: Verifies if a player has won.
  - `evalBoard()`: Assigns scores based on game outcomes (e.g., Computer Wins, Draw).

### 3. Data Representation (Data Class)
- Stores individual board configurations and their associated scores.
- Provides methods to retrieve configuration details and scores.

## How It Works
- Players alternate turns placing tiles (`X` for human, `O` for computer) on the board.
- The computer evaluates all possible moves using a recursive game tree approach and memoization with the hash table.
- Configurations and their scores are stored to avoid redundant computations, improving performance.
- The game ends when a player forms a sequence of length `k`, or the board is full, resulting in a draw.

## Getting Started
1. Clone the repository and compile the Java files.
2. Run the game with the following command:
   ```bash
   java Play <board size> <length to win> <max levels>

Example 
java Play 3 3 5
