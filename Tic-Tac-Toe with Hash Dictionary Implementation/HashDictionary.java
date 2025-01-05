public class HashDictionary implements DictionaryADT {
    // Inner class to represent the key-value pair
    private class HashEntry {
        Data record;
        HashEntry next;  // next node

        public HashEntry(Data record) {
            this.record = record;
            this.next = null;  // Make next as null 
        }
    }

    private HashEntry[] table;  // The hash table
    private int numRecords;     // Number of records in the dictionary

    // Constructor to create an empty dictionary of the specified size
    public HashDictionary(int size) {
        table = new HashEntry[size];  // Initialize the hash table
        numRecords = 0;
    }

    // Hash function using polynomial accumulation
    private int hashFunction(String key) {
        int hashVal = 0;
        int prime = 31;  // Prime number used to reduce collisions
        for (int i = 0; i < key.length(); i++) {
            hashVal = prime * hashVal + key.charAt(i);
        }
        return Math.abs(hashVal % table.length);  // Return the hash index
    }

    // Adds records to the dictionary
    public int put(Data record) throws DictionaryException {
        int hashIndex = hashFunction(record.getConfiguration());

        // Check for collisions and if the record already exists
        HashEntry current = table[hashIndex];
        boolean collision = current != null;  // Collision index is already occupied

        while (current != null) {
            if (current.record.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException(); // If record already in the dictionary 
            }
            current = current.next;  // Move to the next entry
        }

        // Add the new record to the front
        HashEntry newEntry = new HashEntry(record);
        newEntry.next = table[hashIndex];  // Set the new entry's next to the current head
        table[hashIndex] = newEntry;       // Insert the new entry at the start
        numRecords++;                      // Increment the number of records

        return collision ? 1 : 0;  // Return 1 if a collision occurred, 0 otherwise
    }

    // Removing a record from the dictionary
    public void remove(String config) throws DictionaryException {
        int hashIndex = hashFunction(config);
        HashEntry current = table[hashIndex];
        HashEntry prev = null;

        // Search for the record in the chain
        while (current != null) {
            if (current.record.getConfiguration().equals(config)) {
                // Remove the current record from the chain
                if (prev == null) {
                    table[hashIndex] = current.next;  // Remove the head of the chain
                } else {
                    prev.next = current.next;  // Bypass the current record
                }
                numRecords--;  // Decrement the number of records
                return;
            }
            prev = current;  // Move prev pointer
            current = current.next;  // Move to the next entry
        }

        // If the record is not found, throw exception
        throw new DictionaryException();
    }

    // Retrieving a score given a configuration
    public int get(String config) {
        int hashIndex = hashFunction(config);
        HashEntry current = table[hashIndex];

        // Search for the record
        while (current != null) {
            if (current.record.getConfiguration().equals(config)) {
                return current.record.getScore();  // Return the score of the found record
            }
            current = current.next;  // Move to the next entry
        }

        return -1;  // Return -1 if the configuration is not found
    }

    // Returning the number of records in the dictionary
    public int numRecords() {
        return numRecords;
    }
}
