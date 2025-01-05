public class Configurations {

    private char[][] board; // Game board
    private int boardSize;  // Size of the board
    private int lengthToWin;  // Length of sequence needed to win

    // /Initialize the board and other parameters constructor
    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        board = new char[boardSize][boardSize];

        // Initialize board empty spaces
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';  // Space for empty positions
            }
        }
    }

    // Creating a new HashDictionary
    public HashDictionary createDictionary() {
        return new HashDictionary(8011);  // Prime number close to the suggested range (6000-10000)
    }

    // Checking if string in hashTable
    public int repeatedConfiguration(HashDictionary hashTable) {
        String boardConfig = boardToString();
        return hashTable.get(boardConfig);
    }

    // Adding a configuration to the hashTable
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String boardConfig = boardToString();
        Data data = new Data(boardConfig, score);
        try {
            hashDictionary.put(data);
        } catch (DictionaryException e) {
            System.out.println("Configuration already exists");
        }
    }

    // Storing a symbol at specified board location
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Checking if a square is empty
    public boolean squareIsEmpty(int row, int col) {
        if (board[row][col] == ' ') {
            return true;
        } else {
            return false;
        }
    }

    // Checking if the current player has won with the specified symbol
    public boolean wins(char symbol) {
        // Checking rows, columns, and diagonals for a winning sequence
        if (checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol)) {
        	return true;
        } else {
        	return false;
        }
    }

    // Checking if the game is a draw
    public boolean isDraw() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;  // No empty space, so not a draw
                }
            }
        }
        return true;  // If no empty spaces, then draw
    }

    // Evaluating the board
    public int evalBoard() {
        if (wins('O')) {
            return 3;  // Computer wins
        } else if (wins('X')) {
            return 0;  // Human wins
        } else if (isDraw()) {
            return 2;  // Draw
        } else {
            return 1;  // Game is still undecided
        }
    }

    // Converting the board into a string representation
    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    // Checking rows for a winning sequence
    private boolean checkRows(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            int count = 0;
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == symbol) {
                    count++;
                    if (count == lengthToWin) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    // Checking columns for a winning sequence
    private boolean checkColumns(char symbol) {
        for (int j = 0; j < boardSize; j++) {
            int count = 0;
            for (int i = 0; i < boardSize; i++) {
                if (board[i][j] == symbol) {
                    count++;
                    if (count == lengthToWin) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    // Checking diagonals for a winning sequence
    private boolean checkDiagonals(char symbol) {
        // Checking left-to-right diagonals
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = 0; j <= boardSize - lengthToWin; j++) {
                if (checkDiagonal(i, j, symbol)) {
                    return true;
                }
            }
        }
        // Checking right-to-left diagonals
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = lengthToWin - 1; j < boardSize; j++) {
                if (checkReverseDiagonal(i, j, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checking a single diagonal (left-to-right)
    private boolean checkDiagonal(int row, int col, char symbol) {
        int count = 0;
        for (int i = 0; i < lengthToWin; i++) {
            if (board[row + i][col + i] == symbol) {
                count++;
                if (count == lengthToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checking a reverse diagonal (right-to-left)
    private boolean checkReverseDiagonal(int row, int col, char symbol) {
        int count = 0;
        for (int i = 0; i < lengthToWin; i++) {
            if (board[row + i][col - i] == symbol) {
                count++;
                if (count == lengthToWin) {
                    return true;
                }
            }
        }
        return false;
    }
}
