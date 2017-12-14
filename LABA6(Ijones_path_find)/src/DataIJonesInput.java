public class DataIJonesInput {
    private int width;
    private int height;
    private String[] words;

    public DataIJonesInput(int width, int height, String[] words) {
        this.width = width;
        this.height = height;
        this.words = words;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String[] getWords() {
        return words;
    }
}