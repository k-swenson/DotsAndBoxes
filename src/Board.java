public abstract class Board {
    protected int rows;
    protected int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    protected abstract boolean validSize(int rows, int cols);
    protected abstract void initialize();
    public abstract void display();
}