# CS611-Assignment 2
## Dots and Boxes
---------------------------------------------------------------------------
- Name: Keenan Swenson, Xiang Hu
- Email: kswenson@bu.edu, hux@bu.edu
- Student ID: U80694517, U57420994

## Files
---------------------------------------------------------------------------

Board.java: Abstract class for a 2D game board
Game.java: Abstract class for a playable terminal game
Player.java: This class holds information about the player: name and score
DotsAndBoxesBoard.java: This class manages the dots and boxes game board, including edge tracking and box completion detection
DotsAndBoxesGame.java: This class handles the core game loop, user input, and game flow for dots and boxes
Edge.java: This class represents individual edges on the board that can be taken by players
GameManager.java: This class manages game selection interface and menu, allowing users to choose between different games
Main.java: This class is responsible for starting the game manager
SlidingPuzzleBoard.java: This class manages the sliding puzzle board (from Assignment 1)
SlidingPuzzleGame.java: This class handles the sliding puzzle game (from Assignment 1)
Tile.java: This class represents individual tiles in the sliding puzzle (from Assignment 1)
Piece.java: This class represents game pieces (from Assignment 1)

## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

1. **Dual Game System**: Integrated both Dots and Boxes and Sliding Puzzle games into a unified GameManager interface, allowing users to choose between games
2. **Flexible Board Sizing**: Board dimensions can be customized from 1x1 up to 50x50, with proper validation and error handling
3. **Smart Edge Management**: Uses separate arrays for horizontal and vertical edges instead of a traditional 2D piece array, making box completion detection efficient
4. **Real-time Box Detection**: Automatically detects when boxes are completed and awards points to the player who closed the box
5. **Turn Management**: Players only switch turns when they don't score a point, following traditional dots and boxes rules
6. **Comprehensive Input Validation**: Validates all user inputs including names, board dimensions, row/column coordinates, and directions
7. **Visual Board Display**: Clean ASCII art display showing dots, edges, and completed boxes with player initials
8. **Game State Tracking**: Tracks game completion, final scores, and determines winners with tie detection
9. **Replay Functionality**: Players can play multiple rounds with different board sizes
10. **Extensible Design**: Abstract Game and Board classes make it easy to add new game types in the future

## How to compile and run
---------------------------------------------------------------------------

1. Unzip folder and navigate to the 'src' directory
2. Run the following instructions:
javac *.java
java Main

## Input/Output Example
---------------------------------------------------------------------------
Output:
=======================================
      Welcome to the Game Manager!     
=======================================
This program lets you play the following games:
1) Dots and Boxes
2) Sliding Puzzle

---------------------------------------
Game Menu
1) Dots and Boxes
2) Sliding Puzzle
3) Quit
Enter your choice: 
Input:
1
Output:
=======================================
   CS611 Assignment 2: Dot and Boxes   
=======================================
This is a two-player terminal game.
Draw lines to connect the dots and form boxes.
Try to complete as many boxes as possible!

Enter Player 1's Name: 
Input:
Alice
Output:
Enter Player 2's Name: 
Input:
Bob
Output:
Enter board rows (min 1): 
Input:
2
Output:
Enter board columns (min 1): 
Input:
2
Output:

      0   1   2   
  0 .   .   .
    |   |   
  1 .   .   .
    |   |   
  2 .   .   .

Alice's turn
Enter row: 
Input:
0
Output:
Enter col: 
Input:
0
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
r
Output:

      0   1   2   
  0 .———.   .
    |   |   
  1 .   .   .
    |   |   
  2 .   .   .

Bob's turn
Enter row: 
Input:
0
Output:
Enter col: 
Input:
1
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
d
Output:

      0   1   2   
  0 .———.   .
    |   |   
  1 .   .   .
    |   |   
  2 .   .   .

Alice's turn
Enter row: 
Input:
1
Output:
Enter col: 
Input:
0
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
r
Output:

      0   1   2   
  0 .———.   .
    |   |   
  1 .———.   .
    |   |   
  2 .   .   .

Bob's turn
Enter row: 
Input:
0
Output:
Enter col: 
Input:
0
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
d
Output:

      0   1   2   
  0 .———.   .
    |   |   
  1 .———.   .
    |   |   
  2 .   .   .

Alice's turn
Enter row: 
Input:
1
Output:
Enter col: 
Input:
1
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
u
Output:

      0   1   2   
  0 .———.———.
    | A |   
  1 .———.   .
    |   |   
  2 .   .   .

Alice's turn
Enter row: 
Input:
1
Output:
Enter col: 
Input:
1
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
r
Output:

      0   1   2   
  0 .———.———.
    | A |   
  1 .———.———.
    |   |   
  2 .   .   .

Bob's turn
Enter row: 
Input:
1
Output:
Enter col: 
Input:
0
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
d
Output:

      0   1   2   
  0 .———.———.
    | A |   
  1 .———.———.
    |   |   
  2 .———.   .

Alice's turn
Enter row: 
Input:
2
Output:
Enter col: 
Input:
1
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
u
Output:

      0   1   2   
  0 .———.———.
    | A |   
  1 .———.———.
    | A |   
  2 .———.   .

Alice's turn
Enter row: 
Input:
2
Output:
Enter col: 
Input:
1
Output:
Enter direction (u/d/l/r for up, down, left or right): 
Input:
r
Output:

      0   1   2   
  0 .———.———.
    | A |   
  1 .———.———.
    | A |   
  2 .———.———.

=======================================
           GAME OVER!                 
=======================================
Final Scores:
Alice: 2 boxes
Bob: 0 boxes

🎉 Alice wins!
=======================================

Would you like to play again? (y/n): 
Input:
n
Output:
Thanks for playing! Goodbye!