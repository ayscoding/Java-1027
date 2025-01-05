public class Data {
    private String configuration;
    private int score;

    // Constructor to initialize the configuration and score
    public Data(String config, int score) {
        this.configuration = config;
        this.score = score;
    }

    // Returning the configuration stored in this Data object
    public String getConfiguration() {
        return configuration;
    }

    // Returning the score in this data
    public int getScore() {
        return score;
    }
}
