/**
 * DotsAndBoxesGame
 * Runs the terminal Dots and Boxes: reads players/board, manages turns and input,
 * applies moves via DotsAndBoxesBoard, and prints results.
 */

 import java.util.*;

public class DotsAndBoxesGame {
    private DotsAndBoxesBoard board;
    private boolean keepPlaying; 
    private Player[] players = new Player[2];
    private int curPlayerIdx = 0; //current player index
    private Scanner in = new Scanner(System.in);
    

    public void run() {

        welcome();
        keepPlaying = true;

        while (keepPlaying) {
            // Enter the name
            System.out.print("Enter Player 1's Name: ");
            String name1 = in.nextLine().trim();
            if (name1.isEmpty()) name1 = "Player1";

            System.out.print("Enter Player 2's Name: ");
            String name2 = in.nextLine().trim();
            if (name2.isEmpty()) name2 = "Player2";

            players[0] = new Player(name1);
            players[1] = new Player(name2);
            curPlayerIdx = 0;

            // size of the board (min 1)
            int rows = readInt("Enter board rows (min 1): ", 1);
            int cols = readInt("Enter board columns (min 1): ", 1);
            
            // initialize the board
            board = new DotsAndBoxesBoard(rows, cols);
            play();

            // ask if want to play again
            keepPlaying = askIfAgain();
        }
    }

    private void welcome(){
        System.out.println("=======================================");
        System.out.println("   CS611 Assignment 2: Dot and Boxes   ");
        System.out.println("=======================================");
        System.out.println("This is a two-player terminal game.");
        System.out.println("Draw lines to connect the dots and form boxes.");
        System.out.println("Try to complete as many boxes as possible!\n");
    }

    private void play() {
        while (!board.isFull()) {
            board.displayBoard();

            // current player
            Player current = players[curPlayerIdx];
            System.out.println(current.getName() + "'s turn");

            // read the input
            int row = readInt("Enter row: ", 0);
            int col = readInt("Enter col: ", 0);
            String direction = readDirection("Enter direction (u/d/l/r for up, down, left or right): ");

            int before = current.getScore();
            boolean ok = board.takeEdge(row, col, direction, current);
            if (!ok) {
                System.out.println("---!!!Invalid or already taken edge. Try again.!!!---");
                continue; // let the player select again
            }
            int after = current.getScore();

            // if not scored, switch user
            if (after == before && !board.isFull()) {
                curPlayerIdx = 1 - curPlayerIdx;
            }
        }
        end();
    }

    private void end(){
        board.displayBoard();
        showResult();
    }

    //print out results and display results
    private void showResult(){
        System.out.println("\n=======================================");
        System.out.println("           GAME OVER!                 ");
        System.out.println("=======================================");
        
        // Display final scores
        System.out.println("Final Scores:");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + ": " + players[i].getScore() + " boxes");
        }
        
        // Determine winner
        int player1Score = players[0].getScore();
        int player2Score = players[1].getScore();
        
        if (player1Score > player2Score) {
            System.out.println("\n🎉 " + players[0].getName() + " wins!");
        } else if (player2Score > player1Score) {
            System.out.println("\n🎉 " + players[1].getName() + " wins!");
        } else {
            System.out.println("\n🤝 It's a tie!");
        }
        
        System.out.println("=======================================\n");
    }

    private boolean askIfAgain(){
        while (true) {
            System.out.print("Would you like to play again? (y/n): ");
            String response = in.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                System.out.println("Thanks for playing! Goodbye!");
                return false;
            } else {
                System.out.println("Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    // read a int with min
    // make sure the input is valid
    private int readInt(String prompt, int min){
        while(true){
            System.out.print(prompt);
            String line = in.nextLine();
            try{
                int value = Integer.parseInt(line);
                if (value < min) {
                    System.out.println("Value must be at least " + min + ". Try again.");
                }
                else{
                    return value;
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    // read the user input directions (up, down , left or right)
    // and keep the input valid
    private String readDirection(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toUpperCase();
            if (s.equals("U") || s.equals("D") || 
                s.equals("L") || s.equals("R")){
                return s;
            }
            System.out.println("Invalid direction. Use U/D/L/R.");
        }
    }
}