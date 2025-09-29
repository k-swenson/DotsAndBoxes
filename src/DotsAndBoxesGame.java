public class DotsAndBoxesGame {
    private DotsAndBoxesBoard board;

    public void start() {
        board = new DotsAndBoxesBoard(4, 4);
        board.display();
    }
}