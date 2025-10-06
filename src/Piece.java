/**
 * Piece
 * Abstract base class of a piece on a game board.
 * Stores postion on board.
 */

public class Piece {
    protected int row;
    protected int col;

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}