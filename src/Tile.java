public class Tile extends Piece {
    private int value;

    public Tile(int row, int col, int value) {
        super(row, col);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public static boolean compare(Tile t1, Tile t2) {
        return t1.getValue() == t2.getValue();
    }
}