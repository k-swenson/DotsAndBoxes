public class Edge {
    private int row;
    private int col;
    private boolean horizontal;
    private Player takenBy;

    public Edge(int row, int col, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.horizontal = horizontal;
        takenBy = null;
    }

    public boolean isTaken() {
        return takenBy != null;
    }

    public Player isTakenBy() {
        return takenBy;
    }

    public void take(Player player) {
        takenBy = player;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}