public class Interface {

    public static void main(String[] args) {
        // Ensure only one argument is provided (input file)
        if (args.length != 1) {
            System.out.println("Usage: java Interface inputFile");
            return;
        }

        String inputFileName = args[0];
        BSTDictionary dictionary = new BSTDictionary();  // Dictionary to store records
        StringReader keyboard = new StringReader();      // For reading user commands

        // Step 1: Read the input file and populate the dictionary
        try {
            readInputFile(inputFileName, dictionary);
        } catch (DictionaryException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Step 2: Start the command loop for user interaction
        while (true) {
            String command = keyboard.read("Enter next command: ").toLowerCase();
            if (command.equals("exit")) {
                break;  // Exit the loop if the command is "exit"
            }
            processCommand(command, dictionary);  // Process the user command
        }
    }

    // Reads the input file line by line and adds records to the dictionary
    private static void readInputFile(String inputFileName, BSTDictionary dictionary)
            throws DictionaryException {
        java.io.InputStream inputStream = null;
        try {
            inputStream = new java.io.FileInputStream(inputFileName);
            String label;
            while ((label = readLineFromFile(inputStream)) != null) {
                label = label.toLowerCase();  // Convert label to lowercase
                String line = readLineFromFile(inputStream);  // Read the next line for data
                if (line == null) {
                    System.out.println("Incomplete data for label: " + label);
                    break;
                }
                processRecord(label, line, dictionary);  // Process and add to dictionary
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + inputFileName);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                // Ignore
            }
        }
    }

