# CS611-Assignment 1
## Sliding Puzzle
---------------------------------------------------------------------------
- Name: Keenan Swenson
- Email: kswenson@bu.edu
- Student ID: U80694517

## Files
---------------------------------------------------------------------------

Board.java: Abstract class for a 2D game board
Game.java: Abstract class for a playable terminal game
Player.java: This class holds information about the player: name, number of moves
SlidingPuzzleBoard.java: This class is responsible for keeping the sliding puzzle board updated after shuffling, moves, etc.
SlidingPuzzleGame.java: This class is responsible for taking user input and the core game loop
GameFactory.java: This class is responsible for creating new games. Allows for extendibility when introducing different game types.
Main.java: This class is responsible for starting the game

## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

1. Board size can be customized to any NxM size up to 50 each round played, can easily be made to increase size after each puzzle solved (anything bigger felt excessive)
2. Display adjusts itself regardless of digits to maintain a clean grid shape
3. Game shows how many moves it took to solve the puzzle
4. String and integer input validation as well as move validation
5. GameFactory class allows easy implementation of game selection if a new game subclass is created
6. Abstract game and board classes means new game type is easy to integrate

## How to compile and run
---------------------------------------------------------------------------

1. Unzip folder and navigate to 'kswenson-slidingpuzzle/src' directory
2. Run the following instructions:
javac *.java
java Main

## Input/Output Example
---------------------------------------------------------------------------
Output:
[+] Welcome to the Sliding Puzzle Game!
[+] What is your name?
Input:
Keenan
Output:
[+] Hi Keenan! Customize the size of your sliding puzzle:
[+] Number of Rows?
Input:
2
[+] Number of Columns?
Input:
2
Output:
[+] +-+-+
[+] |2|3|
[+] +-+-+
[+] |1|0|
[+] +-+-+
[+] Keenan, which tile do you want to slide to the empty space?
Input:
3
Output:
[+] +-+-+
[+] |2|0|
[+] +-+-+
[+] |1|3|
[+] +-+-+
[+] Keenan, which tile do you want to slide to the empty space?
Input:
2
Output:
[+] +-+-+
[+] |0|2|
[+] +-+-+
[+] |1|3|
[+] +-+-+
[+] Keenan, which tile do you want to slide to the empty space?
Input:
1
Output:
[+] +-+-+
[+] |1|2|
[+] +-+-+
[+] |0|3|
[+] +-+-+
[+] Keenan, which tile do you want to slide to the empty space?
Input:
3
Output:
[+] +-+-+
[+] |1|2|
[+] +-+-+
[+] |3|0|
[+] +-+-+
[+] Congrats Keenan! You solved the sliding puzzle in 4 moves! Play again?(y/n):
Input:
No

