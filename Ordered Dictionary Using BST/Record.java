public class Record {
	
	private String theData; // Stores the data for this record
	private Key theKey; // Stores the key associated with this record
	
    // Constructor to initialize Record with a Key and data
	public Record(Key k, String theData) {
		this.theData = theData;
		this.theKey = k;
	}
	
    // Returns the key associated with this record
	public Key getKey() {
		return theKey;
	}
	
    // Returns the data stored in this record
	public String getDataItem() { 
		return theData;
	}
}