    // Reads a line from the file input stream
    private static String readLineFromFile(java.io.InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                if (ch == '\n' || ch == '\r') {
                    // Handle Windows-style \r\n
                    if (ch == '\r') {
                        inputStream.mark(1);
                        int nextCh = inputStream.read();
                        if (nextCh != '\n') {
                            inputStream.reset();
                        }
                    }
                    break;
                }
                sb.append((char) ch);
            }
            if (sb.length() == 0 && ch == -1) {
                return null;
            }
            return sb.toString();
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }

    // Processes each record by determining its type and storing it in the dictionary
    private static void processRecord(String label, String line, BSTDictionary dictionary) throws DictionaryException {
        int type = 1;  // Default type
        String data;

        // Determine type based on the starting character or extension
        if (line.startsWith("-")) {
            type = 3;
            data = line.substring(1).trim();  // Data is a sound file
        } else if (line.startsWith("+")) {
            type = 4;
            data = line.substring(1).trim();  // Data is a music file
        } else if (line.startsWith("*")) {
            type = 5;
            data = line.substring(1).trim();  // Data is a voice file
        } else if (line.startsWith("/")) {
            type = 2;
            data = line.substring(1).trim();  // Data is a translation
        } else if (line.contains(".")) {
            // Determine type based on file extension
            String extension = line.substring(line.lastIndexOf(".") + 1);
            if (extension.equals("gif")) {
                type = 7;
            } else if (extension.equals("jpg")) {
                type = 6;
            } else if (extension.equals("html")) {
                type = 8;
            }
            data = line.trim();
        } else {
            type = 1;
            data = line.trim();  // Data is a text definition
        }

        // Create key and record and add it to the dictionary
        Key key = new Key(label, type);
        Record record = new Record(key, data);
        dictionary.put(record);
    }

    // Processes a command entered by the user
    private static void processCommand(String command, BSTDictionary dictionary) {
        if (command.isEmpty()) {
            System.out.println("Invalid command.");
            return;
        }

        String[] tokens = command.split(" ");
        String action = tokens[0];

        // Execute the appropriate method based on command action
        switch (action) {
            case "define":
                handleDefine(tokens, dictionary);
                break;
            case "translate":
                handleTranslate(tokens, dictionary);
                break;
            case "sound":
                handleMultimediaCommand(tokens, dictionary, 3, "sound");
                break;
            case "play":
                handleMultimediaCommand(tokens, dictionary, 4, "music");
                break;
            case "say":
                handleMultimediaCommand(tokens, dictionary, 5, "voice");
                break;
            case "show":
                handleMultimediaCommand(tokens, dictionary, 6, "image");
                break;
            case "animate":
                handleMultimediaCommand(tokens, dictionary, 7, "animated image");
                break;
            case "browse":
                handleMultimediaCommand(tokens, dictionary, 8, "webpage");
                break;
            case "delete":
                handleDelete(tokens, dictionary);
                break;
            case "add":
                handleAdd(tokens, dictionary);
                break;
            case "list":
                handleList(tokens, dictionary);
                break;
            case "first":
                handleFirst(dictionary);
                break;
            case "last":
                handleLast(dictionary);
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    // Handles the "define" command to get a definition
    private static void handleDefine(String[] tokens, BSTDictionary dictionary) {
        if (tokens.length < 2) {
            System.out.println("Invalid command format.");
            return;
        }
        String word = tokens[1];
        Record record = dictionary.get(new Key(word, 1));
        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("The word " + word + " is not in the dictionary");
        }
    }

    // Handles the "translate" command to get a translation
    private static void handleTranslate(String[] tokens, BSTDictionary dictionary) {
        if (tokens.length < 2) {
            System.out.println("Invalid command format.");
            return;
        }
        String word = tokens[1];
        Record record = dictionary.get(new Key(word, 2));
        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("There is no definition for the word " + word);
        }
    }

    // Handles multimedia commands like sound, play, show, etc.
    private static void handleMultimediaCommand(String[] tokens, BSTDictionary dictionary, int type, String fileType) {
        if (tokens.length < 2) {
            System.out.println("Invalid command format.");
            return;
        }
        String word = tokens[1];
        Record record = dictionary.get(new Key(word, type));
        if (record != null) {
            try {
                if (type == 3 || type == 4 || type == 5) {
                    SoundPlayer player = new SoundPlayer();
                    player.play(record.getDataItem());
                } else if (type == 6 || type == 7) {
                    PictureViewer viewer = new PictureViewer();
                    viewer.show(record.getDataItem());
                } else if (type == 8) {
                    ShowHTML browser = new ShowHTML();
                    browser.show(record.getDataItem());
                }
            } catch (MultimediaException e) {
                System.out.println("Error displaying " + fileType + " for " + word);
            }
        } else {
            System.out.println("There is no " + fileType + " file for " + word);
        }
    }

    // Handles the "delete" command to remove a record
    private static void handleDelete(String[] tokens, BSTDictionary dictionary) {
        if (tokens.length < 3) {
            System.out.println("Invalid command format.");
            return;
        }
        String word = tokens[1];
        int type = Integer.parseInt(tokens[2]);
        try {
            dictionary.remove(new Key(word, type));
        } catch (DictionaryException e) {
            System.out.println("No record in the ordered dictionary has key (" + word + "," + type + ")");
        }
    }

    // Handles the "add" command to add a new record
    private static void handleAdd(String[] tokens, BSTDictionary dictionary) {
        if (tokens.length < 4) {
            System.out.println("Invalid command format.");
            return;
        }
        String word = tokens[1];
        int type = Integer.parseInt(tokens[2]);

        // Reconstruct the content from tokens[3] onwards
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < tokens.length; i++) {
            sb.append(tokens[i]);
            if (i != tokens.length - 1) {
                sb.append(" ");
            }
        }
        String content = sb.toString();

        try {
            dictionary.put(new Record(new Key(word, type), content));
        } catch (DictionaryException e) {
            System.out.println("A record with the given key (" + word + "," + type + ") is already in the ordered dictionary");
        }
    }

    // Lists all records with labels starting with a given prefix
    private static void handleList(String[] tokens, BSTDictionary dictionary) {
        if (tokens.length < 2) {
            System.out.println("Invalid command format.");
            return;
        }
        String prefix = tokens[1];
        Record currentRecord = dictionary.smallest();
        boolean found = false;
        while (currentRecord != null) {
            if (currentRecord.getKey().getLabel().startsWith(prefix)) {
                System.out.print(currentRecord.getKey().getLabel() + ", ");
                found = true;
            }
            currentRecord = dictionary.successor(currentRecord.getKey());
        }
        if (!found) {
            System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
        } else {
            System.out.println();
        }
    }

    // Retrieves and displays the first record in the dictionary
    private static void handleFirst(BSTDictionary dictionary) {
        Record record = dictionary.smallest();
        if (record != null) {
            System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
        } else {
            System.out.println("The ordered dictionary is empty");
        }
    }

    // Retrieves and displays the last record in the dictionary
    private static void handleLast(BSTDictionary dictionary) {
        Record record = dictionary.largest();
        if (record != null) {
            System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
        } else {
            System.out.println("The ordered dictionary is empty");
        }
    }
}
