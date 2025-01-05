public class Key {
	
	private String theLabel; // Stores the label of this key
	private int theType;     // Stores the type associated with this key
	
	// Constructor to initialize Key with a label and type
	public Key(String theLabel, int theType) {
		this.theLabel = theLabel.toLowerCase(); // Convert label to lowercase for case-insensitive storage
		this.theType = theType;
	}
	
	// Returns the label of this key
	public String getLabel() {
		return theLabel;
	}

	// Returns the type of this key
	public int getType() {
		return theType;
	}
	
	// Compares this key with another key
	public int compareTo(Key k) {
		
		// If both label and type are equal, the keys are considered equal
		if (this.theLabel.equals(k.theLabel) && this.theType == k.theType) {
			return 0;
		}
		
		// Compare labels lexicographically (alphabetical order)
		int labelComparison = this.theLabel.compareTo(k.theLabel);
		
		// If label comparison shows this label is smaller, return -1
		if (labelComparison < 0) {
			return -1;	
		} else if (labelComparison > 0) {
			return 1; // If this label is greater, return 1
		} else {
			// If labels are the same, compare by type numerically
			return Integer.compare(this.theType, k.theType);
		}
	}
}
