import java.util.Scanner;

public class GameManager {
    private Scanner scanner;
    private Game currentGame;
    private boolean quit;

    public GameManager() {
        this.scanner = new Scanner(System.in);
        this.quit = false;
    }

    public void start() {
        printWelcomeMsg();

        while (!quit) {
            int choice = pickGame();

            switch (choice) {
                case 1:
                    DotsAndBoxesGame game = new DotsAndBoxesGame(scanner);  // change this!!!!
                    game.run();
                    break;
                case 2:
                    currentGame = new SlidingPuzzleGame(scanner);
                    currentGame.run();
                    break;
                case 3:
                    quit();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void printWelcomeMsg() {
        System.out.println("=======================================");
        System.out.println("      Welcome to the Game Manager!     ");
        System.out.println("=======================================");
        System.out.println("This program lets you play the following games:");
        System.out.println("1) Dots and Boxes");
        System.out.println("2) Sliding Puzzle");
        System.out.println();
    }

    private int pickGame() {
        System.out.println("---------------------------------------");
        System.out.println("Game Menu");
        System.out.println("1) Dots and Boxes");
        System.out.println("2) Sliding Puzzle");
        System.out.println("3) Quit");
        System.out.println("Enter your choice: ");

        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
        System.out.println();
        return choice;
    }

    private void quit() {
        System.out.println("Goodbye!");
        quit = true;
    }
}