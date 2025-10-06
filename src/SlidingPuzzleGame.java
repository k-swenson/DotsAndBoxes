import java.util.Scanner;

public class SlidingPuzzleGame extends Game {
    private boolean playAgain;
    private SlidingPuzzleBoard board;
    private Player player;

    public SlidingPuzzleGame(Scanner scanner) {
        this.in = scanner;
        this.playAgain = false;
        this.players = new Player[1];
        this.player = players[0];
    }

    public void run() {
        System.out.println("Welcome to the Sliding Puzzle Game!");
        String playerName;
        while (true) {
            System.out.print("What is your name? ");
            playerName = in.nextLine().trim();
            if (!playerName.isEmpty()) {
                break;
            }
            System.out.println("Name cannot be empty. Please enter a valid name.");
        }
        player = new Player(playerName);
        System.out.println(String.format("Hi %s! Customize the size of your sliding puzzle:", playerName));
        do {
            player.resetScore();
            boardInput();
            gameTurn();
            board.display();
            System.out.print(String.format("Congrats %s! You solved the sliding puzzle in %d moves! Play again?(y/n): ",
                    player.getName(), player.getScore()));
            String input = in.nextLine();
            input = input.toLowerCase();
            playAgain = input.equals("y") || input.equals("yes");
        } while (playAgain);
    }

    public void gameTurn() {
        while (!board.isSolved()){
            board.display();
            String msg = String.format("%s, which tile do you want to slide to the empty space? ",
                    player.getName());
            int tile = readInt(msg);
            if (!board.slideTile(tile)) {
                System.out.println("Invalid tile number, try again.");
            } else {
                player.incrementScore();
            }
        }
    }

    private void boardInput() {
        boolean validBoard = false;
        do {
            int rows = readInt("Number of Rows? ");
            int cols = readInt("Number of Columns? ");
            try {
                board = new SlidingPuzzleBoard(rows, cols);
                validBoard = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ", try again.");
            }
        } while(!validBoard);
    }

    private int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            String num = in.nextLine().trim();
            try {
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid integer, try again.");
            }
        }
    }
}