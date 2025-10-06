public class Edge extends Piece{
    private boolean horizontal;
    private Player takenBy;

    public Edge(int row, int col, boolean horizontal) {
        super(row, col);
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
}