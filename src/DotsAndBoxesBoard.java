public class DotsAndBoxesBoard {
    private int rows;
    private int cols;

    public DotsAndBoxesBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initializeBoard();
    }

    public void initializeBoard() {

    }

    public boolean takeEdge(int row, int col, String direction, Player player) {
        return false;
    }

    public boolean isEdgeTaken(int row, int col, String direction) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    void displayBoard() {

    }
}