# Ordered Dictionary Using Binary Search Tree

This project is an implementation of an **Ordered Dictionary** using a **Binary Search Tree (BST)** in Java. The dictionary supports various operations on key-value pairs and includes a user-friendly text-based interface for interaction.

## Features

### 1. Binary Search Tree (BST) Implementation
- **Custom BST Implementation**:
  - Nodes store `Record` objects containing a `Key` (label and type) and associated data.
  - Supports efficient insertion, retrieval, deletion, and traversal of records.
- **Key Operations**:
  - Retrieve a record based on its key.
  - Insert new records while maintaining the BST structure.
  - Remove records by key.
  - Find successor and predecessor nodes.
  - Retrieve the smallest and largest records in the tree.

### 2. Ordered Dictionary (BSTDictionary)
- Implements a dictionary based on the BST structure.
- Supports:
  - Adding new records.
  - Retrieving records by key.
  - Deleting records.
  - Finding successors, predecessors, and extreme records (smallest/largest).
- Efficiently handles operations using the BST’s logarithmic height-based complexity.

### 3. Key and Record Management
- **Key**:
  - Composed of a `label` (case-insensitive) and a `type`.
  - Provides comparison methods for lexicographic ordering and type precedence.
- **Record**:
  - Stores data and a unique key.
  - Easily accessible through getters.

### 4. Interactive User Interface
- **Command-Line Interaction**:
  - Load data from an input file containing label, type, and data.
  - Supports various commands, including:
    - Define, translate, play audio, display images, animate files, browse webpages.
    - Add, delete, and list records.
    - Display the smallest or largest record.
    - Search for records by prefix.
- **Error Handling**:
  - Graceful handling of invalid commands and missing files with informative messages.

### 5. Multimedia Integration
- Plays sound and music files (`.wav`, `.mid`).
- Displays images (`.jpg`, `.gif`) and web pages (`.html`).
- Supports animations and voice files.

## How It Works
1. **Input File**:
   - Reads an input file containing records formatted as:
     ```
     label
     type and data
     ```
     For example:
     ```
     homework
     Very enjoyable work that students need to complete outside the classroom.
     ```
   - Type and data are inferred based on the content (e.g., file extension, special characters).
2. **User Commands**:
   - Accepts commands like `define`, `add`, `delete`, `list`, and more.
   - Displays or plays multimedia files based on the record type.
3. **BST Operations**:
   - Internally manages records with efficient tree operations.

## Example Commands
- `define homework`: Displays the definition of the word.
- `sound roar`: Plays the audio file associated with the word.
- `list co`: Lists all records with labels starting with "co".
- `delete flower 6`: Deletes the record with label "flower" and type `6`.

## Setup and Execution
1. Clone the repository and compile the Java files.
2. Run the program with:
   ```bash
   java Interface inputFile
