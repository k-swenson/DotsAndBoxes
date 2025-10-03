import java.util.*;

public class DotsAndBoxesGame {
    private DotsAndBoxesBoard board;
    private boolean keepPlaying; 
    private Player[] players = new Player[2];
    private int curPlayerIdx = 0; //current player index
    private Scanner in = new Scanner(System.in);
    

    public void run() {

        welcome();
        //start by asking the users' name
        System.out.print("Enter Player's Name:");
        String name = in.nextLine();
        player = new Player(name);        

        // welcome message
        int rows = readInt("Enter board rows (min 2): ", 1);
        int cols = readInt("Enter board columns (min 2): ", 1);
        
        //initialize the game
        board = new DotsAndBoxesBoard(rows, cols);
        play();
        //ask if want to play again
        /* 
        board = new DotsAndBoxesBoard(4, 4);
        board.display();
        */
    }

    private void welcome(){
        System.out.println("=======================================");
        System.out.println("   CS611 Assignment 2: Dot and Boxes   ");
        System.out.println("=======================================");
        System.out.println("This is a single-player terminal game.");
        System.out.println("Draw lines to connect the dots and form boxes.");
        System.out.println("Try to complete as many boxes as possible!\n");
    }
    //!board.isFull()

    private void play() {
        while (keepPlaying && !board.isFull()) {
            board.displayBoard();
            // two people take turns
            Player current = players[curPlayerIdx];
            System.out.println(current.getName( + " 's turn"));

            // read the user input
            int row = readInt("Enter row: ", 0);
            int col = readInt("Enter col: ", 0);
            String direction = readDirection("Enter direction (U/D/L/R for up, down, right or left): ");
            
            //check if it is taken
            if (!board.takeEdge(row, col, current)){
                System.out.println("That edge is already taken. Try again.");
                continue;
            }
            // if a player just filled a box
            // i assume that there is a function that checks this
            // draw a line and check if a box is completed
            boolean completedBox = board.takeEdge(row, col, direction, current);
            }
            if(!board.isFull()) {
                curPlayerIdx = 1- curPlayerIdx;
            }
        end();
    }

    private void end(){
        board.displayBoard();
        showResult();
        askIfAgain();
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

    private void askIfAgain(){
        System.out.print("Would you like to play again? (y/n): ");
        String response = in.nextLine().trim().toLowerCase();
        if (response.equals("y") || response.equals("yes")) {
            // Reset game state
            keepPlaying = true;
            curPlayerIdx = 0;
            
            // Reset scores
            for (Player player : players) {
                player.resetScore();
            }
            
            // Ask for new board size
            int rows = readInt("Enter board rows (min 1): ", 1);
            int cols = readInt("Enter board columns (min 1): ", 1);
            
            // Create new board
            board = new DotsAndBoxesBoard(rows, cols);
            
            System.out.println("\nStarting new game...\n");
            play(); // Start new game
            break;
        } else if (response.equals("n") || response.equals("no")) {
            System.out.println("Thanks for playing! Goodbye!");
            keepPlaying = false;
            break;
        } else {
            System.out.println("Please enter 'y' for yes or 'n' for no.");
        }
    }

    // read a int with min is 1
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