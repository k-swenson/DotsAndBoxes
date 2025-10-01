public class DotsAndBoxesBoard {
    private int rows;
    private int cols;
    private List<Edge> horizontalEdges;
    private List<Edge> verticalEdges;
    private

    public DotsAndBoxesBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.horizontalEdges = new ArrayList<>();
        this.verticalEdges = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {

    }

    public boolean takeEdge(int row, int col, String direction, Player player) {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    void displayBoard() {

    }
}