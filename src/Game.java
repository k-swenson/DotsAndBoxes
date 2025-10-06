import java.util.Scanner;

public abstract class Game {
    protected Player[] players;
    protected Scanner in;

    public abstract void run();
}