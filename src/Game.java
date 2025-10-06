/**
 * Game
 * Abstract base class for all games.
 * Defines structure for game implementation like
 * player management and main game loop.
 */

import java.util.Scanner;

public abstract class Game {
    protected Player[] players;
    protected Scanner in;

    public abstract void run();
}