/**
 * DotsAndBoxesBoard
 * Represents 2D game board for dots and boxes
 * Instead of a traditional 2D array for pieces,
 * class uses two 2d arrays of horizontal and vertical
 * edges to check when a box is closed.
 * Box winners are stored in its own 2d array.
 */

public class DotsAndBoxesBoard extends Board {
    private final int MIN_ROWS = 1;
    private final int MIN_COLS = 1;
    private final int MAX_ROWS = 50;
    private final int MAX_COLS = 50;

    private Edge[][] horizontalEdges;    // (rows + 1) x cols
    private Edge[][] verticalEdges;   // rows x (cols + 1)
    private Player[][] boxWinners;

    public DotsAndBoxesBoard(int rows, int cols) {
        super(rows, cols);
        this.maxRow = MAX_ROWS;
        this.maxCol = MAX_COLS;
        this.minRow = MIN_ROWS;
        this.minCol = MIN_COLS;
        this.horizontalEdges = new Edge[rows+1][cols];
        this.verticalEdges = new Edge[rows][cols+1];
        this.boxWinners = new Player[rows][cols];
        initialize();
    }

    protected void initialize() {
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c < cols; c++) {
                horizontalEdges[r][c] = new Edge(r, c, true);
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols; c++) {
                verticalEdges[r][c] = new Edge(r, c, false);
            }
        }
    }

    public boolean takeEdge(int row, int col, String direction, Player player) {
        String dir = direction.toLowerCase();
        switch (dir) {
            case "u": {   // UP: Vertical Edge above node
                if (row - 1 < 0 || row - 1 >= rows || col < 0 || col > cols) {
                    return false;   // Tried taking out of bounds edge
                }
                Edge e = verticalEdges[row - 1][col];
                if (e.isTaken()) {
                    return false;   // Tried taking already taken edge
                }
                e.take(player);
                break;
            }
            case "d": {   // DOWN: Vertical Edge below node
                if (row < 0 || row >= rows || col < 0 || col > cols) {
                    return false;
                }
                Edge e = verticalEdges[row][col];
                if (e.isTaken()) {
                    return false;
                }
                e.take(player);
                break;
            }
            case "l": {   // LEFT: Horizontal Edge to the left of node
                if (row < 0 || row > rows || col - 1 < 0 || col - 1 >= cols) {
                    return false;
                }
                Edge e = horizontalEdges[row][col - 1];
                if (e.isTaken()) {
                    return false;
                }
                e.take(player);
                break;
            }
            case "r": {   // RIGHT: Horizontal Edge to the right of node
                if (row < 0 || row > rows || col < 0 || col >= cols) {
                    return false;
                }
                Edge e = horizontalEdges[row][col];
                if (e.isTaken()) {
                    return false;
                }
                e.take(player);
                break;
            }
            default:
                return false;   // Invalid direction
        }
        checkForFullBoxes(player);
        return true;
    }

    private void checkForFullBoxes(Player player) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (boxWinners[r][c] == null) {
                    if (horizontalEdges[r][c].isTaken() &&  // check four surrounding edges
                            horizontalEdges[r+1][c].isTaken() &&
                            verticalEdges[r][c].isTaken() /*shawn changed this to verticalEdges*/&&
                            verticalEdges[r][c+1].isTaken()) {
                        boxWinners[r][c] = player;
                        player.incrementScore();
                    }
                }
            }
        }
    }

    public boolean isFull() {
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!horizontalEdges[r][c].isTaken()) {
                    return false;
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols; c++) {
                if (!verticalEdges[r][c].isTaken()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        System.out.println();

        // Column indexes
        System.out.print("    ");
        for (int c = 0; c <= cols; c++) {
            System.out.printf("%-4d", c);
        }
        System.out.println();

        for (int r = 0; r <= rows; r++) {
            System.out.printf("%3d ", r);

            // Print horizontal edges
            for (int c = 0; c < cols; c++) {
                System.out.print(".");
                if (horizontalEdges[r][c].isTaken()) {
                    System.out.print("———");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println(".");

            if (r < rows) {
                System.out.print("    ");
                for (int c = 0; c <= cols; c++) {
                    if (verticalEdges[r][c].isTaken()) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }

                    if (c < cols) {
                        if (boxWinners[r][c] != null) {
                            System.out.print(" " + boxWinners[r][c].getName().charAt(0) + " ");
                        } else {
                            System.out.print("   ");
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}