public class DotsAndBoxesBoard {
    private int rows;
    private int cols;
    private Edge[][] horizontalEdges;    // (rows + 1) x cols
    private Edge[][] verticalEdges;   // rows x (cols + 1)
    private char[][] boxWinners;

    public DotsAndBoxesBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.horizontalEdges = new Edge[rows+1][cols];
        this.verticalEdges = new Edge[rows][cols+1];
        this.boxWinners = new char[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
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
                if (boxWinners[r][c] == '\u0000') {
                    if (horizontalEdges[r][c].isTaken() &&  // check four surrounding edges
                            horizontalEdges[r+1][c].isTaken() &&
                            verticalEdges[r][c].isTaken() /*shawn changed this to verticalEdges*/&&
                            verticalEdges[r][c+1].isTaken()) {
                        boxWinners[r][c] = player.getName().charAt(0);
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

    public void displayBoard() {
        for (int r = 0; r < rows; r++) {
            // Print top of boxes
            for (int c = 0; c < cols; c++) {
                System.out.print(".");
                if (horizontalEdges[r][c].isTaken()) {
                    System.out.print("———");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println(".");

            // Print sides of boxes and box winners
            for (int c = 0; c < cols; c++) {
                if (verticalEdges[r][c].isTaken()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                char owner = boxWinners[r][c];
                if (owner == '\u0000') {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + owner + " ");
                }
            }
            if (verticalEdges[r][cols].isTaken()) {
                System.out.println("|");
            } else {
                System.out.println(" ");
            }
        }
        // Print last row
        for (int c = 0; c < cols; c++) {
            System.out.print(".");
            if (horizontalEdges[rows][c].isTaken()) {
                System.out.print("———");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println(".");
    }
}