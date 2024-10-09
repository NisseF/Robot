public class TableTop {

    private final int width = 4;
    private final int height = 4;

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}