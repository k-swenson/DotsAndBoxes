public abstract class Board {
    protected int rows;
    protected int cols;
    protected int minRow;
    protected int maxRow;
    protected int minCol;
    protected int maxCol;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    protected boolean validSize() {
        return (rows >= minRow && rows <= maxRow && cols >= minCol && cols <= maxCol);
    }
    protected abstract void initialize();
    public abstract void display();
}