# Design Document: Dots and Boxes Game System

## Overview
This document outlines the design architecture for the Dots and Boxes game system, which extends the existing Sliding Puzzle framework from Assignment 1. The system demonstrates object-oriented design principles through abstract base classes, inheritance hierarchies, and polymorphic behavior.

## Class Structure at High Level (UML Overview)

### Core Abstract Classes
```
Game (Abstract)
├── Player[] players
├── Scanner in
└── abstract void run()

Board (Abstract)
├── int rows, cols
├── int minRow, maxRow, minCol, maxCol
├── protected boolean validSize()
├── protected abstract void initialize()
└── public abstract void display()

Piece (Concrete)
├── int row, col
├── Piece(int row, int col)
├── int getRow()
└── int getCol()
```

### Game Implementations
```
Game
├── DotsAndBoxesGame
│   ├── DotsAndBoxesBoard board
│   ├── boolean keepPlaying
│   ├── int curPlayerIdx
│   └── void run()
└── SlidingPuzzleGame
    ├── SlidingPuzzleBoard board
    ├── boolean playAgain
    ├── Player player
    └── void run()
```

### Board Implementations
```
Board
├── DotsAndBoxesBoard
│   ├── Edge[][] horizontalEdges
│   ├── Edge[][] verticalEdges
│   ├── Player[][] boxWinners
│   ├── boolean takeEdge(int row, int col, String direction, Player player)
│   ├── void checkForFullBoxes(Player player)
│   ├── boolean isFull()
│   └── void display()
└── SlidingPuzzleBoard
    ├── Tile[][] tiles
    ├── boolean moveTile(int tileNumber)
    ├── boolean isSolved()
    └── void display()
```

### Supporting Classes
```
Player
├── String name
├── int score
├── Player(String name)
├── String getName()
├── int getScore()
├── void incrementScore()
└── void resetScore()

Edge extends Piece
├── boolean horizontal
├── Player takenBy
├── Edge(int row, int col, boolean horizontal)
├── boolean isTaken()
├── Player isTakenBy()
├── void take(Player player)
└── boolean isHorizontal()

Tile extends Piece
├── int number
├── Tile(int row, int col, int number)
├── int getNumber()
└── boolean isEmpty()

GameManager
├── Scanner scanner
├── Game currentGame
├── boolean quit
├── void start()
├── int pickGame()
└── void quit()
```

## Scalability and Extendibility Design

### 1. Abstract Base Classes Enable Extensibility

**Game Abstract Class:**
- Provides common structure for all games (player management, input handling)
- New game types can extend Game and implement the `run()` method
- Polymorphic behavior allows GameManager to handle any Game subclass

**Board Abstract Class:**
- Defines common board properties (dimensions, validation)
- Abstract methods force concrete implementations to define core functionality
- Size constraints can be customized per game type

**Piece Abstract Class:**
- Base class for all board elements (Edge, Tile)
- Common position tracking functionality
- Enables polymorphic collections of different piece types

### 2. Separation of Concerns

**Game Logic Separation:**
- `DotsAndBoxesGame`: Handles game flow, turn management, input validation
- `DotsAndBoxesBoard`: Manages board state, edge tracking, box completion
- `Edge`: Encapsulates individual edge state and ownership

**Data Structure Optimization:**
- Separate arrays for horizontal and vertical edges instead of unified 2D array
- Efficient box completion detection without scanning entire board
- Player-specific box ownership tracking

### 3. Configuration and Flexibility

**Dynamic Board Sizing:**
- Board dimensions configurable at runtime (1x1 to 50x50)
- Validation through abstract Board constraints
- Easy to modify size limits by changing constants

**Player Management:**
- Generic Player class works for any game type
- Score tracking adaptable to different scoring systems
- Support for variable number of players

### 4. Input/Output Abstraction

**Scanner Injection:**
- Games receive Scanner through constructor for testability
- Input validation centralized in each game implementation
- Easy to mock for unit testing

**Display Methods:**
- Each board type implements its own display logic
- ASCII art rendering maintains readability
- Consistent formatting across different game types

## Design Framework Selection and Evolution

### Starting Framework (Assignment 1)

**Original Sliding Puzzle Design:**
- Simple single-player game with Tile-based board
- Basic Game/Board abstract classes
- Player class with move counting
- Direct main() entry point

**Strengths of Original Framework:**
- Clean separation between game logic and board management
- Abstract classes provided good foundation for extension
- Simple but effective input validation
- Clear display formatting

### Changes Made for Assignment 2

**1. Enhanced Game Management:**
```java
// Added GameManager for multiple game support
public class GameManager {
    private Game currentGame;  // Polymorphic game handling
    // Menu-driven game selection
}
```

**2. Extended Board Architecture:**
```java
// DotsAndBoxesBoard uses specialized data structures
private Edge[][] horizontalEdges;    // Separate edge tracking
private Edge[][] verticalEdges;      // Optimized for box detection
private Player[][] boxWinners;       // Ownership tracking
```

**3. Multi-Player Support:**
```java
// Enhanced Player class for scoring
public class Player {
    private int score;  // Changed from move count to score
    public void incrementScore();  // New scoring method
}
```

**4. Specialized Piece Types:**
```java
// Edge class for Dots and Boxes
public class Edge extends Piece {
    private boolean horizontal;  // Edge orientation
    private Player takenBy;       // Ownership tracking
}
```

### Design Decisions Rationale

**1. Dual Array Structure for Edges:**
- **Decision:** Separate horizontal and vertical edge arrays
- **Rationale:** Enables efficient box completion detection without scanning entire board
- **Benefit:** O(1) edge access and O(rows×cols) box checking

**2. GameManager Pattern:**
- **Decision:** Centralized game selection and management
- **Rationale:** Allows easy addition of new game types
- **Benefit:** Single entry point, consistent user experience

**3. Polymorphic Game Handling:**
- **Decision:** GameManager holds Game reference, not specific implementations
- **Rationale:** Follows Open/Closed Principle - open for extension, closed for modification
- **Benefit:** New games can be added without changing existing code

**4. Edge-Based Board Representation:**
- **Decision:** Track edges rather than boxes as primary data structure
- **Rationale:** Edges are the actual game elements players interact with
- **Benefit:** Natural mapping to user input and game mechanics

**5. Score-Based Player System:**
- **Decision:** Changed Player from move counting to score tracking
- **Rationale:** Different games have different scoring mechanisms
- **Benefit:** Flexible scoring system adaptable to various game types

## Extensibility Examples

### Adding a New Game Type
```java
public class ChessGame extends Game {
    private ChessBoard board;
    public void run() { /* chess-specific logic */ }
}

public class ChessBoard extends Board {
    private ChessPiece[][] pieces;
    protected void initialize() { /* chess setup */ }
    public void display() { /* chess board display */ }
}
```

### Adding New Piece Types
```java
public class ChessPiece extends Piece {
    private PieceType type;
    private boolean isWhite;
    // chess-specific functionality
}
```

### Modifying Board Constraints
```java
public class LargeDotsAndBoxesBoard extends DotsAndBoxesBoard {
    private final int MAX_ROWS = 100;  // Override size limits
    private final int MAX_COLS = 100;
}
```

## Conclusion

The design successfully demonstrates object-oriented principles through:
- **Inheritance:** Abstract base classes with concrete implementations
- **Polymorphism:** GameManager handling different Game types uniformly
- **Encapsulation:** Private data with controlled access through methods
- **Abstraction:** High-level interfaces hiding implementation details

The framework is highly extensible, allowing new games to be added with minimal changes to existing code, while maintaining clean separation of concerns and efficient data structures optimized for each game type's specific requirements.
